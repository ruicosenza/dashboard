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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ping")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ping.findAll", query = "SELECT p FROM Ping p"),
    @NamedQuery(name = "Ping.findByPingId", query = "SELECT p FROM Ping p WHERE p.pingId = :pingId"),
    @NamedQuery(name = "Ping.findByDestino", query = "SELECT p FROM Ping p WHERE p.destino = :destino"),
    @NamedQuery(name = "Ping.findByTimeStamp", query = "SELECT p FROM Ping p WHERE p.timeStamp = :timeStamp"),
    @NamedQuery(name = "Ping.findByStatus", query = "SELECT p FROM Ping p WHERE p.status = :status")})
public class Ping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ping_id")
    private Integer pingId;
    @Column(name = "destino")
    private String destino;
    @Column(name = "time_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Column(name = "status")
    private Boolean status;

    public Ping() {
    }

    public Ping(Integer pingId) {
        this.pingId = pingId;
    }

    public Integer getPingId() {
        return pingId;
    }

    public void setPingId(Integer pingId) {
        this.pingId = pingId;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pingId != null ? pingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ping)) {
            return false;
        }
        Ping other = (Ping) object;
        if ((this.pingId == null && other.pingId != null) || (this.pingId != null && !this.pingId.equals(other.pingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.teste.Ping[ pingId=" + pingId + " ]";
    }
}