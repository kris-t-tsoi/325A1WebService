package nz.ac.auckland.userDetail.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Test;

import nz.ac.auckland.services.PersistenceManager;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

public class UserTest {

	@Test
	public void CreateUserTest() {
		
		
		Address address = new Address("abc Street", "Auckland", "New Zealand", 1111);
		User user = new User("userAwesome", "Some", "Awe");
		
//		Client client = ClientBuilder.newClient();
	}

}
