package br.com.dashboard.models.olap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruicosenza
 */
@Entity
@Table(name = "fato_msg_recebida", schema = "olap")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FatoMsgRecebida.findAll", query = "SELECT f FROM FatoMsgRecebida f"),
    @NamedQuery(name = "FatoMsgRecebida.findByFatoId", query = "SELECT f FROM FatoMsgRecebida f WHERE f.fatoId = :fatoId"),
    @NamedQuery(name = "FatoMsgRecebida.findByFatoKeyQtdMsgRecebida", query = "SELECT f FROM FatoMsgRecebida f WHERE f.fatoKeyQtdMsgRecebida = :fatoKeyQtdMsgRecebida"),
    @NamedQuery(name = "FatoMsgRecebida.findByFatoTimestamp", query = "SELECT f FROM FatoMsgRecebida f WHERE f.fatoTimestamp = :fatoTimestamp")})
public class FatoMsgRecebida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fato_id")
    private Integer fatoId;
    @Basic(optional = false)
    @Column(name = "fato_key_qtd_msg_recebida")
    private int fatoKeyQtdMsgRecebida;
    @Basic(optional = false)
    @Column(name = "fato_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fatoTimestamp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fatoMsgRecebida")
    private Collection<KnowActMsgRecebida> knowActMsgRecebidaCollection;

    public FatoMsgRecebida() {
    }

    public FatoMsgRecebida(Integer fatoId) {
        this.fatoId = fatoId;
    }

    public FatoMsgRecebida(Integer fatoId, int fatoKeyQtdMsgRecebida, Date fatoTimestamp) {
        this.fatoId = fatoId;
        this.fatoKeyQtdMsgRecebida = fatoKeyQtdMsgRecebida;
        this.fatoTimestamp = fatoTimestamp;
    }

    public Integer getFatoId() {
        return fatoId;
    }

    public void setFatoId(Integer fatoId) {
        this.fatoId = fatoId;
    }

    public int getFatoKeyQtdMsgRecebida() {
        return fatoKeyQtdMsgRecebida;
    }

    public void setFatoKeyQtdMsgRecebida(int fatoKeyQtdMsgRecebida) {
        this.fatoKeyQtdMsgRecebida = fatoKeyQtdMsgRecebida;
    }

    public Date getFatoTimestamp() {
        return fatoTimestamp;
    }

    public void setFatoTimestamp(Date fatoTimestamp) {
        this.fatoTimestamp = fatoTimestamp;
    }

    @XmlTransient
    public Collection<KnowActMsgRecebida> getKnowActMsgRecebidaCollection() {
        return knowActMsgRecebidaCollection;
    }

    public void setKnowActMsgRecebidaCollection(Collection<KnowActMsgRecebida> knowActMsgRecebidaCollection) {
        this.knowActMsgRecebidaCollection = knowActMsgRecebidaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fatoId != null ? fatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FatoMsgRecebida)) {
            return false;
        }
        FatoMsgRecebida other = (FatoMsgRecebida) object;
        if ((this.fatoId == null && other.fatoId != null) || (this.fatoId != null && !this.fatoId.equals(other.fatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.teste.FatoMsgRecebida[ fatoId=" + fatoId + " ]";
    }   
}