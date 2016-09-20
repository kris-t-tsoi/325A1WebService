package nz.ac.auckland.dto;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDTO {
	
	@XmlElement(name="street",nillable=false)
	private String street;
	
	@XmlElement(name="city",nillable=false)
	private String city;
	
	@XmlElement(name="country",nillable=false)
	private String country;
	
	@XmlElement(name="post-code")
	private int postcode;
	
	//Needed by JPA.
	protected AddressDTO() {}
		
	public AddressDTO( String street, String city, String country, int postcode) {
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
	
	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
}
