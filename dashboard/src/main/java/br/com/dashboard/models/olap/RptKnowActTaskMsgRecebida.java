package br.com.dashboard.models.olap;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruicosenza
 */
@Entity
@Table(name = "rpt_know_act_task_msg_recebida", schema = "olap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findAll", query = "SELECT r FROM RptKnowActTaskMsgRecebida r"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByDimId", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.dimId = :dimId"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByDimKeyUriActTask", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.dimKeyUriActTask = :dimKeyUriActTask"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByDimTimestamp", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.dimTimestamp = :dimTimestamp"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByFatoId", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.fatoId = :fatoId"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByFatoKeyQtdMsgRecebida", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.fatoKeyQtdMsgRecebida = :fatoKeyQtdMsgRecebida"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByFatoTimestamp", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.fatoTimestamp = :fatoTimestamp"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByKnowId", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.knowId = :knowId"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByDimAct", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.dimAct = :dimAct"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByFatoMsgRecebida", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.fatoMsgRecebida = :fatoMsgRecebida"),
    @NamedQuery(name = "RptKnowActTaskMsgRecebida.findByKnowTimestamp", query = "SELECT r FROM RptKnowActTaskMsgRecebida r WHERE r.knowTimestamp = :knowTimestamp")})
public class RptKnowActTaskMsgRecebida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "dim_id")
    private Integer dimId;
    @Column(name = "dim_key_uri_act_task")
    private String dimKeyUriActTask;
    @Column(name = "dim_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dimTimestamp;
    @Column(name = "fato_id")
    private Integer fatoId;
    @Column(name = "fato_key_qtd_msg_recebida")
    private Integer fatoKeyQtdMsgRecebida;
    @Column(name = "fato_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fatoTimestamp;
    @Column(name = "know_id")
    private Integer knowId;
    @Column(name = "dim_act")
    private Integer dimAct;
    @Column(name = "fato_msg_recebida")
    private Integer fatoMsgRecebida;
    @Column(name = "know_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date knowTimestamp;

    public RptKnowActTaskMsgRecebida() {
    }

    public Integer getDimId() {
        return dimId;
    }

    public void setDimId(Integer dimId) {
        this.dimId = dimId;
    }

    public String getDimKeyUriActTask() {
        return dimKeyUriActTask;
    }

    public void setDimKeyUriActTask(String dimKeyUriActTask) {
        this.dimKeyUriActTask = dimKeyUriActTask;
    }

    public Date getDimTimestamp() {
        return dimTimestamp;
    }

    public void setDimTimestamp(Date dimTimestamp) {
        this.dimTimestamp = dimTimestamp;
    }

    public Integer getFatoId() {
        return fatoId;
    }

    public void setFatoId(Integer fatoId) {
        this.fatoId = fatoId;
    }

    public Integer getFatoKeyQtdMsgRecebida() {
        return fatoKeyQtdMsgRecebida;
    }

    public void setFatoKeyQtdMsgRecebida(Integer fatoKeyQtdMsgRecebida) {
        this.fatoKeyQtdMsgRecebida = fatoKeyQtdMsgRecebida;
    }

    public Date getFatoTimestamp() {
        return fatoTimestamp;
    }

    public void setFatoTimestamp(Date fatoTimestamp) {
        this.fatoTimestamp = fatoTimestamp;
    }

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public Integer getDimAct() {
        return dimAct;
    }

    public void setDimAct(Integer dimAct) {
        this.dimAct = dimAct;
    }

    public Integer getFatoMsgRecebida() {
        return fatoMsgRecebida;
    }

    public void setFatoMsgRecebida(Integer fatoMsgRecebida) {
        this.fatoMsgRecebida = fatoMsgRecebida;
    }

    public Date getKnowTimestamp() {
        return knowTimestamp;
    }

    public void setKnowTimestamp(Date knowTimestamp) {
        this.knowTimestamp = knowTimestamp;
    }
}