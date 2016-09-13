package nz.ac.auckland.userDetail;
import javax.persistence.*;
//import javax.xml.bind.annotation.*;

import nz.ac.auckland.userDetail.*;

@Entity
public class User {
	
//	@XmlID
	@Id
	@GeneratedValue
	private Long userId;
	
	
	private String firstName;
	private String lastName;
	private String address;
	private int contactNumber;
	private UserAge age;
	private UserGender gender;
	
	
	public User(String firstName, String lastName, String address, int contactNumber, UserAge ageDetails, UserGender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.age = ageDetails;
		this.gender = gender;
	}

}
