package com.jla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "inv_trx_det")
public class InventoryTransactionDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="quantity")
	private Double quantity;
	@Column(name="uom",length=15)
	private String uom;
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="product_id")
	private Product product;
	
	public InventoryTransactionDetails(long id, Double quantity, String uom, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.uom = uom;
		this.product = product;
	}

	public InventoryTransactionDetails(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(double i) {
		this.quantity = i;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	

}
