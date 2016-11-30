package br.com.dashboard.models.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.dashboard.models.dto.Count;

@Entity
@Table(name = "vw_list_uri_data_msg_audit", schema = "interc2")
@SqlResultSetMapping(name = "CountResults", classes = {
		@ConstructorResult(targetClass = Count.class, columns = { @ColumnResult(name = "count"),
				@ColumnResult(name = "dataMsg") }) })
public class VwUriDataMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String uri;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataMsg;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Date getDataMsg() {
		return dataMsg;
	}

	public void setDataMsg(Date dataMsg) {
		this.dataMsg = dataMsg;
	}

	@Override
	public String toString() {
		return "VwUriDataMsg [uri=" + uri + ", dataMsg=" + dataMsg + "]";
	}
}