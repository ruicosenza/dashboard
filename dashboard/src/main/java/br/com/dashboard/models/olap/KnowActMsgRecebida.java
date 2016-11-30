package br.com.dashboard.models.olap;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "know_act_msg_recebida", schema = "olap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KnowActMsgRecebida.findAll", query = "SELECT k FROM KnowActMsgRecebida k"),
    @NamedQuery(name = "KnowActMsgRecebida.findByKnowId", query = "SELECT k FROM KnowActMsgRecebida k WHERE k.knowId = :knowId"),
    @NamedQuery(name = "KnowActMsgRecebida.findByKnowTimestamp", query = "SELECT k FROM KnowActMsgRecebida k WHERE k.knowTimestamp = :knowTimestamp")})
public class KnowActMsgRecebida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "know_id")
    private Integer knowId;
    @Basic(optional = false)
    @Column(name = "know_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date knowTimestamp;
    @JoinColumn(name = "fato_msg_recebida", referencedColumnName = "fato_id")
    @ManyToOne(optional = false)
    private FatoMsgRecebida fatoMsgRecebida;
    @JoinColumn(name = "dim_act", referencedColumnName = "dim_id")
    @ManyToOne(optional = false)
    private DimAct dimAct;

    public KnowActMsgRecebida() {
    }

    public KnowActMsgRecebida(Integer knowId) {
        this.knowId = knowId;
    }

    public KnowActMsgRecebida(Integer knowId, Date knowTimestamp) {
        this.knowId = knowId;
        this.knowTimestamp = knowTimestamp;
    }

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public Date getKnowTimestamp() {
        return knowTimestamp;
    }

    public void setKnowTimestamp(Date knowTimestamp) {
        this.knowTimestamp = knowTimestamp;
    }

    public FatoMsgRecebida getFatoMsgRecebida() {
        return fatoMsgRecebida;
    }

    public void setFatoMsgRecebida(FatoMsgRecebida fatoMsgRecebida) {
        this.fatoMsgRecebida = fatoMsgRecebida;
    }

    public DimAct getDimAct() {
        return dimAct;
    }

    public void setDimAct(DimAct dimAct) {
        this.dimAct = dimAct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (knowId != null ? knowId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KnowActMsgRecebida)) {
            return false;
        }
        KnowActMsgRecebida other = (KnowActMsgRecebida) object;
        if ((this.knowId == null && other.knowId != null) || (this.knowId != null && !this.knowId.equals(other.knowId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.teste.KnowActMsgRecebida[ knowId=" + knowId + " ]";
    }
    
}