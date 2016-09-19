package nz.ac.auckland.services.item;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.purchaseItems.Item;

@Path("item")
public class ItemResource {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shoppingPU");
	EntityManager em = entityManagerFactory.createEntityManager();
	
	@POST
	@Consumes("{application/xml}")
	public Response createItem(Item it) {		
		em.getTransaction().begin();
		
		em.persist(it);
		em.getTransaction().commit();
		em.close();

		return Response.created(URI.create("/purchase/" + it.getId()))
				.build();
	}
	

	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public ItemDTO getItem(@PathParam("id") int id) {
		
		em.getTransaction().begin();
		
		Item it = em.find(Item.class, id);
		
		if(it == null){
			// Return a HTTP 404 response if the specified Parolee isn't found.
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		ItemDTO dto = ItemMapper.toDTO(it);		
		em.getTransaction().commit();
		em.close();
		
		return dto;		
	}
	
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public List<Item> getAllItem(@PathParam("id") int id) {
		em.getTransaction().begin();
		
		List<Item> items  = em.createQuery("select i from Item i").getResultList( );
		
		if(items.isEmpty()|| items == null){
			return null;
		}
		
		em.getTransaction().commit();
		em.close();
		
		return items;
	}
	
	
	
	//get items within price range of
	
	
	
	
	//get items with name
	
	
	//get items in category
	
	
	
	
	
	
	
	
	
	

		
}
