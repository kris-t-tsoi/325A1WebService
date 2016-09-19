package nz.ac.auckland.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCardDetailsDTO {
	
	@XmlID
	protected int id;
	
	@XmlElement(name="owner")
	protected String owner;
	
	@XmlElement(name="card-number")
	private String cardNumber;
	
	@XmlElement(name="expiry-date")
	private String expiryDate;
	
	private CreditCardDetailsDTO(String owner, String cardNumber, String expiryDate) {
		this(0,owner,cardNumber,expiryDate);
	}
	
	public CreditCardDetailsDTO(int id,String owner, String cardNumber, String expiryDate) {
		this.id = id;
		this.owner = owner;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

	
	
}
