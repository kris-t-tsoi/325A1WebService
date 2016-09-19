package nz.ac.auckland.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import nz.ac.auckland.userDetail.PersistenceManager;

public class PurchaseApplication  extends Application{

	private Set<Object> singletons = new HashSet<Object>();
	
	
	   public PurchaseApplication()
	   {
		  // Register the ParoleeResource singleton to handle HTTP requests.
		  PurchaseResource resource = new PurchaseResource();
	      singletons.add(resource);
	      singletons.add(PersistenceManager.instance());
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;
	   }
	   
}
