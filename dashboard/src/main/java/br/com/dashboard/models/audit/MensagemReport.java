package br.com.dashboard.models.audit;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruicosenza
 */
@Entity
@Table(name = "mensagem_report",schema="interc2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MensagemReport.findAll", query = "SELECT m FROM MensagemReport m"),
    @NamedQuery(name = "MensagemReport.findByMensagemId", query = "SELECT m FROM MensagemReport m WHERE m.mensagemId = :mensagemId"),
    @NamedQuery(name = "MensagemReport.findByNmMsg", query = "SELECT m FROM MensagemReport m WHERE m.nmMsg = :nmMsg"),
    @NamedQuery(name = "MensagemReport.findByTimeStamp", query = "SELECT m FROM MensagemReport m WHERE m.timeStamp = :timeStamp"),
    @NamedQuery(name = "MensagemReport.findByTxtMsg", query = "SELECT m FROM MensagemReport m WHERE m.txtMsg = :txtMsg"),
    @NamedQuery(name = "MensagemReport.findByEndpoint", query = "SELECT m FROM MensagemReport m WHERE m.endpoint = :endpoint"),
    @NamedQuery(name = "MensagemReport.findByDirection", query = "SELECT m FROM MensagemReport m WHERE m.direction = :direction"),
    @NamedQuery(name = "MensagemReport.findBySource", query = "SELECT m FROM MensagemReport m WHERE m.source = :source"),
    @NamedQuery(name = "MensagemReport.findByDestino", query = "SELECT m FROM MensagemReport m WHERE m.destino = :destino"),
    @NamedQuery(name = "MensagemReport.findByEndpointCatCode", query = "SELECT m FROM MensagemReport m WHERE m.endpointCatCode = :endpointCatCode"),
    @NamedQuery(name = "MensagemReport.findByNmMsgPt", query = "SELECT m FROM MensagemReport m WHERE m.nmMsgPt = :nmMsgPt"),
    @NamedQuery(name = "MensagemReport.findByTsmsg", query = "SELECT m FROM MensagemReport m WHERE m.tsmsg = :tsmsg"),
    @NamedQuery(name = "MensagemReport.findByUriActTask", query = "SELECT m FROM MensagemReport m WHERE m.uriActTask = :uriActTask"),
    @NamedQuery(name = "MensagemReport.findByOperacao", query = "SELECT m FROM MensagemReport m WHERE m.operacao = :operacao"),
    @NamedQuery(name = "MensagemReport.findByPlndstartdtm", query = "SELECT m FROM MensagemReport m WHERE m.plndstartdtm = :plndstartdtm"),
    @NamedQuery(name = "MensagemReport.findByPlndenddttm", query = "SELECT m FROM MensagemReport m WHERE m.plndenddttm = :plndenddttm")})
public class MensagemReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "mensagem_id")
    private Integer mensagemId;
    @Size(max = 50)
    @Column(name = "nm_msg")
    private String nmMsg;
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Size(max = 10000)
    @Column(name = "txt_msg")
    private String txtMsg;
    @Size(max = 100)
    @Column(name = "endpoint")
    private String endpoint;
    @Column(name = "direction")
    private Character direction;
    @Size(max = 10)
    @Column(name = "source")
    private String source;
    @Size(max = 10)
    @Column(name = "destino")
    private String destino;
    @Size(max = 6)
    @Column(name = "endpoint_cat_code")
    private String endpointCatCode;
    @Size(max = 50)
    @Column(name = "nm_msg_pt")
    private String nmMsgPt;
    @Column(name = "tsmsg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsmsg;
    @Size(max = 100)
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

    public MensagemReport() {
    }

    public Integer getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Integer mensagemId) {
        this.mensagemId = mensagemId;
    }

    public String getNmMsg() {
        return nmMsg;
    }

    public void setNmMsg(String nmMsg) {
        this.nmMsg = nmMsg;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTxtMsg() {
        return txtMsg;
    }

    public void setTxtMsg(String txtMsg) {
        this.txtMsg = txtMsg;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Character getDirection() {
        return direction;
    }

    public void setDirection(Character direction) {
        this.direction = direction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEndpointCatCode() {
        return endpointCatCode;
    }

    public void setEndpointCatCode(String endpointCatCode) {
        this.endpointCatCode = endpointCatCode;
    }

    public String getNmMsgPt() {
        return nmMsgPt;
    }

    public void setNmMsgPt(String nmMsgPt) {
        this.nmMsgPt = nmMsgPt;
    }

    public Date getTsmsg() {
        return tsmsg;
    }

    public void setTsmsg(Date tsmsg) {
        this.tsmsg = tsmsg;
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
}