package br.com.dashboard.models.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@SqlResultSetMapping(name = "CountResults", classes = {
//		@ConstructorResult(targetClass = VwUriDataMsg.class, columns = { @ColumnResult(name = "count"),
//				@ColumnResult(name = "date", type = Timestamp.class) }) })
public class Count {
	private BigInteger count;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Count() {
	}

	public Count(BigInteger count, Date date) {
		super();
		this.count = count;
		this.date = date;
	}

	public BigInteger getCount() {
		return count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}