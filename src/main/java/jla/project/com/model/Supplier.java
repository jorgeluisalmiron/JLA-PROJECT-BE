package jla.project.com.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
public class Supplier extends PersonCompany{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Size(min=2,max=50)
	@Column(name = "supplier_type")
	private String supplierType;

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Supplier(long id, String firstName, String lastName, String companyName, String identificationType, String identificationNum, String address, String city, String country, String state, String zip, String phone1, String phone2, String url, String email, String email2, String email3, String comments, String supplierType) {
		super(firstName, lastName, companyName, identificationType, identificationNum, address, city, country, state, zip, phone1, phone2, url, email, email2, email3, comments);
		this.id = id;
		this.supplierType = supplierType;
	}
}
