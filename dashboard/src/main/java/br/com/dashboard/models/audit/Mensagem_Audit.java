package br.com.dashboard.models.audit;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ruicosenza
 */
@Entity
@Table(name = "mensagens", schema="interc2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagem_Audit.findAll", query = "SELECT m FROM Mensagem_Audit m"),
    @NamedQuery(name = "Mensagem_Audit.findByMensagemId", query = "SELECT m FROM Mensagem_Audit m WHERE m.mensagemId = :mensagemId"),
    @NamedQuery(name = "Mensagem_Audit.findByNmMsg", query = "SELECT m FROM Mensagem_Audit m WHERE m.nmMsg = :nmMsg"),
    @NamedQuery(name = "Mensagem_Audit.findByTimeStamp", query = "SELECT m FROM Mensagem_Audit m WHERE m.timeStamp = :timeStamp"),
    @NamedQuery(name = "Mensagem_Audit.findByTxtMsg", query = "SELECT m FROM Mensagem_Audit m WHERE m.txtMsg = :txtMsg"),
    @NamedQuery(name = "Mensagem_Audit.findByEndpoint", query = "SELECT m FROM Mensagem_Audit m WHERE m.endpoint = :endpoint"),
    @NamedQuery(name = "Mensagem_Audit.findByDirection", query = "SELECT m FROM Mensagem_Audit m WHERE m.direction = :direction"),
    @NamedQuery(name = "Mensagem_Audit.findBySource", query = "SELECT m FROM Mensagem_Audit m WHERE m.source = :source"),
    @NamedQuery(name = "Mensagem_Audit.findByDestino", query = "SELECT m FROM Mensagem_Audit m WHERE m.destino = :destino"),
    @NamedQuery(name = "Mensagem_Audit.findByEndpointCatCode", query = "SELECT m FROM Mensagem_Audit m WHERE m.endpointCatCode = :endpointCatCode"),
    @NamedQuery(name = "Mensagem_Audit.findByNmMsgPt", query = "SELECT m FROM Mensagem_Audit m WHERE m.nmMsgPt = :nmMsgPt"),
    @NamedQuery(name = "Mensagem_Audit.findByTsmsg", query = "SELECT m FROM Mensagem_Audit m WHERE m.tsmsg = :tsmsg")})
public class Mensagem_Audit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mensagem_id")
    private Integer mensagemId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nm_msg")
    private String nmMsg;
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Size(max = 10000)
    @Column(name = "txt_msg")
    private String txtMsg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "endpoint")
    private String endpoint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "direction")
    private Character direction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "source")
    private String source;
    @Size(max = 10)
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "endpoint_cat_code")
    private String endpointCatCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nm_msg_pt")
    private String nmMsgPt;
    @Column(name = "tsmsg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tsmsg;

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
}