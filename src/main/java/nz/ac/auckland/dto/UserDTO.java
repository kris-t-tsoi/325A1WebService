package nz.ac.auckland.dto;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.userDetail.Address;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {

		@XmlID
		@XmlAttribute(name="id")
		private int id;	
		
		@XmlElement(name="user-name")
		private String userName;
		
		@XmlElement(name="first-name")
		private String firstName;
		
		@XmlElement(name="last-name")
		private String lastName;

		@XmlElement(name="billing-address")
		private Address billingAddress;
		
		@XmlElement(name="shipping-address")
		private Address shippingAddress;


		public UserDTO(String username, String lastName, String firstName, Address billing, Address shipping){
			this(0,username,lastName,firstName,billing,shipping);
		}
		
		public UserDTO(int id, String username, String lastName, String firstName, Address billing, Address shipping){
			this.id = id;
			this.userName = username;
			this.lastName = lastName;
			this.firstName = firstName;
			this.billingAddress = billing;
			this.shippingAddress = shipping;
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
//			return super.equals(obj);
			if (!(obj instanceof UserDTO))
	            return false;
	        if (obj == this)
	            return true;

	        UserDTO usr = (UserDTO) obj;
	        return new EqualsBuilder().
	            append(id, usr.getId()).
	            isEquals();
		}
		
		
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
//			return super.hashCode();
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
