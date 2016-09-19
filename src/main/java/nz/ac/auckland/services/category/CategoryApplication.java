package nz.ac.auckland.services.category;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.services.item.ItemResource;
import nz.ac.auckland.userDetail.PersistenceManager;

@ApplicationPath("/services")
public class CategoryApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	
	
	   public CategoryApplication()
	   {
		  // Register the ParoleeResource singleton to handle HTTP requests.
		  CategoryResource resource = new CategoryResource();
	      singletons.add(resource);
	      singletons.add(PersistenceManager.instance());
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
	   
}
