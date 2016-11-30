package br.com.dashboard.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dashboard.interfaces.MsgDaoAuditRepository;
import br.com.dashboard.models.dto.DTOOperacao;
import br.com.dashboard.models.dto.Mensagem;

@Repository
@Transactional
public class MensagemDAO_Audit implements MsgDaoAuditRepository<Mensagem, DTOOperacao> {

	@PersistenceContext(unitName = "audit")
	@Qualifier("audit")
	private EntityManager manager_audit;

	/**
	 * Retorna a lista de operações com o total de informes
	 * 
	 * @param operacoes
	 * @return
	 */
	@Override
	public List<DTOOperacao> countQtdInformes(List<DTOOperacao> operacoes) {
		int totalInformes = 0;
		Query createNativeQuery;
		List<DTOOperacao> ops = new ArrayList<>();
		HashMap<String, String> map = new HashMap<>();

		for (DTOOperacao operacao : operacoes) {
			totalInformes = 0;
			try {
				if (operacao.getObjsAdj() != null) {

					for (String idObjAdj : operacao.getObjsAdj()) {
						if( map.containsKey(operacao.getUriActTask()) && map.containsValue(idObjAdj) ){
							continue;
						}
						createNativeQuery = manager_audit.createNativeQuery( " SELECT qtd " + 
																			 "   FROM interc2.get_qtd_informes( '" + idObjAdj + "', " +
																			 		 							" '" + operacao.getPlndstartdtm() + "', " + 
																			 		 							" '" + operacao.getPlndenddtm() + "', " +
																			 		 							" '" + operacao.getUriActTask() + "' )" ) ;

						try {
							totalInformes += Integer.valueOf(createNativeQuery.getResultList().get(0).toString());
						} catch (NumberFormatException e) {
							totalInformes += 0;
							Logger.getLogger(MensagemDAO_Audit.class.getName()).log(Level.WARNING, null, e);
						}
						map.put(operacao.getUriActTask(), idObjAdj);
					}
				}
			} catch (Exception e) {
				Logger.getLogger(MensagemDAO_Audit.class.getName()).log(Level.WARNING, null, e);
			}

			operacao.setTotalInformes(totalInformes);
			ops.add(operacao);
		}

		return ops;

	}

	/**
	 * Retorna o número de registros de meios no barramento
	 */
	public long count() {
		Query createNativeQuery;
		int totalRegistros = 0;
		try {
			createNativeQuery = manager_audit.createNativeQuery(" SELECT COUNT(1) " + 
																"   FROM interc2.mensagens m " +
																"  INNER JOIN interc2.eventos e ON m.nm_msg = e.nm_msg " +
																"							   AND e.status = 'O' " +
																"							   AND e.place_log = 'E' " +
																"  WHERE direction = 'I' " + 
																"    AND endpoint_cat_code = 'R'");
			totalRegistros += Integer.valueOf(createNativeQuery.getResultList().get(0).toString());
		} catch (Exception e) {
			Logger.getLogger(MensagemDAO_Audit.class.getName()).log(Level.WARNING, null, e);
		}
		return totalRegistros;
	}
}