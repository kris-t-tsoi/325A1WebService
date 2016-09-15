package nz.ac.auckland.userDetail;
import java.util.Set;

import javax.persistence.*;
//import javax.xml.bind.annotation.*;

import nz.ac.auckland.account.*;

@Entity
public class User {
	
//	@XmlID
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long userId;
	
	
	private String firstName;
	private String lastName;
	private String address;
	private int contactNumber;
	private UserAge ageDetails;
	private UserGender gender;
	
	@ManyToMany(mappedBy="accountNumber")
	Set<Account> accountsOwned;
	
	
	public User(String firstName, String lastName, String address, int contactNumber, UserAge ageDetails, UserGender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.ageDetails = ageDetails;
		this.gender = gender;
	}
	
	public User(String firstName, String lastName, String address, int contactNumber, int age, boolean isStudent, UserGender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.ageDetails = new UserAge(age, isStudent);
		this.gender = gender;
	}
	
	public User(String firstName, String lastName, String address, int contactNumber, int age, UserGender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.ageDetails = new UserAge(age);
		this.gender = gender;
	}	
	
	public Long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public UserAge getAgeDetails() {
		return ageDetails;
	}

	public UserGender getGender() {
		return gender;
	}



}
