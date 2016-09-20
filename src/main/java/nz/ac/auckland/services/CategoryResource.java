package nz.ac.auckland.services;

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

import nz.ac.auckland.dto.CategoryDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

@Path("/category")
public class CategoryResource {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shoppingPU");
	
	
	@POST
	@Consumes("application/xml")
	public Response createCategory(CategoryDTO dto) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Category cat = CategoryMapper.toDomainModel(dto);
		em.persist(cat);
		em.getTransaction().commit();
		em.close();

		return Response.created(URI.create("/category/" + cat.getId()))
				.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/xml")
	public CategoryDTO getCategory(@PathParam("id") long id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		Category cat = em.find(Category.class,(long) id);
		
		if(cat == null){
			// Return a HTTP 404 response if the specified Parolee isn't found.
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		CategoryDTO dto =CategoryMapper.toDTO(cat);		
		em.getTransaction().commit();
		em.close();
		
		return dto;		
	}

	
	
}
