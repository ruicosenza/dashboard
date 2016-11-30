package br.com.dashboard.repositories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dashboard.daos.OlapDAO_Audit;
import br.com.dashboard.graphs.GeradorDeGrafico;
import br.com.dashboard.interfaces.MsgDaoAuditRepository;
import br.com.dashboard.interfaces.MsgReportDaoAuditRepository;
import br.com.dashboard.interfaces.ObjItemDAOJC3Repository;
import br.com.dashboard.models.audit.MensagemReport;
import br.com.dashboard.models.audit.VwListOperacoesAudit;
import br.com.dashboard.models.dto.DTOOperacao;
import br.com.dashboard.models.dto.Mensagem;
import br.com.dashboard.models.jc3.ObjItemUriActTaskUriVw;
import br.com.dashboard.models.olap.RptAbsQtdMsgRecebida;
import br.com.dashboard.services.RestClientConsumer;

@Component
public class ListaOperacoes {
	
	@Autowired
	private MsgReportDaoAuditRepository<MensagemReport, VwListOperacoesAudit> msg_reportDAO_Audit;
	@Autowired
	private ObjItemDAOJC3Repository<ObjItemUriActTaskUriVw, DTOOperacao> msgDAO_JC3;
	@Autowired
	private MsgDaoAuditRepository<Mensagem, DTOOperacao> msg_DAO_Audit;
	@Autowired
	private RestClientConsumer restClientConsumer;
	@Autowired
	private OlapDAO_Audit olap_DAO_Audit;
	
	public List<Mensagem> getListaMensagens() {
		List<VwListOperacoesAudit> operacoesAtivas = getOperacoesAtivas();
		List<DTOOperacao> listInformes = getListInformes(operacoesAtivas);
		List<Mensagem> resultListMsgs = listMsgsOperacoes(listInformes);
		return resultListMsgs;
	}
	
	/**
	 * Coleta a lista de 
	 * @param vwListOperacoesAudit
	 * @return
	 */
	private List<DTOOperacao> getListInformes( List<VwListOperacoesAudit> vwListOperacoesAudit ){
		List<DTOOperacao> dtoOperacoes = new ArrayList<DTOOperacao>();
		List<String> idOpe = new ArrayList<String>();
		List<DTOOperacao> list_informes = new ArrayList<>();
		
		for (VwListOperacoesAudit operacao : vwListOperacoesAudit) {
			DTOOperacao dtoOpe = new DTOOperacao();
			dtoOpe.setUriActTask(operacao.getUriActTask());
			dtoOpe.setNome(operacao.getOperacao());
			dtoOpe.setPlndstartdtm(operacao.getPlndstartdtm());
			dtoOpe.setPlndenddtm(operacao.getPlndenddttm());

			if (!idOpe.contains(dtoOpe.getUriActTask())) {
				dtoOperacoes.add(dtoOpe);
				idOpe.add(dtoOpe.getUriActTask());
			}
		}

		List<DTOOperacao> list_obj_adj = new ArrayList<>();

		try {
			list_obj_adj = restClientConsumer.getObjsAdjudicados(dtoOperacoes);
		} catch (Exception e) {
			list_obj_adj = msgDAO_JC3.findAll(dtoOperacoes);
			Logger.getLogger(ListaOperacoes.class.getName()).log(Level.SEVERE, null, e);
		}
		
		if (!list_obj_adj.isEmpty()) {
			list_informes = msg_DAO_Audit.countQtdInformes(list_obj_adj);
		}
		
		return list_informes;
	}
	
	/**
	 * Retorna a lista de operações em andamento
	 * @return
	 */
	private List<VwListOperacoesAudit> getOperacoesAtivas(){
		return msg_reportDAO_Audit.findAllOpe();
	}

	/**
	 * Método utilizado para calcular todas as mensagens trocadas por operação
	 * 
	 * @return lista de mensagens por operação
	 */
	private List<Mensagem> listMsgsOperacoes( List<DTOOperacao> list_informes ) {
		List<Mensagem> resultListMsgs = new ArrayList<>();

		if (!list_informes.isEmpty()) {

			for (DTOOperacao operacao : list_informes) {
				try {
					Mensagem msg = new Mensagem();
					msg.setOperacao(operacao.getNome());
					msg.setPlndstartdtm(new SimpleDateFormat("yyyy-MM-dd HH:MM").format(operacao.getPlndstartdtm()));
					msg.setPlndenddttm(new SimpleDateFormat("yyyy-MM-dd HH:MM").format(operacao.getPlndenddtm()));
					msg.setUri_act_task(operacao.getUriActTask());
					msg.setTotalAdjudicacoes(operacao.getObjsAdj() == null ? 0 : operacao.getObjsAdj().size());
					msg.setTotalInformes(operacao.getTotalInformes());

					resultListMsgs.add(msg);
				} catch (Exception e) {
					Logger.getLogger(ListaOperacoes.class.getName()).log(Level.WARNING, null, e);
					continue;
				}
			}
		}
		
		return resultListMsgs;
	}
	
	/**
	 * Retorna o número de objetos resgistrados no barramento
	 * @return
	 */
	public long getCountRegistros(){
		long count = msg_DAO_Audit.count();
		return count;
	}
	
	public ArrayList<String> getGrafico(){
		List<VwListOperacoesAudit> operacoesAtivas = getOperacoesAtivas();
		List<RptAbsQtdMsgRecebida> rptAbsQtdMsgRecebida = olap_DAO_Audit.findAll();
		HashMap<Date, Integer> map = new HashMap<>();
		GeradorDeGrafico gdf = new GeradorDeGrafico();
		ArrayList<String> images = new ArrayList<>();
		
		for (VwListOperacoesAudit operacao : operacoesAtivas) {
			for (RptAbsQtdMsgRecebida rptAbsQtdMsgRecebida2 : rptAbsQtdMsgRecebida) {
				if(operacao.getUriActTask().equals(rptAbsQtdMsgRecebida2.getUriActTask())){
					map.put(rptAbsQtdMsgRecebida2.getDia(), Integer.parseInt(rptAbsQtdMsgRecebida2.getTotalAbsDia()));
				}
			}
			
			images.add(gdf.getImage(map, operacao.getOperacao(), operacao.getUriActTask()));
		}

		return images;
	}
}