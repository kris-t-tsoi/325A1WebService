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
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "USER_ID")
	private long id;	
	
	@Column(name="USERNAME", nullable=false, length=20, unique = true)
	private String userName;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@AttributeOverrides( {
		@AttributeOverride(name="street",
    			column=@Column(name="BILLING_STREET")),
    	@AttributeOverride(name="city",
    			column=@Column(name="BILLING_CITY")),
    	@AttributeOverride(name="country",
			column=@Column(name="BILLING_COUNTRY")),
    	@AttributeOverride(name="postcode",
    			column=@Column(name="BILLING_POST_CODE")),
    })
	private Address billingAddress;
	
	@AttributeOverrides( {
		@AttributeOverride(name="street",
    			column=@Column(name="SHIPPING_STREET")),
    	@AttributeOverride(name="city",
    			column=@Column(name="SHIPPING_CITY")),
    	@AttributeOverride(name="country",
			column=@Column(name="SHIPPING_COUNTRY")),		
    	@AttributeOverride(name="postcode",
    			column=@Column(name="SHIPPING_POST_CODE")),
    })
	private Address shippingAddress;
		
	@OneToMany(mappedBy = "buyer")
    private Set<Purchase> purchaseHistory = new HashSet<Purchase>();
	

	public void setPurchaseHistory(Set<Purchase> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public User(){}
	
	public Set<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}
	
	public void addPurchase(Purchase purch){
		purchaseHistory.add(purch);
	}

	public User(long l,String username, String lastName, String firstName,Address billing, Address shipping){
		this.id = l;
		this.userName = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.billingAddress = billing;
		this.shippingAddress = shipping;
	}
	
	public User(String username, String lastName, String firstName,Address billing, Address shipping){
		this(0,username,lastName,firstName,billing,shipping);
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

	public long getId() {
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

	public void setId(long id) {
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
