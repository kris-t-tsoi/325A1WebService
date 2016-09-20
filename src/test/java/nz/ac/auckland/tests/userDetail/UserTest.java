package nz.ac.auckland.tests.userDetail;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.AddressDTO;
import nz.ac.auckland.dto.CategoryDTO;
import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.services.CategoryMapper;
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
	private static final String CATEGORY_WEB_SERVICE_URI = "http://localhost:10000/services/category";
	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);	

//	
//	@Test
//	public void CreateUserTest() {	
//		Client client = ClientBuilder.newClient();
//		
//		AddressDTO address = new AddressDTO("12 abc Street", "Auckland", "New Zealand", 1111);
//		UserDTO dto = new UserDTO("userAwesome", "Some", "Awe", address, address);
//		
//		logger.debug("Open connection to write user");
//		
//		Response response = client
//				.target(USER_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//
//
//		if (response.getStatus() != 201) {
//			response.close();
//			fail("Failed to create new User");
//		}
//		
//
//		String location = response.getLocation().toString();
//		logger.debug("location: "+location);
//		response.close();
//				
//
//		// Check for new user
//		UserDTO FromService = client.target(location).request()
//				.accept("application/xml").get(UserDTO.class);
//
//		// Check user details
//		assertEquals(dto.getUserName(), FromService.getUserName());
//		assertEquals(dto.getLastName(), FromService.getLastName());
//		assertEquals(dto.getFirstName(), FromService.getFirstName());
//	}
	
//	
//	@Test
//	public void CreateItemTest() {	
//		Client client = ClientBuilder.newClient();
//		
//		CategoryDTO cat = new CategoryDTO("Bathroom");
//		Response catresponse = client
//				.target(CATEGORY_WEB_SERVICE_URI).request()
//				.post(Entity.xml(cat));
//
//
//		if (catresponse.getStatus() != 201) {
//			catresponse.close();
//			fail("Failed to create new Category");
//		}
//		Set<CategoryDTO> set = new HashSet<CategoryDTO>();
//		set.add(cat);
//		catresponse.close();		
//
//		
//		logger.debug("Open connection to write item");
//		ItemDTO dto = new ItemDTO("cup",8.50,set);
//		Response response = client
//				.target(ITEM_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//
//
//		if (response.getStatus() != 201) {
//			response.close();
//			fail("Failed to create new item");
//		}
//		
//
//		String location = response.getLocation().toString();
//		logger.debug("location: "+location);
//		response.close();
//				
//
//		// Check for new user
//		ItemDTO FromService = client.target(location).request()
//				.accept("application/xml").get(ItemDTO.class);
//
//		// Check user details
//		assertEquals(dto.getName(), FromService.getName());
//		assertEquals(dto.getPrice(), FromService.getPrice(),0);
//	}
//	
	
	
	@Test
	public void GetItemsWithinPriceTest() {	
		Client client = ClientBuilder.newClient();
		
		CategoryDTO cat = new CategoryDTO("Garden");
		Response catresponse = client
				.target(CATEGORY_WEB_SERVICE_URI).request()
				.post(Entity.xml(cat));


		if (catresponse.getStatus() != 201) {
			catresponse.close();
			fail("Failed to create new Category");
		}
		Set<CategoryDTO> set = new HashSet<CategoryDTO>();
		set.add(cat);
		catresponse.close();		

		//add spade,30		shovel,35		tractor,130
		logger.debug("Open connection to write 3 items");
		ItemDTO dto = new ItemDTO("spade",30,set);
		Response response = client
				.target(ITEM_WEB_SERVICE_URI).request()
				.post(Entity.xml(dto));


		if (response.getStatus() != 201) {
			response.close();
			fail("Failed to create new item");
		}
		response.close();
		
		ItemDTO dto1 = new ItemDTO("shovel",35,set);
		Response response1 = client
				.target(ITEM_WEB_SERVICE_URI).request()
				.post(Entity.xml(dto1));


		if (response1.getStatus() != 201) {
			response1.close();
			fail("Failed to create new item");
		}
		response1.close();
		
		ItemDTO dto2 = new ItemDTO("tractor",130,set);
		Response response2 = client
				.target(ITEM_WEB_SERVICE_URI).request()
				.post(Entity.xml(dto2));


		if (response2.getStatus() != 201) {
			response2.close();
			fail("Failed to create new item");
		}
		response2.close();

		String location = response.getLocation().toString();
		logger.debug("location: "+location);
		response.close();

		List<ItemDTO> items = client
				.target(ITEM_WEB_SERVICE_URI+"?from=29&to=50").request()
				.accept("application/xml").get(new GenericType<List<ItemDTO>>(){});

		for(int i=0; i<items.size();i++){
			if(i==0){
				assertEquals(items.get(i).getName(),dto.getName());
				assertEquals(items.get(i).getPrice(),dto.getPrice(),1);
			}else{
				assertEquals(items.get(i).getName(),dto1.getName());
				assertEquals(items.get(i).getPrice(),dto1.getPrice(),1);//delta 1 as was chaged to int
			}
		}
		
	}
	
	
