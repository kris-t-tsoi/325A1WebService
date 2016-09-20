package nz.ac.auckland.tests.userDetail;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.services.ItemMapper;
import nz.ac.auckland.services.PurchaseMapper;
import nz.ac.auckland.services.UserMapper;
import nz.ac.auckland.services.UserResource;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.PersistenceManager;
import nz.ac.auckland.userDetail.User;

public class UserTest {
	
	private static final String PURCHASE_WEB_SERVICE_URI = "http://localhost:10000/services/purchase";
	private static final String USER_WEB_SERVICE_URI = "http://localhost:10000/services/user";
	private static final String ITEM_WEB_SERVICE_URI = "http://localhost:10000/services/item";
	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);	
//	static Client client = ClientBuilder.newClient();

	
	@Before
	public void beforeTest() {
	    
	}
	
	
	
	
	//honestly idk what is going on, it works sometimes but not other times
	//sometimes it cant find the path and other times it when trying to access the object created
	//the id used (the id of the object) can not find the object created
	@Test
	public void CreateUserTest() {		
		Client client = ClientBuilder.newClient();
		
		Address address = new Address("12 abc Street", "Auckland", "New Zealand", 1111);
		UserDTO awesome = new UserDTO("userAwesome", "Some", "Awe", address, address);
		
		logger.debug("Open connection to write user");
		
		Response response = client
				.target(USER_WEB_SERVICE_URI).request()
				.post(Entity.xml(awesome));


		if (response.getStatus() != 201) {
			fail("Failed to create new user");
		}
		

		String location = response.getLocation().toString();
		logger.debug("location: "+location);
		response.close();
				

		// Check for new user
		UserDTO awesomeFromService = client.target(location).request()
				.accept("application/xml").get(UserDTO.class);

		// Check user details
		assertEquals(awesome.getLastName(), awesomeFromService.getLastName());
		assertEquals(awesome.getFirstName(), awesomeFromService.getFirstName());
		assertEquals(awesome.getUserName(), awesomeFromService.getUserName());
	}
	
	@Test
	public void testSingleItemPurchase(){
		Client client = ClientBuilder.newClient();
		
		
		Address address = new Address("12 abc Street", "Auckland", "New Zealand", 1111);
		User user = new User("soCool", "Cool", "So", address, address);
		
		Response userResponse = client
				.target(USER_WEB_SERVICE_URI).request()
				.post(Entity.xml(UserMapper.toDTO(user)));
		
		userResponse.close();
		
		List<Item> it = new ArrayList<Item>();
		Item i = new Item("Toothbrush", 5);
		ItemDTO itdto = ItemMapper.toDTO(i);
		
		Response itemResponse = client
				.target(ITEM_WEB_SERVICE_URI).request()
				.post(Entity.xml(itdto));
		
		itemResponse.close();
		

		it.add(i);
		
		Purchase pur = new Purchase(user, it);
		
		PurchaseDTO dto = PurchaseMapper.toDTO(pur);
				
		Response response = client
				.target(PURCHASE_WEB_SERVICE_URI).request()
				.post(Entity.xml(dto));
				
		if (response.getStatus() != 201) {
			fail("Failed to create new purchase");
		}
		
		String location = response.getLocation().toString();
		response.close();
		

		// Check entry was created
		PurchaseDTO FromService = client.target(location).request()
				.accept("application/xml").get(PurchaseDTO.class);
		
		// Check purchase details
		assertEquals(dto.getBuyer().getId(), FromService.getBuyer().getId());
		assertEquals(dto.getItems(), FromService.getItems());
//		assertEquals(dto.getLastName(), FromService.getLastName());
		
	}
	
	@Test
	public void testAddItemToPurchase(){
		
	}
	

}
