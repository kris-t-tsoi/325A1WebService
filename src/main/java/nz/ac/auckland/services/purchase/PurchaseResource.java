package nz.ac.auckland.services.purchase;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

@Path("purchase")
public class PurchaseResource {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shoppingPU");
	EntityManager em = entityManagerFactory.createEntityManager();
	
	@POST
	@Consumes("{application/xml}")
	public Response createPurchase(User use) {		
		Address address = new Address("12 abc Street", "Auckland", "New Zealand", 1111);
		User awesome = new User("userAwesome", "Some", "Awe", address, address, null);
		em.getTransaction().begin();
		em.persist(awesome);
		em.getTransaction().commit();
		em.close();

		return Response.created(URI.create("/user/" + use.getId()))
				.build();
	}
	
	
	
	
}
