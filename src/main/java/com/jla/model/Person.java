package com.jla.model;

import java.util.Date;



public class Person {
	
	
	
	private long id;
	private String firstname;
	private String lastname;
	private String gender;
	private String doc_type;
	private String doc_number;
	private Date birthdate;
	private String phone_mobile;
	private String phone_home;
	private String email_personal;
	private String email_work;
	private String job;
	private String nationality;
	private String company_name;
	private String address;
	private String city;
	private String county;
	private String state;
	private String zip;
	
	public Person(long id, String firstname, String lastname, String gender, String doc_type, String doc_number,
			Date birthdate, String phone_mobile, String phone_home,String email_personal, String email_work, String job,
			String nationality, String company_name, String address, String city, String county, String state,
			String zip) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.doc_type = doc_type;
		this.doc_number = doc_number;
		this.birthdate = birthdate;
		this.phone_mobile = phone_mobile;
		this.setPhone_home(phone_home);
		this.email_personal = email_personal;
		this.email_work = email_work;
		this.job = job;
		this.nationality = nationality;
		this.company_name = company_name;
		this.address = address;
		this.city = city;
		this.county = county;
		this.state = state;
		this.zip = zip;
	}
	
	public Person(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getDoc_number() {
		return doc_number;
	}

	public void setDoc_number(String doc_number) {
		this.doc_number = doc_number;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone_mobile() {
		return phone_mobile;
	}

	public void setPhone_mobile(String phone_mobile) {
		this.phone_mobile = phone_mobile;
	}

	public String getEmail_personal() {
		return email_personal;
	}

	public void setEmail_personal(String email_personal) {
		this.email_personal = email_personal;
	}

	public String getEmail_work() {
		return email_work;
	}

	public void setEmail_work(String email_work) {
		this.email_work = email_work;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone_home() {
		return phone_home;
	}

	public void setPhone_home(String phone_home) {
		this.phone_home = phone_home;
	}
	
	
	
	

}