//	
//	
//		
//	@Test
//	public void CreateCateogryTest() {	
//		Client client = ClientBuilder.newClient();
//		
//		CategoryDTO dto = new CategoryDTO("House Hold");
//		
//		logger.debug("Open connection to write category");
//		
//		Response response = client
//				.target(CATEGORY_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//
//
//		if (response.getStatus() != 201) {
//			response.close();
//			fail("Failed to create new Category");
//		}
//		
//
//		String location = response.getLocation().toString();
//		logger.debug("location: "+location);
//		response.close();
//				
//
//		CategoryDTO FromService = client.target(location).request()
//				.accept("application/xml").get(CategoryDTO.class);
//
//		assertEquals(dto.getName(), FromService.getName());
//	}
	
//	
//	
//	
//	@Test
//	public void testSingleItemPurchase(){
//		
//		Client client = ClientBuilder.newClient();
//		
//		AddressDTO address = new AddressDTO("34 def Street", "Auckland", "New Zealand", 1111);
//		UserDTO userdto = new UserDTO("soCool", "Cool", "So", address, address);
//		
//		Response userResponse = client
//				.target(USER_WEB_SERVICE_URI).request()
//				.post(Entity.xml(userdto));
//		
//		if (userResponse.getStatus() != 201) {
//			userResponse.close();
//			fail("Failed to create new user");
//		}
//		
//		userResponse.close();
//		
//		Category cat = new Category("Kitchen");
//		
//		logger.debug("Open connection to write category");
//		
//		Response catresponse = client
//				.target(CATEGORY_WEB_SERVICE_URI).request()
//				.post(Entity.xml(CategoryMapper.toDTO(cat)));
//
//
//		if (catresponse.getStatus() != 201) {
//			catresponse.close();
//			fail("Failed to create new Category");
//		}
//		Set<Category> set = new HashSet<Category>();
//		set.add(cat);
//		catresponse.close();
//		
//		List<Item> it = new ArrayList<Item>();
//		Item i = new Item("Toothbrush", 5,set);
//		ItemDTO itdto = ItemMapper.toDTO(i);
//		
//		Response itemResponse = client
//				.target(ITEM_WEB_SERVICE_URI).request()
//				.post(Entity.xml(itdto));
//		
//		
//		if (itemResponse.getStatus() != 201) {
//			userResponse.close();
//			itemResponse.close();
//			fail("Failed to create new item");
//		}	
//		
//		itemResponse.close();
//		it.add(i);
//		Purchase pur = new Purchase(UserMapper.toDomainModel(userdto), it);
//		PurchaseDTO dto = PurchaseMapper.toDTO(pur);
//		
//		Response response = client
//				.target(PURCHASE_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//		
//				
//		if (response.getStatus() != 201) {
//			fail("Failed to create new purchase");
//		}
//		
//		String location = response.getLocation().toString();
//		response.close();
//		
//
//		// Check entry was created
//		PurchaseDTO FromService = client.target(location).request()
//				.accept("application/xml").get(PurchaseDTO.class);
//		
//		// Check purchase details
//		assertEquals(dto.getBuyer().getId(), FromService.getBuyer().getId());
//		assertEquals(dto.getItems(), FromService.getItems());
////		assertEquals(dto.getLastName(), FromService.getLastName());
//		
//	}
//	
	
	
	
//	
//	@Test
//	public void testAddItemToPurchase(){
//		
//	}
	

}
