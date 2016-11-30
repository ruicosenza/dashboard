/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dashboard.models.olap;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruicosenza
 */
@Entity
@Table(name = "rpt_abs_qtd_msg_recebida", schema="olap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findAll", query = "SELECT r FROM RptAbsQtdMsgRecebida r"),
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findByX", query = "SELECT r FROM RptAbsQtdMsgRecebida r WHERE r.totalMsgs = :totalMsgs"),
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findByZx", query = "SELECT r FROM RptAbsQtdMsgRecebida r WHERE r.totalAbsDia = :totalAbsDia"),
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findByIx", query = "SELECT r FROM RptAbsQtdMsgRecebida r WHERE r.dia = :dia"),
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findByUri", query = "SELECT r FROM RptAbsQtdMsgRecebida r WHERE r.uriActTask = :uriActTask"),
    @NamedQuery(name = "RptAbsQtdMsgRecebida.findByL", query = "SELECT r FROM RptAbsQtdMsgRecebida r WHERE r.nomeOperacao = :nomeOperacao")})
public class RptAbsQtdMsgRecebida implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "x")
    private String totalMsgs;
    @Column(name = "zx")
    private String totalAbsDia;
    @Column(name = "ix")
    private Date dia;
    @Column(name = "uri")
    private String uriActTask;
    @Column(name = "l")
    private String nomeOperacao;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTotalMsgs() {
		return totalMsgs;
	}
	public void setTotalMsgs(String totalMsgs) {
		this.totalMsgs = totalMsgs;
	}
	public String getTotalAbsDia() {
		return totalAbsDia;
	}
	public void setTotalAbsDia(String totalAbsDia) {
		this.totalAbsDia = totalAbsDia;
	}
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public String getUriActTask() {
		return uriActTask;
	}
	public void setUriActTask(String uriActTask) {
		this.uriActTask = uriActTask;
	}
	public String getNomeOperacao() {
		return nomeOperacao;
	}
	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}
	
	@Override
	public String toString() {
		return "RptAbsQtdMsgRecebida [id=" + id + ", totalMsgs=" + totalMsgs + ", totalAbsDia=" + totalAbsDia + ", dia="
				+ dia + ", uriActTask=" + uriActTask + ", nomeOperacao=" + nomeOperacao + "]";
	}
}