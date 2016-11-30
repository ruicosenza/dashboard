package br.com.dashboard.models.dto;

public class QueueStatus {

	private String name;
    private Integer size;
    private Integer queued;
    private Integer consumers;
    private Integer dequeuing;

    public QueueStatus(String name, Integer size, Integer queued, Integer consumers, Integer dequeuing) {
        this.name = name;
        this.size = size;
        this.queued = queued;
        this.consumers = consumers;
        this.dequeuing = dequeuing;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getQueued() {
		return queued;
	}

	public void setQueued(Integer queued) {
		this.queued = queued;
	}

	public Integer getConsumers() {
		return consumers;
	}

	public void setConsumers(Integer consumers) {
		this.consumers = consumers;
	}

	public Integer getDequeuing() {
		return dequeuing;
	}

	public void setDequeuing(Integer dequeuing) {
		this.dequeuing = dequeuing;
	}
	
	@Override
	public String toString() {
		return "QueueStatus [name=" + name + ", size=" + size + ", queued=" + queued + ", consumers=" + consumers
				+ ", dequeuing=" + dequeuing + "]";
	}
}