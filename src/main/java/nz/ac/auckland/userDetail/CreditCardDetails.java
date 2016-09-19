package nz.ac.auckland.userDetail;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
public class CreditCardDetails {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	
	@Column(nullable = false)
	protected String owner;
	
	private String cardNumber;
	
	private String expiryDate;
	
	private CreditCardDetails() {}
	
	public CreditCardDetails(String owner, String cardNumber, String expiryDate) {
		this.owner = owner;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
	}

}
