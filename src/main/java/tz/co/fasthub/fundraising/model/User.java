package tz.co.fasthub.fundraising.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;

/**
 * Created by Revocatus Nyaindi on 9/12/2017.
 */

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
//	@Email(message = "*Please provide a valid Email")
	private String email;

	private String username;

	@Column(name = "password")
	@Transient
	private String password;

	private String cpassword;

	@Column(name = "name")
	private String name;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "company_name")
	private String campanyName;

	@Column(name = "position")
	private String position;

/*	@Column(name = "address")
	private String address;*/

	@Column(name = "physical_address")
	private String physicalAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "tel1")
	private String mobile1;

	@Column(name = "tel2")
	private String mobile2;

	@Column(name = "tel3")
	private String mobile3;

	@Column(name = "ref_first_name")
	private String refFirstName;

	@Column(name = "ref_last_name")
	private String refLastName;

	@Column(name = "ref_company")
	private String refCampany;

	@Column(name = "ref_position")
	private String refPosition;

	@Column(name = "ref_address")
	private String refAddress;

	@Column(name = "ref_phys_adrs")
	private String refPhysAddress;

	@Column(name = "ref_city")
	private String refCity;

	@Column(name = "ref_country")
	private String refCountry;

	@Column(name = "ref_tel1")
	private String refMobile1;

	@Column(name = "ref_tel2")
	private String refMobile2;

	@Column(name = "ref_tel3")
	private String refMobile3;

	@Column(name = "active")
	private int active;

	private String role;

	/*
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
*/

	public User() {
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCampanyName() {
		return campanyName;
	}

	public void setCampanyName(String campanyName) {
		this.campanyName = campanyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

/*
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
*/

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getMobile3() {
		return mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public String getRefFirstName() {
		return refFirstName;
	}

	public void setRefFirstName(String refFirstName) {
		this.refFirstName = refFirstName;
	}

	public String getRefLastName() {
		return refLastName;
	}

	public void setRefLastName(String refLastName) {
		this.refLastName = refLastName;
	}

	public String getRefCampany() {
		return refCampany;
	}

	public void setRefCampany(String refCampany) {
		this.refCampany = refCampany;
	}

	public String getRefPosition() {
		return refPosition;
	}

	public void setRefPosition(String refPosition) {
		this.refPosition = refPosition;
	}

	public String getRefAddress() {
		return refAddress;
	}

	public void setRefAddress(String refAddress) {
		this.refAddress = refAddress;
	}

	public String getRefPhysAddress() {
		return refPhysAddress;
	}

	public void setRefPhysAddress(String refPhysAddress) {
		this.refPhysAddress = refPhysAddress;
	}

	public String getRefCity() {
		return refCity;
	}

	public void setRefCity(String refCity) {
		this.refCity = refCity;
	}

	public String getRefCountry() {
		return refCountry;
	}

	public void setRefCountry(String refCountry) {
		this.refCountry = refCountry;
	}

	public String getRefMobile1() {
		return refMobile1;
	}

	public void setRefMobile1(String refMobile1) {
		this.refMobile1 = refMobile1;
	}

	public String getRefMobile2() {
		return refMobile2;
	}

	public void setRefMobile2(String refMobile2) {
		this.refMobile2 = refMobile2;
	}

	public String getRefMobile3() {
		return refMobile3;
	}

	public void setRefMobile3(String refMobile3) {
		this.refMobile3 = refMobile3;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

/*
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
*/

}
