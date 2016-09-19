package nz.ac.auckland.services.purchase;

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
import nz.ac.auckland.services.user.UserMapper;
import nz.ac.auckland.services.user.UserResource;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

@Path("/purchase")
public class PurchaseResource {
	//Set up Logger
	private static Logger logger = LoggerFactory.getLogger(PurchaseResource.class);
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shoppingPU");
	EntityManager em = entityManagerFactory.createEntityManager();
	
	@POST
	@Consumes("{application/xml}")
	public Response createPurchase(PurchaseDTO dto) {	
		logger.info("Create account with id: " + dto.getId());
		
		em.getTransaction().begin();
		Purchase pur = PurchaseMapper.toDomainModel(dto);
		
		//add purchase to user's purchase history
		logger.info("Add purchase to user with id purchase history: " + pur.getBuyer().getId());
		User user = em.find(User.class, pur.getBuyer().getId());
		user.addPurchase(pur);
		em.persist(pur);
		em.persist(pur);
		em.getTransaction().commit();
		em.close();

		return Response.created(URI.create("/purchase/" + pur.getId()))
				.build();
	}
	

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public PurchaseDTO getPurchase(@PathParam("id") long id) {
		
		em.getTransaction().begin();
		
		Purchase pur = em.find(Purchase.class, id);
		
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
	
	

//	
//	protected Purchase readPurchase(InputStream is) {
//		try {
//			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
//					.newDocumentBuilder();
//			Document doc = builder.parse(is);
//			Element root = doc.getDocumentElement();
//
//			Purchase p = new Purchase();
//			if (root.getAttribute("id") != null
//					&& !root.getAttribute("id").trim().equals(""))
//				p.setId(Integer.valueOf(root.getAttribute("id")));
//			NodeList nodes = root.getChildNodes();
//			for (int i = 0; i < nodes.getLength(); i++) {
//				Element element = (Element) nodes.item(i);
//				if (element.getTagName().equals("buyer")) {
//					User use = new User();
//					use.setId(Integer.valueOf(element.getAttribute("id")));
//					p.setBuyer(use);
//				}else if (element.getTagName().equals("items")) {
//					NodeList subnodes = element.getChildNodes();//item objects
//					for(int j=0;j<subnodes.getLength();j++){
//						Element current = (Element) subnodes.item(j);//item object
//////						if (current.getAttribute("id") != null
//////								&& !current.getAttribute("id").trim().equals("")){	
//////							NodeList subsubNode = current.getChildNodes();
//////							for(int k=0; k<subsubNode.getLength();k++){
//////								if (element.getTagName().equals("buyer")) {
//////									User use = new User();
//////									use.setId(Integer.valueOf(element.getAttribute("id")));
//////									p.setBuyer(use);
//////								}
////							}
//							
//							Item it = new Item();
//							it.setId(Integer.valueOf(current.getAttribute("id")));							
//					}				
//				} else if (element.getTagName().equals("total_cost")) {
//					p.setTotalCost(Double.valueOf(element.getTextContent()));
//				} 
//
//			}
//			return p;
//		} catch (Exception e) {
//			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
//		}
//	}
//	
//	
}
