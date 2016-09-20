package nz.ac.auckland.services;

import java.io.InputStream;
import java.net.URI;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

@Path("/purchase")
public class PurchaseResource {
	//Set up Logger
	private static Logger logger = LoggerFactory.getLogger(PurchaseResource.class);
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shoppingPU");
	
	
	@POST
	@Consumes("{application/xml}")
	public Response createPurchase(PurchaseDTO dto) {
		EntityManager em = entityManagerFactory.createEntityManager();		
		em.getTransaction().begin();

		logger.info("Create purchase: " + dto);
		
		Purchase pur = PurchaseMapper.toDomainModel(dto);
		logger.debug("Create purchase: " + pur);
		em.persist(pur);
		
		
		//add purchase to user's purchase history
		logger.info("Add purchase to user with id purchase history: " + pur.getBuyer().getId());
		User user = em.find(User.class, pur.getBuyer().getId());
		user.addPurchase(pur);
			
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		return Response.created(URI.create("/purchase/" + pur.getId()))
				.build();
	}

	

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public PurchaseDTO getPurchase(@PathParam("id") int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		Purchase pur = em.find(Purchase.class, (long)id);
		logger.debug("Read purchase: " + pur);
		
		if(pur == null){
			// Return a HTTP 404 response if the specified Parolee isn't found.
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		PurchaseDTO dto = PurchaseMapper.toDTO(pur);		
		em.getTransaction().commit();
		em.close();
		
		return dto;		
	}
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public UserDTO getPurchaseOwner(@PathParam("id") long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		Purchase pur = em.find(Purchase.class, id);
		
		if(pur == null){
			// Return a HTTP 404 response if the specified Parolee isn't found.
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		User user = pur.getBuyer();
		UserDTO dto = UserMapper.toDTO(user);
		em.getTransaction().commit();
		em.close();
		
		return dto;		
	}
	

}
