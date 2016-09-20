package nz.ac.auckland.tests.userDetail;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.services.user.UserResource;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.PersistenceManager;
import nz.ac.auckland.userDetail.User;

public class UserTest {
	
	private static final String WEB_SERVICE_URI = "http://localhost:10000/services/user";
	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	static Client client = ClientBuilder.newClient();
	
	
	//client only use dto
	//server uses dto but can use domain
	//database only use domain

	@Test
	public void CreateUserTest() {		
		
		Address address = new Address("12 abc Street", "Auckland", "New Zealand", 1111);
		UserDTO awesome = new UserDTO("userAwesome", "Some", "Awe", address, address);
		
		logger.debug("Open connection to write user");
		
		Response response = client
				.target(WEB_SERVICE_URI).request()
				.post(Entity.xml(awesome));


		if (response.getStatus() != 201) {
			fail("Failed to create new user");
		}
		

		String location = response.getLocation().toString();
		logger.debug("location: "+location);
		response.close();
		
		
//
//		// Check for new user
//		User awesomeFromService = client.target(location).request()
//				.accept("application/xml").get(User.class);
//
//		// Check user details
//		assertEquals(awesome.getLastName(), awesomeFromService.getLastName());
//		assertEquals(awesome.getFirstName(), awesomeFromService.getFirstName());
//		assertEquals(awesome.getUserName(), awesomeFromService.getUserName());
//		assertEquals(awesome.getBillingAddress(), awesomeFromService.getBillingAddress());
//		assertEquals(awesome.getShippingAddress(), awesomeFromService.getShippingAddress());
	}

}
