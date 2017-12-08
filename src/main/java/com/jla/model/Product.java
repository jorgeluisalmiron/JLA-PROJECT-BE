package com.jla.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "product_cat")
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="code",length=15)
	private String code;
	@Column(name="name",length=15)
	private String name;
	@Column(name="mark",length=50)
	private String mark;
	@Column(name="main_supplier",length=50)
	private String mainSupplier;
	@Column(name="uom",length=15)
	private String uom;

	public Product(long id, String code, String name, String mark, String mainSupplier, String uom) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.mark = mark;
		this.mainSupplier = mainSupplier;
		this.uom=uom;
	}
	
	public Product() {
	}


}
