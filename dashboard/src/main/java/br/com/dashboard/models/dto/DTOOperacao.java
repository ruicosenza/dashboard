package br.com.dashboard.models.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DTOOperacao {
	private String uriActTask;
	private String nome;
	private String source;
    @Temporal(TemporalType.TIMESTAMP)
    private Date plndstartdtm;
    @Temporal(TemporalType.TIMESTAMP)
	private Date plndenddtm;
	private List<String> objsAdj;
	private int totalInformes;

	/**
	 * @return the UriActTask
	 */
	public String getUriActTask() {
		return uriActTask;
	}

	/**
	 * @param uriActTask
	 *            the id to set
	 */
	public void setUriActTask(String uriActTask) {
		this.uriActTask = uriActTask;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the objsAdj
	 */
	public List<String> getObjsAdj() {
		return objsAdj;
	}

	/**
	 * @param objsAdj
	 *            the objsAdj to set
	 */
	public void setObjsAdj(List<String> objsAdj) {
		this.objsAdj = objsAdj;
	}

	/**
	 * @return the plndstartdtm
	 */
	public Date getPlndstartdtm() {
		return plndstartdtm;
	}

	/**
	 * @param plndstartdtm
	 *            the plndstartdtm to set
	 */
	public void setPlndstartdtm(Date plndstartdtm) {
		this.plndstartdtm = plndstartdtm;
	}

	/**
	 * @return the plndenddtm
	 */
	public Date getPlndenddtm() {
		return plndenddtm;
	}

	/**
	 * @param plndenddtm
	 *            the plndenddtm to set
	 */
	public void setPlndenddtm(Date plndenddtm) {
		this.plndenddtm = plndenddtm;
	}

	public int getTotalInformes() {
		return totalInformes;
	}

	public void setTotalInformes(int totalInformes) {
		this.totalInformes = totalInformes;
	}

	@Override
	public String toString() {
		return "Operacao [uriActTask=" + uriActTask + ", nome=" + nome + ", source=" + source + ", plndstartdtm="
				+ plndstartdtm + ", plndenddtm=" + plndenddtm + ", objsAdj=" + objsAdj + ", totalInformes="
				+ totalInformes + "]";
	}
}