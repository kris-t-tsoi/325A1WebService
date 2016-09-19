package nz.ac.auckland.tests.purchaseItems;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

public class PurchaseTest {
	private static final String WEB_SERVICE_URI = "http://localhost:10000/services/purchase";
	
	@Test
	public void testSingleItemPurchase(){
		Address address = new Address("12 abc Street", "Auckland", "New Zealand", 1111);
		User user = new User("userAwesome", "Some", "Awe", address, address);
//		
//		List<Item> it = new ArrayList<Item>();
//		Item i = new Item("Toothbrush", 5);
//		it.add(i);
//		
//		PurchaseDTO dto = new PurchaseDTO(user, it);
//		
//		Client client = ClientBuilder.newClient();
//		Response response = client
//				.target(WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//		
//		
//		if (response.getStatus() != 201) {
//			fail("Failed to create new purchase");
//		}
//		
//
//		String location = response.getLocation().toString();
//		response.close();
//		
	}
	

}
