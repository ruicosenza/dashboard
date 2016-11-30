package br.com.dashboard.models.jc3;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author ruicosenza
 */
@Entity
@Table(name = "obj_item_uri_act_task_uri_vw", schema = "jc3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findAll", query = "SELECT o FROM ObjItemUriActTaskUriVw o"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByUriActTask", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.uriActTask = :uriActTask"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByUriObjItem", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.uriObjItem = :uriObjItem"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByActTaskId", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.actTaskId = :actTaskId"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByObjItemId", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.objItemId = :objItemId"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByNameTxt", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.nameTxt = :nameTxt"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findById", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.id = :id"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByEffctvStartDttm", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.effctvStartDttm = :effctvStartDttm"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByEffctvEndDttm", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.effctvEndDttm = :effctvEndDttm"),
    @NamedQuery(name = "ObjItemUriActTaskUriVw.findByScheduleCatCode", query = "SELECT o FROM ObjItemUriActTaskUriVw o WHERE o.scheduleCatCode = :scheduleCatCode")})
public class ObjItemUriActTaskUriVw implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "uri_act_task")
    private String uriActTask;
    @Column(name = "uri_obj_item")
    private String uriObjItem;
    @Column(name = "act_task_id")
    private BigInteger actTaskId;
    @Column(name = "obj_item_id")
    private BigInteger objItemId;
    @Column(name = "name_txt")
    private String nameTxt;
    @Column(name = "id")
    @Id
    private BigInteger id;
    @Column(name = "effctv_start_dttm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effctvStartDttm;
    @Column(name = "effctv_end_dttm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effctvEndDttm;
    @Column(name = "schedule_cat_code")
    private Short scheduleCatCode;

    public ObjItemUriActTaskUriVw() {
    }

    public String getUriActTask() {
        return uriActTask;
    }

    public void setUriActTask(String uriActTask) {
        this.uriActTask = uriActTask;
    }

    public String getUriObjItem() {
        return uriObjItem;
    }

    public void setUriObjItem(String uriObjItem) {
        this.uriObjItem = uriObjItem;
    }

    public BigInteger getActTaskId() {
        return actTaskId;
    }

    public void setActTaskId(BigInteger actTaskId) {
        this.actTaskId = actTaskId;
    }

    public BigInteger getObjItemId() {
        return objItemId;
    }

    public void setObjItemId(BigInteger objItemId) {
        this.objItemId = objItemId;
    }

    public String getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(String nameTxt) {
        this.nameTxt = nameTxt;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getEffctvStartDttm() {
        return effctvStartDttm;
    }

    public void setEffctvStartDttm(Date effctvStartDttm) {
        this.effctvStartDttm = effctvStartDttm;
    }

    public Date getEffctvEndDttm() {
        return effctvEndDttm;
    }

    public void setEffctvEndDttm(Date effctvEndDttm) {
        this.effctvEndDttm = effctvEndDttm;
    }

    public Short getScheduleCatCode() {
        return scheduleCatCode;
    }

    public void setScheduleCatCode(Short scheduleCatCode) {
        this.scheduleCatCode = scheduleCatCode;
    }
}