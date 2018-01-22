package jla.project.com.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@MappedSuperclass
public class PersonCompany{

    @NotNull
    @Size(max=50)
    @Column(name = "first_name")
    private String firstName;
    @Size(max=50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max=50)
    @Column(name = "company_name")
    private String companyName;
    @Size(max=20)
    @Column(name = "ident_type")
    private String identificationType;
    @Size(max=20)
    @Column(name = "ident_number")
    private String identificationNum;
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

    public PersonCompany() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PersonCompany(String firstName, String lastName, String companyName, String identificationType, String identificationNum, String address, String city, String country, String state, String zip, String phone1, String phone2, String url, String email, String email2, String email3, String comments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName= companyName;
        this.identificationType = identificationType;
        this.identificationNum = identificationNum;
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
