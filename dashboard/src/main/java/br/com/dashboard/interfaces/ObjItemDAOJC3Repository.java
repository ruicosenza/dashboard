package br.com.dashboard.interfaces;

import java.util.List;

public interface ObjItemDAOJC3Repository<T, E> {
	public List<E> findAll( List<E> list );
}