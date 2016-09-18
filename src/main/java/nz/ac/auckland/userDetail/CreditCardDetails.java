package nz.ac.auckland.userDetail;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class CreditCardDetails {
	
	@XmlID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@XmlElement(name="owner")
	@Column(nullable = false)
	protected String owner;
	
	@XmlElement(name="card-number")
	private String cardNumber;
	
	@XmlElement(name="expiry-date")
	private String expiryDate;
	
	private CreditCardDetails() {}
	
	public CreditCardDetails(String owner, String cardNumber, String expiryDate) {
		this.owner = owner;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

}
