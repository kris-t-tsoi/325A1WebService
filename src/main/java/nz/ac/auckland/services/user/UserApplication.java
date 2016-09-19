package nz.ac.auckland.services.user;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.services.purchase.PurchaseResource;
import nz.ac.auckland.userDetail.PersistenceManager;

@ApplicationPath("/services")
public class UserApplication extends Application{
	
	private Set<Object> singletons = new HashSet<Object>();
	
	
	   public UserApplication()
	   {
		  // Register the ParoleeResource singleton to handle HTTP requests.
		  UserResource resource = new UserResource();
	      singletons.add(resource);
	      singletons.add(new PurchaseResource());
	      singletons.add(PersistenceManager.instance());
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
	   
	

}
