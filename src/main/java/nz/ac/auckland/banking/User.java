package nz.ac.auckland.banking;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

import nz.ac.auckland.userDetail.*;

public class User {
	
	@XmlID
	private Long id;
	
	
	private String firstName;
	private String lastName;
	private String address;
	private int contactNumber;
	private UserAge age;
	private UserGender gender;

}
