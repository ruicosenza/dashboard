package br.com.dashboard.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dashboard.interfaces.ObjItemDAOJC3Repository;
import br.com.dashboard.models.dto.DTOOperacao;
import br.com.dashboard.models.jc3.ObjItemUriActTaskUriVw;

@Repository
@Transactional
public class ObjItemDAO_JC3 implements ObjItemDAOJC3Repository<ObjItemUriActTaskUriVw, DTOOperacao> {

	@PersistenceContext(unitName="jc3")
	@Qualifier("jc3")
	private EntityManager manager_jc3;
	
	private final static String OBJ_ITEM_URI_ACT_TASK_URI_VW = " SELECT DISTINCT obj.uriObjItem " +
													           "   FROM ObjItemUriActTaskUriVw obj " +
													           "  WHERE obj.uriActTask = :uri_act_task";
	
	@Override
	public List<DTOOperacao> findAll( List<DTOOperacao> ops ){
		List<String> objsAdjs = new ArrayList<>();
		for (DTOOperacao operacao : ops) {
			objsAdjs = getObjsAdjs(operacao.getUriActTask());
			operacao.setObjsAdj(objsAdjs);
        }
		
		return ops;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Retorna a lista de objetos adjudicados na operação
	 * @param idOpe uri_act_task da operação 
	 * @return
	 */
	private List<String> getObjsAdjs( String idOpe ){
		List<String> resultList = new ArrayList<>();
		try {
			List<String> resultList_Obj = (List<String>)manager_jc3.
														createQuery(OBJ_ITEM_URI_ACT_TASK_URI_VW).setParameter("uri_act_task", idOpe).getResultList();
			resultList = new ArrayList<>();
			for (String objItemUriActTaskUriVw : resultList_Obj) {
				resultList.add(objItemUriActTaskUriVw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultList;
	}
}