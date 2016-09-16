package nz.ac.auckland.userDetail;

import javax.persistence.*;

@Entity
public class CreditCardDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
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
