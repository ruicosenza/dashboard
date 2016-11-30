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
@Table(name = "dim_act", schema = "olap")
@XmlRootElement
@NamedQueries({
   @NamedQuery(name = "DimAct.findAll", query = "SELECT d FROM DimAct d"),
   @NamedQuery(name = "DimAct.findByDimId", query = "SELECT d FROM DimAct d WHERE d.dimId = :dimId"),
   @NamedQuery(name = "DimAct.findByDimKeyUriActTask", query = "SELECT d FROM DimAct d WHERE d.dimKeyUriActTask = :dimKeyUriActTask"),
   @NamedQuery(name = "DimAct.findByDimTimestamp", query = "SELECT d FROM DimAct d WHERE d.dimTimestamp = :dimTimestamp")})
public class DimAct implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "dim_id")
   private Integer dimId;
   @Basic(optional = false)
   @Column(name = "dim_key_uri_act_task")
   private String dimKeyUriActTask;
   @Basic(optional = false)
   @Column(name = "dim_timestamp")
   @Temporal(TemporalType.TIMESTAMP)
   private Date dimTimestamp;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "dimAct")
   private Collection<KnowActMsgRecebida> knowActMsgRecebidaCollection;

   public DimAct() {
   }

   public DimAct(Integer dimId) {
       this.dimId = dimId;
   }
   
   public DimAct(String dimKeyUriActTask) {
	   this.dimKeyUriActTask = dimKeyUriActTask;
   }

   public DimAct(Integer dimId, String dimKeyUriActTask, Date dimTimestamp) {
       this.dimId = dimId;
       this.dimKeyUriActTask = dimKeyUriActTask;
       this.dimTimestamp = dimTimestamp;
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
       hash += (dimId != null ? dimId.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       // TODO: Warning - this method won't work in the case the id fields are not set
       if (!(object instanceof DimAct)) {
           return false;
       }
       DimAct other = (DimAct) object;
       if ((this.dimId == null && other.dimId != null) || (this.dimId != null && !this.dimId.equals(other.dimId))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "com.mycompany.teste.DimAct[ dimId=" + dimId + " ]";
   }
   
}