package jla.project.com.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "inv_trx_hdr")
public class InventoryTransaction {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="date")
	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date date;
	@Column(name="type" , length=10)
	private String type;
	@Column(name="comments" , length=50)	
	private String comments;
	@Column(name="status" , length=15)
	private String status;
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name="hdr_id")
	private List<InventoryTransactionDetails> details;  
	
	
	
	public InventoryTransaction(long id, Date date, String type, String comments, String status,
			List<InventoryTransactionDetails> details) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.comments = comments;
		this.status = status;
		this.details = details;
	}

	public InventoryTransaction(long id, Date date, String type, String comments, String status) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.comments = comments;
		this.status = status;
		
	}
	public InventoryTransaction(){
	
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<InventoryTransactionDetails> getdetails() {
		return details;
	}

	public void setdetails(List<InventoryTransactionDetails> details) {
		this.details = details;
	}
	
	public void addTrxDetail(InventoryTransactionDetails det) 
	{ 
	        this.details.add(det); 
	 } 
	
	
}
