package nz.ac.auckland.userDetail;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
//import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Purchase;


@Entity
@Table(name="USERS")
public class User {
	
//	@XmlID
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;	
	
	@Column(name="USERNAME", nullable=false, length=20, unique = true)
	private String userName;
	
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	
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
	
	
	@ManyToOne(fetch=FetchType.LAZY)
    private CreditCardDetails CCDetails;
	
	@OneToMany(mappedBy = "buyer")
    private Set<Purchase> purchases = new HashSet<Purchase>();
	
	
	protected User(){}
	
	public User(String username, String lastName, String firstName){
		this.userName = username;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Long getId() {
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
	
	
	
	

}
