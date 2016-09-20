package nz.ac.auckland.tests.userDetail;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.AddressDTO;
import nz.ac.auckland.dto.UserDTO;

public class UserTest {
	
	private static final String PURCHASE_WEB_SERVICE_URI = "http://localhost:10000/services/purchase";
	private static final String USER_WEB_SERVICE_URI = "http://localhost:10000/services/user";
	private static final String ITEM_WEB_SERVICE_URI = "http://localhost:10000/services/item";
	private static final String CATEGORY_WEB_SERVICE_URI = "http://localhost:10000/services/category";
	
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);	

	
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
	
	
	
	
	@Test
	public void CookieGetUserTest() {	
		Client client = ClientBuilder.newClient();
		
		AddressDTO address = new AddressDTO("52 fudge Street", "Hong Kong", "Hong Kong", 6666);
		UserDTO dto = new UserDTO("OhMyGosh", "Gosh", "Ohmy", address, address);
		
		logger.info("Open connection to write user");
		
		Response response = client
				.target(USER_WEB_SERVICE_URI).request()
				.post(Entity.xml(dto));


		if (response.getStatus() != 201) {
			response.close();
			fail("Failed to create new User");
		}
		
		String location = response.getLocation().toString();
		logger.debug("location: "+location);
		response.close();
				

		// Check for new user
		UserDTO fromService = client.target(location).request()
				.accept("application/xml").get(UserDTO.class);
		
		//get Cookie for user			
		Response cookieResponse = client.target(location + "/cookie").request().get();
		
		assertEquals( 200,cookieResponse.getStatus());

	}
	
	
	
	
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
//		// Check for 
//		ItemDTO FromService = client.target(location).request()
//				.accept("application/xml").get(ItemDTO.class);
//
//		// Check 
//		assertEquals(dto.getName(), FromService.getName());
//		assertEquals(dto.getPrice(), FromService.getPrice(),0);
//	}
//	
//	
//	
//	@Test
//	public void GetItemsWithinPriceTest() {	
//		Client client = ClientBuilder.newClient();
//		
//		CategoryDTO cat = new CategoryDTO("Garden");
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
//		//add spade,30		shovel,35		tractor,130
//		logger.debug("Open connection to write 3 items");
//		ItemDTO dto = new ItemDTO("spade",30,set);
//		Response response = client
//				.target(ITEM_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto));
//
//
//		if (response.getStatus() != 201) {
//			response.close();
//			fail("Failed to create new item");
//		}
//		response.close();
//		
//		ItemDTO dto1 = new ItemDTO("shovel",35,set);
//		Response response1 = client
//				.target(ITEM_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto1));
//
//
//		if (response1.getStatus() != 201) {
//			response1.close();
//			fail("Failed to create new item");
//		}
//		response1.close();
//		
//		ItemDTO dto2 = new ItemDTO("tractor",130,set);
//		Response response2 = client
//				.target(ITEM_WEB_SERVICE_URI).request()
//				.post(Entity.xml(dto2));
//
//
//		if (response2.getStatus() != 201) {
//			response2.close();
//			fail("Failed to create new item");
//		}
//		response2.close();
//
//		String location = response.getLocation().toString();
//		logger.debug("location: "+location);
//		response.close();
//
//		List<ItemDTO> items = client
//				.target(ITEM_WEB_SERVICE_URI+"?from=29&to=50").request()
//				.accept("application/xml").get(new GenericType<List<ItemDTO>>(){});
//
//		for(int i=0; i<items.size();i++){
//			if(i==0){
//				assertEquals(items.get(i).getName(),dto.getName());
//				assertEquals(items.get(i).getPrice(),dto.getPrice(),1);
//			}else{
//				assertEquals(items.get(i).getName(),dto1.getName());
//				assertEquals(items.get(i).getPrice(),dto1.getPrice(),1);//delta 1 as was chaged to int
//			}
//		}
//		
//	}
//	
//	
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
//		String userlocation = userResponse.getLocation().toString();
//		userResponse.close();
//				
//		UserDTO userdtoFromServer = client.target(userlocation).request()
//				.accept("application/xml").get(UserDTO.class);
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
//		
//
//		String catlocation = catresponse.getLocation().toString();
//		catresponse.close();
//				
//		CategoryDTO catFromServer = client.target(catlocation).request()
//				.accept("application/xml").get(CategoryDTO.class);
//		
//		Set<Category> set = new HashSet<Category>();
//		set.add(CategoryMapper.toDomainModel(catFromServer));
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
////		String itemlocation = itemResponse.getLocation().toString();
//		itemResponse.close();
//		
////		ItemDTO itemFromServer = client.target(itemlocation).request()
////				.accept("application/xml").get(ItemDTO.class);
//		
////		it.add(ItemMapper.toDomainModel(itemFromServer));
//		it.add(i);
//		Purchase pur = new Purchase(UserMapper.toDomainModel(userdtoFromServer), it);
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
//		for(ItemDTO itm: FromService.getItems()){
//			assertEquals(itm.getName(),itdto.getName());
//			assertEquals(itm.getPrice(),itdto.getPrice(),1);
//	}
//		//Check that the owner is correct
//		UserDTO userfromPurchase = client.target(location+"/user").request()
//				.accept("application/xml").get(UserDTO.class);
//		assertEquals(userdtoFromServer.getId(),userfromPurchase.getId());		
//
//		
//	}
	
	
	
	
//	
//	@Test
//	public void testAddItemToPurchase(){
//		
//	}
	

}
