package nz.ac.auckland.banking.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountTest {
	
	
	@Test
	public void testCheckAccountBalance(){
		Client _client = ClientBuilder.newClient();
		
		Client client = ClientBuilder.newClient();
//		Response response = client.target(WEB_SERVICE_URI + "/1").request().get();

	
	}
	

}
