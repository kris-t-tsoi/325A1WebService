package nz.ac.auckland.userDetail;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String street;
	private String city;
	private int postocde;

}
