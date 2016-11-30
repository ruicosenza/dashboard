package br.com.dashboard.interfaces;

import java.util.List;

public interface MsgDaoAuditRepository<T, E> {
	public List<E> countQtdInformes( List<E> list );
	public long count();
}