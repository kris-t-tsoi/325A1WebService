package nz.ac.auckland.services.item;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.userDetail.PersistenceManager;

@ApplicationPath("/services")
public class ItemApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	
	
	   public ItemApplication()
	   {
		  // Register the ParoleeResource singleton to handle HTTP requests.
		  ItemResource resource = new ItemResource();
	      singletons.add(resource);
	      singletons.add(PersistenceManager.instance());
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
	   
	
}
