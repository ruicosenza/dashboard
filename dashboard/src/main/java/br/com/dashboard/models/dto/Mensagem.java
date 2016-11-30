package br.com.dashboard.models.dto;

public class Mensagem {

	private String uri_act_task;
	private String plndstartdtm;
	private String plndenddttm;
	private String operacao;
	private int totalAdjudicacoes;
	private int totalInformes;

	public String getUri_act_task() {
		return uri_act_task;
	}
	public void setUri_act_task(String uri_act_task) {
		this.uri_act_task = uri_act_task;
	}
	public String getPlndstartdtm() {
		return plndstartdtm;
	}
	public void setPlndstartdtm(String plndstartdtm) {
		this.plndstartdtm = plndstartdtm;
	}
	public String getPlndenddttm() {
		return plndenddttm;
	}
	public void setPlndenddttm(String date) {
		this.plndenddttm = date;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public int getTotalAdjudicacoes() {
		return totalAdjudicacoes;
	}
	public void setTotalAdjudicacoes(int totalAdjudicacoes) {
		this.totalAdjudicacoes = totalAdjudicacoes;
	}
	public int getTotalInformes() {
		return totalInformes;
	}
	public void setTotalInformes(int totalInformes) {
		this.totalInformes = totalInformes;
	}
	@Override
	public String toString() {
		return "Mensagem [uri_act_task=" + uri_act_task + ", plndstartdtm=" + plndstartdtm + ", plndenddttm="
				+ plndenddttm + ", operacao=" + operacao + ", totalAdjudicacoes=" + totalAdjudicacoes
				+ ", totalInformes=" + totalInformes + "]";
	}
}