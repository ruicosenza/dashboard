package br.com.dashboard.models.audit;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class VwListOperacoesAudit  {

    private BigInteger id;
    @Column(name = "uri_act_task")
    private String uriActTask;
    @Column(name = "operacao")
    private String operacao;
    @Column(name = "plndstartdtm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plndstartdtm;
    @Column(name = "plndenddttm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plndenddttm;

    public VwListOperacoesAudit() {
    }

    public String getUriActTask() {
        return uriActTask;
    }

    public void setUriActTask(String uriActTask) {
        this.uriActTask = uriActTask;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Date getPlndstartdtm() {
        return plndstartdtm;
    }

    public void setPlndstartdtm(Date plndstartdtm) {
        this.plndstartdtm = plndstartdtm;
    }

    public Date getPlndenddttm() {
        return plndenddttm;
    }

    public void setPlndenddttm(Date plndenddttm) {
        this.plndenddttm = plndenddttm;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
