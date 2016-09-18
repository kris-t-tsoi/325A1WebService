package nz.ac.auckland.userDetail;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Embeddable
public class Address {
//	
//	@XmlElement(name="street-number")
//	@Column(name="STREET_NUMBER", nullable=false, length=10)
//	private String streetNumber;
	
	@XmlElement(name="street")
	@Column(name="STREET", nullable=false, length=50)
	private String street;
	
	@XmlElement(name="city")
	@Column(name="CITY", nullable=false, length=30)
	private String city;
	
	@XmlElement(name="country")
	@Column(name="COUNTRY", nullable=false, length=30)
	private String country;
	
	@XmlElement(name="post-code")
	@Column(name="POST_CODE", length=10)
	private int postcode;
	
	//Needed by JPA.
	protected Address() {}
		
	public Address(/*String srtNum,*/ String street, String city, String country, int postcode) {
//		this.streetNumber = srtNum;
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}
	
//
//	public String getStreetNumber() {
//		return streetNumber;
//	}

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
	
	public void ChangeAddress (/*String srtNum,*/String street, String city, String country, int postcode){
//		this.streetNumber = srtNum;
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}

}
