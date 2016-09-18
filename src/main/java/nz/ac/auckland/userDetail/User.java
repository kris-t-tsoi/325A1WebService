package nz.ac.auckland.userDetail;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Purchase;

@XmlRootElement(name="user")
@Entity
@Table(name="USERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@XmlID
	@XmlAttribute(name="id")
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;	
	
	@XmlElement(name="user-name")
	@Column(name="USERNAME", nullable=false, length=20, unique = true)
	private String userName;
	
	@XmlElement(name="first-name")
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@XmlElement(name="last-name")
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@XmlElement(name="billing-address")
	@AttributeOverrides( {
		@AttributeOverride(name="street",
    			column=@Column(name="BILLING_STREET", nullable=false)),
    	@AttributeOverride(name="city",
    			column=@Column(name="BILLING_CITY", nullable=false)),
    	@AttributeOverride(name="country",
			column=@Column(name="BILLING_COUNTRY", nullable=false)),
    	@AttributeOverride(name="postcode",
    			column=@Column(name="BILLING_POST_CODE")),
    })
	private Address billingAddress;
	
	@XmlElement(name="shipping-address")
	@AttributeOverrides( {
		@AttributeOverride(name="street",
    			column=@Column(name="SHIPPING_STREET", nullable=false)),
    	@AttributeOverride(name="city",
    			column=@Column(name="SHIPPING_CITY", nullable=false)),
    	@AttributeOverride(name="country",
			column=@Column(name="SHIPPING_COUNTRY", nullable=false)),		
    	@AttributeOverride(name="postcode",
    			column=@Column(name="SHIPPING_POST_CODE")),
    })
	private Address shippingAddress;
	
	@XmlElement(name="creditcard-details")
	@OneToOne(cascade=CascadeType.ALL)
    private CreditCardDetails CCDetails;
	
	@XmlElement(name="purchase-history")
	@OneToMany(mappedBy = "buyer")
    private Set<Purchase> purchaseHistory = new HashSet<Purchase>();
	
	
	public User(){}
	
	public CreditCardDetails getCCDetails() {
		return CCDetails;
	}

	public void changeCCDetails(CreditCardDetails cCDetails) {
		CCDetails = cCDetails;
	}
	
	public void deleteCCDetails(){
		CCDetails = null;
	}

	public Set<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}
	
	public void addPurchase(Purchase purch){
		purchaseHistory.add(purch);
	}

	public User(String username, String lastName, String firstName){
		this.userName = username;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void changeBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void changeShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
//		return super.equals(obj);
		if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User usr = (User) obj;
        return new EqualsBuilder().
            append(id, usr.getId()).
            isEquals();
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
//		return super.hashCode();
		return new HashCodeBuilder(17, 31). 
				append(getClass().getName()).
	            append(id).
	            toHashCode();
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
	

}
