package nz.ac.auckland.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.userDetail.PersistenceManager;

@ApplicationPath("/services")
public class UserApplication  extends Application{

	private Set<Object> singletons = new HashSet<Object>();
	
	
	   public UserApplication()
	   {
		  // Register Resource singleton to handle HTTP requests.
		  UserResource ur = new UserResource();
		  PurchaseResource pr = new PurchaseResource();
		  ItemResource ir = new ItemResource();
		  CategoryResource cr = new CategoryResource();
		  singletons.add(ur);	 
	      singletons.add(pr);
	      singletons.add(ir);
	      singletons.add(cr);
	      singletons.add(PersistenceManager.instance());
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
	   
}
