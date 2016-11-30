package br.com.dashboard.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dashboard.interfaces.MsgReportDaoAuditRepository;
import br.com.dashboard.models.audit.MensagemReport;
import br.com.dashboard.models.audit.VwListOperacoesAudit;
import br.com.dashboard.repositories.DataHoraBarramento;

@Repository
@Transactional
public class Msg_ReportDAO_Audit implements MsgReportDaoAuditRepository<MensagemReport, VwListOperacoesAudit> {

	@PersistenceContext(unitName = "audit")
	@Qualifier("audit")
	private EntityManager manager_audit;

	/**
	 * Retorna a lista de operações em andamento baseadas no horário do
	 * barramento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<VwListOperacoesAudit> findAllOpe() {
		List<Object> resultList = new ArrayList<>();
		List<VwListOperacoesAudit> operacoesResultList = new ArrayList<>();
		try {
			DataHoraBarramento dataHora = new DataHoraBarramento();
			String dataHoraBarramento = dataHora.getDataHoraBarramento();

			String data = dataHoraBarramento.split(" ")[0];
			String hora = dataHoraBarramento.split(" ")[1];
			String dia = data.split("/")[0];
			String mes = data.split("/")[1];
			String ano = data.split("/")[2];

			String dataNova = mes + "/" + dia + "/" + ano + " " + hora;

			resultList = manager_audit.createNativeQuery(" SELECT uri_act_task, operacao, plndstartdttm, plndenddttm "
					+ "   FROM interc2.get_vw_list_operacoes_audit( '" + dataNova + "' ) ").getResultList();

			for (int i = 0; i < resultList.size(); i++) {
				try {
					Object[] row = (Object[]) resultList.get(i);
					VwListOperacoesAudit vwLOA = new VwListOperacoesAudit();
					vwLOA.setOperacao((String) row[1]);
					vwLOA.setPlndstartdtm((Date) row[2]);
					vwLOA.setPlndenddttm((Date) row[3]);
					vwLOA.setUriActTask((String) row[0]);
					operacoesResultList.add(vwLOA);
				} catch (Exception e) {
					Logger.getLogger(Msg_ReportDAO_Audit.class.getName()).log(Level.WARNING, null, e);
					continue;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(Msg_ReportDAO_Audit.class.getName()).log(Level.WARNING, null, e);
		}

		return operacoesResultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VwListOperacoesAudit findOpe(String uri_act_task) {
		List<Object> resultList = new ArrayList<>();

		try {
			DataHoraBarramento dataHora = new DataHoraBarramento();
			String dataHoraBarramento = dataHora.getDataHoraBarramento();
			
			String data = dataHoraBarramento.split(" ")[0];
			String hora = dataHoraBarramento.split(" ")[1];
			String dia = data.split("/")[0];
			String mes = data.split("/")[1];
			String ano = data.split("/")[2];
			
			String dataNova = mes + "/" + dia + "/" + ano + " " + hora;
			
			resultList = manager_audit.createNativeQuery(" SELECT uri_act_task, operacao, plndstartdttm, plndenddttm " + 
						 							     "   FROM interc2.get_vw_list_operacoes_audit( '" + dataNova + "' ) ").getResultList();
			
			for( int i = 0; i < resultList.size(); i++ ){
				Object[] row = (Object[]) resultList.get(i);
				if( ((String) row[0]).equalsIgnoreCase(uri_act_task) ){
				VwListOperacoesAudit vwLOA = new VwListOperacoesAudit();
				vwLOA.setOperacao((String) row[1]);
				vwLOA.setPlndstartdtm((Date) row[2]);
				vwLOA.setPlndenddttm((Date) row[3]);
				vwLOA.setUriActTask((String) row[0]);
				return vwLOA;
				}
			}
		} catch (Exception e) {
			Logger.getLogger(Msg_ReportDAO_Audit.class.getName()).log(Level.WARNING, null, e);
		}
		return null;
	}
}