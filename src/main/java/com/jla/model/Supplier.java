package com.jla.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	@Size(min=2,max=50)
	private String name;
	@Size(min=2,max=20)
	private String taxNumber;
	@Size(max=50)
	private String type;
	@Size(max=100)
	private String address;
	@Size(max=50)
	private String city;
	@Size(max=50)
	private String country;
	@Size(max=50)
	private String state;
	@Size(max=15)
	private String zip;
	@Size(max=20)
	private String phone1;
	@Size(max=20)
	private String phone2;
	@Size(max=50)
	private String url;
	@Size(max=50)
	private String email;
	@Size(max=50)
	private String email2;
	@Size(max=50)
	private String email3;
	@Size(max=100)
	private String comments;
	
	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(long id, String name, String taxNumber, String type, String address, String city, String country,
			String state, String zip, String phone1, String phone2, String url, String email, String email2,
			String email3, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.taxNumber = taxNumber;
		this.type = type;
		this.address = address;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zip = zip;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.url = url;
		this.email = email;
		this.email2 = email2;
		this.email3 = email3;
		this.comments = comments;
	}

}
