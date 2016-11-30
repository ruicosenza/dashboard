package br.com.dashboard.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.dashboard.models.olap.RptAbsQtdMsgRecebida;

@Repository
@Transactional
public class OlapDAO_Audit {

	@PersistenceContext(unitName = "audit")
	@Qualifier("audit")
	private EntityManager manager_audit;
	
	@SuppressWarnings("unchecked")
	public List<RptAbsQtdMsgRecebida> findAll(){
		return manager_audit.createNamedQuery("RptAbsQtdMsgRecebida.findAll").getResultList();
	}
}