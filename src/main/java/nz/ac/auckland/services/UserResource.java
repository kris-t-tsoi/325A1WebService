package nz.ac.auckland.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import nz.ac.auckland.userDetail.User;

/**
 * Class to implement a simple REST Web service for managing accounts.
 * 
 * UserResource implements a WEB service with the following interface:
 * - GET    <base-uri>/user/{id}
 *          Retrieves a user based on their unique id. The format of the 
 *          returned data is XML.
 *          
 * - GET    <base-uri>/user?start&size
 *          Retrieves a collection of user, where the "start" query 
 *          parameter identifies an index position, and "size" represents the 
 *          max number of successive user to return. The format of the 
 *          returned data is XML.
 *          
 * - POST   <base-uri>/user
 *          Creates a new user. The HTTP post message contains an XML 
 *          representation of the user to be created.
 *          
 * - PUT    <base-uri>/user/{id}
 *          Updates a user, identified by their id.The HTTP PUT message
 *          contains an XML document describing the new state of the user.
 *          
 * - DELETE <base-uri>/user/{id}
 *          Deletes a user, identified by their unique id.
 * 
 * @author Ian Warren
 *
 */

@Path("/user")
public class UserResource {
	
	//Set up Logger
	private static Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	// Thread-safe data structure. This is necessary because a single
	// ParoleeResource instance will be created and used to handle all incoming
	// requests. The JAX-RS implementation uses a thread-per-request model and
	// so concurrent requests will concurrently access the ParoleeResource 
	// object.
	private Map<Integer, User> userDB = new ConcurrentHashMap<Integer, User>();
	private AtomicInteger _idCounter = new AtomicInteger();
	
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public StreamingOutput retrieveUserNames(@PathParam("id") int id) {
		logger.info("Retrieving account with id: " + id);
		final User user = userDB.get(id);
		if(user == null){
			// Return a HTTP 404 response if the specified Parolee isn't found.
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		// Return a StreamingOuput instance that the JAX-RS implementation will
				// use to set the body of the HTTP response message.
				return new StreamingOutput() {
					public void write(OutputStream outputStream) throws IOException,
							WebApplicationException {
						outputUserNames(outputStream, user);
					}
				};
	}

	
	
	/**
	 * Helper method to generate an XML representation of a particular user.
	 * 
	 * @param os the OutputStream used to write out the XML.
	 * 
	 * @param parolee the user for which to generate an XML representation.
	 * 
	 * @throws IOException if an error is encountered in writing the XML to the 
	 * OutputStream.
	 */
	protected void outputUserNames(OutputStream os, User user)
			throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<parolee id=\"" + user.getId() + "\">");
		writer.println("   <user-name>" + user.getUserName()
		+ "</user-name>");
		writer.println("   <first-name>" + user.getFirstName()
				+ "</first-name>");
		writer.println("   <last-name>" + user.getLastName()
				+ "</last-name>");
		writer.println("   <last-name>" + user.getLastName()
		+ "</last-name>");
		writer.println("</parolee>");
	}
	
	/**
	 * Helper method to read an XML representation of a user, and return a
	 * corresponding user object. 
	 * 
	 * @param is the InputStream containing an XML representation of the 
	 *        user to create.
	 *        
	 * @return a new user object.
	 */
	protected User readUser(InputStream is) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();

			User user = new User();
			if (root.getAttribute("id") != null
					&& !root.getAttribute("id").trim().equals(""))
				user.setId(Integer.valueOf(root.getAttribute("id")));
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("user-name")) {
					user.setUserName(element.getTextContent());
				} else if (element.getTagName().equals("first-name")) {
					user.setFirstName(element.getTextContent());
				} else if (element.getTagName().equals("last-name")) {
					user.setLastName(element.getTextContent());
				} 
			}
			return user;
		} catch (Exception e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
	}
	
	/**
	 * Creates a new user.
	 * 
	 * @param is the Inputstream that contains an XML representation of the
	 * user to be created.
	 * 
	 * @return a Response object that includes the HTTP "Location" header,
	 *         whose value is the URI of the newly created resource. The HTTP 
	 *         response code is 201. The JAX-RS run-time processes the Response
	 *         object when preparing the HTTP response message.
	 */
	@POST
	@Produces("application/xml")
	public Response createUser(InputStream is) {
		User user = readUser(is);

		user.setId(_idCounter.incrementAndGet());
		userDB.put(user.getId(), user);

		logger.debug("Created parolee with id: " + user.getId());

		return Response.created(URI.create("/parolees/" + user.getId()))
				.build();
	}

	/**
	 * Attempts to update an existing user. If the specified user is
	 * found it is updated, resulting in a HTTP 204 response being returned to 
	 * the consumer. In other cases, a 404 response is returned.
	 * 
	 * @param id the unique id of the user to update.
	 * 
	 * @param is the InputStream used to store an XML representation of the
	 * new state for the user.
	 */
	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateUser(@PathParam("id") int id, InputStream is) {
		User update = readUser(is);
		User current = userDB.get(id);
		if (current == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		// Update the details of the Parolee to be updated.
		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setUserName(update.getUserName());
	}

	/**
	 * Attempts to delete an existing parolee. If the specified parolee isn't 
	 * found, a 404 response is returned to the consumer. In other cases, a 204
	 * response is returned after deleting the parolee.
	 * 
	 * @param id the unique id of the parolee to delete.
	 */
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") int id) {
		User current = userDB.get(id);
		if (current == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		// Remove the Parolee.
		userDB.remove(id);
		logger.info("Deleted parolee with ID: " + id);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


//		/**
//		 * Attempts to retrieve a collection of parolees. 
//		 * 
//		 * @param start the start index within the database of parolees.
//		 * 
//		 * @param size the number of parolees to return.
//		 * 
//		 * @return a StreamingOutput object that writes out up to a maximum of size 
//		 *         parolees in XML form.  
//		 */
//		@GET
//		@Produces("application/xml")
//		public StreamingOutput retrieveParolees(@QueryParam("start") int start, @QueryParam("size") int size) {
//			final List<Parolee> parolees = new ArrayList<Parolee>();
//
//			for (int i = start; i <= size; i++) {
//				Parolee parolee = _paroleeDB.get(i);
//				if (parolee != null) {
//					parolees.add(parolee);
//				}
//			}
//
//			return new StreamingOutput() {
//				public void write(OutputStream outputStream) throws IOException,
//						WebApplicationException {
//					outputParolees(outputStream, parolees);
//				}
//			};
//		}
//
//		/**
//		 * Creates a new parolee.
//		 * 
//		 * @param is the Inputstream that contains an XML representation of the
//		 * parolee to be created.
//		 * 
//		 * @return a Response object that includes the HTTP "Location" header,
//		 *         whose value is the URI of the newly created resource. The HTTP 
//		 *         response code is 201. The JAX-RS run-time processes the Response
//		 *         object when preparing the HTTP response message.
//		 */
//		@POST
//		@Produces("application/xml")
//		public Response createParolee(InputStream is) {
//			// Read an XML representation of a new Parolee. Note that with JAX-RS, 
//			// any non-annotated parameter in a Resource method is assumed to hold 
//			// the HTTP request's message body.
//			Parolee parolee = readParolee(is);
//
//			// Generate an ID for the new Parolee, and store it in memory.
//			parolee.setId(_idCounter.incrementAndGet());
//			_paroleeDB.put(parolee.getId(), parolee);
//
//			_logger.debug("Created parolee with id: " + parolee.getId());
//
//			return Response.created(URI.create("/parolees/" + parolee.getId()))
//					.build();
//		}
//
//		/**
//		 * Attempts to update an existing parolee. If the specified parolee is
//		 * found it is updated, resulting in a HTTP 204 response being returned to 
//		 * the consumer. In other cases, a 404 response is returned.
//		 * 
//		 * @param id the unique id of the parolee to update.
//		 * 
//		 * @param is the InputStream used to store an XML representation of the
//		 * new state for the parolee.
//		 */
//		@PUT
//		@Path("{id}")
//		@Consumes("application/xml")
//		public void updateParolee(@PathParam("id") int id, InputStream is) {
//			Parolee update = readParolee(is);
//			Parolee current = _paroleeDB.get(id);
//			if (current == null) {
//				throw new WebApplicationException(Response.Status.NOT_FOUND);
//			}
//
//			// Update the details of the Parolee to be updated.
//			current.setFirstname(update.getFirstname());
//			current.setLastname(update.getLastname());
//			current.setGender(update.getGender());
//			current.setDateOfBirth(update.getDateOfBirth());
//		}
//
//		/**
//		 * Attempts to delete an existing parolee. If the specified parolee isn't 
//		 * found, a 404 response is returned to the consumer. In other cases, a 204
//		 * response is returned after deleting the parolee.
//		 * 
//		 * @param id the unique id of the parolee to delete.
//		 */
//		@DELETE
//		@Path("{id}")
//		public void deleteParolee(@PathParam("id") int id) {
//			Parolee current = _paroleeDB.get(id);
//			if (current == null) {
//				throw new WebApplicationException(Response.Status.NOT_FOUND);
//			}
//
//			// Remove the Parolee.
//			_paroleeDB.remove(id);
//			_logger.info("Deleted parolee with ID: " + id);
//		}
//
//		/**
//		 * Helper method to generate an XML representation of a particular parolee.
//		 * 
//		 * @param os the OutputStream used to write out the XML.
//		 * 
//		 * @param parolee the parolee for which to generate an XML representation.
//		 * 
//		 * @throws IOException if an error is encountered in writing the XML to the 
//		 * OutputStream.
//		 */
//		protected void outputParolee(OutputStream os, Parolee parolee)
//				throws IOException {
//			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
//			String dateOfBirth = formatter.print(parolee.getDateOfBirth());
//
//			PrintStream writer = new PrintStream(os);
//			writer.println("<parolee id=\"" + parolee.getId() + "\">");
//			writer.println("   <first-name>" + parolee.getFirstname()
//					+ "</first-name>");
//			writer.println("   <last-name>" + parolee.getLastname()
//					+ "</last-name>");
//			writer.println("   <gender>" + parolee.getGender() + "</gender>");
//			writer.println("   <date-of-birth>" + dateOfBirth + "</date-of-birth>");
//			writer.println("</parolee>");
//		}
//
//		/**
//		 * Helper method to generate an XML representation for a collection of 
//		 * parolees.
//		 */
//		protected void outputParolees(OutputStream os, List<Parolee> parolees)
//				throws IOException {
//			for (Parolee parolee : parolees) {
//				outputParolee(os, parolee);
//			}
//		}
//
//		/**
//		 * Helper method to read an XML representation of a Parolee, and return a
//		 * corresponding Parolee object. This method uses the org.w3c API for 
//		 * parsing XML. The details aren't important, and shortly we'll use an
//		 * automated approach rather than having to do this by hand. Currently this
//		 * is a minimal Web service and so we'll parse the XML by hand.
//		 * 
//		 * @param is the InputStream containing an XML representation of the 
//		 *        parolee to create.
//		 *        
//		 * @return a new Parolee object.
//		 */
//		protected Parolee readParolee(InputStream is) {
//			try {
//				DocumentBuilder builder = DocumentBuilderFactory.newInstance()
//						.newDocumentBuilder();
//				Document doc = builder.parse(is);
//				Element root = doc.getDocumentElement();
//
//				Parolee parolee = new Parolee();
//				if (root.getAttribute("id") != null
//						&& !root.getAttribute("id").trim().equals(""))
//					parolee.setId(Integer.valueOf(root.getAttribute("id")));
//				NodeList nodes = root.getChildNodes();
//				for (int i = 0; i < nodes.getLength(); i++) {
//					Element element = (Element) nodes.item(i);
//					if (element.getTagName().equals("first-name")) {
//						parolee.setFirstname(element.getTextContent());
//					} else if (element.getTagName().equals("last-name")) {
//						parolee.setLastname(element.getTextContent());
//					} else if (element.getTagName().equals("gender")) {
//						parolee.setGender(Gender.fromString(element
//								.getTextContent()));
//					} else if (element.getTagName().equals("date-of-birth")) {
//						DateTimeFormatter formatter = DateTimeFormat
//								.forPattern("dd/MM/yyyy");
//						DateTime dateOfBirth = formatter.parseDateTime(element
//								.getTextContent());
//						parolee.setDateOfBirth(dateOfBirth);
//					}
//				}
//				return parolee;
//			} catch (Exception e) {
//				throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
//			}
//		}

}
