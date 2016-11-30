package br.com.dashboard.interfaces;

import java.util.List;

public interface MsgReportDaoAuditRepository<T, E>  {
	public List<E> findAllOpe( );
	public E findOpe( String uri_act_task );
}