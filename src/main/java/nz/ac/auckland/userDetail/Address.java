package nz.ac.auckland.userDetail;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name="STREET", nullable=false, length=50)
	private String street;
	
	@Column(name="CITY", nullable=false, length=30)
	private String city;
	
	@Column(name="COUNTRY", nullable=false, length=30)
	private String country;
	
	@Column(name="POST_CODE", length=10)
	private int postcode;
	
	//Needed by JPA.
	protected Address() {}
		
	public Address(String street, String city, String country, int postcode) {
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}
	

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getPostcode() {
		return postcode;
	}

}
