package br.com.dashboard.interfaces;

import java.util.List;

import br.com.dashboard.models.dto.QueueStatus;

public interface QueueStatusRepository {
	List<QueueStatus> all();
}