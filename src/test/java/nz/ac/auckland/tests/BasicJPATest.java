package nz.ac.auckland.tests;
import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicJPATest {
//
//	
//	private static Logger _logger = LoggerFactory.getLogger(DomainTest.class);
//
//	// JPA EntityManagerFactory, used to create an EntityManager.
//	protected static EntityManagerFactory _factory = null;
//	
//	// JPA EntityManager, which provides transactional and persistence 
//	// operations.
//	protected EntityManager _entityManager = null;
//
//	/**
//	 * One-time setup method for all test cases.
//	 * 
//	 * Initialises the database by dropping any existing tables prior to 
//	 * creating a JPA EntityManagerFactory. When the JPA EntityManagerFactory
//	 * is created, it extracts metadata from domain model classes and creates 
//	 * the necessary database tables.
//	 * 
//	 * @throws ClassNotFoundException
//	 * @throws SQLException
//	 */
//	@BeforeClass
//	public static void initialiseDatabase() throws ClassNotFoundException,
//			SQLException {
//		// Open a connection to the database. 
//		DatabaseUtility.openDatabase();
//
//		// Drop any existing tables.
//		DatabaseUtility.clearDatabase(true);
//		
//		// Create the JPA EntityManagerFactory.
//		_factory = Persistence.createEntityManagerFactory("auctionPU");
//	}
//
//	/**
//	 * One-time finalisation method for all test cases. This method releases 
//	 * the JDBC database connection.
//	 * 
//	 * @throws SQLException
//	 */
//	@AfterClass
//	public static void releaseEntityManager() throws SQLException {
//		DatabaseUtility.closeDatabase();
//	}
//
//	/**
//	 * Immediately before each test cases runs, this method runs to remove any 
//	 * rows in database tables. This ensures that each test begins with an 
//	 * empty database. In addition it creates a new entityManager for each test.
//	 * 
//	 * @throws SQLException
//	 */
//	@Before
//	public void clearDatabase() throws SQLException {
//		// Delete all rows from any existing database tables.
//		DatabaseUtility.clearDatabase(false);
//		
//		// Create the JPA EntityManager.
//		_entityManager = _factory.createEntityManager();
//	}
//	
//	/**
//	 * Immediately after each test case runs, this method releases the JPA
//	 * EntityManager used by the test. In addition, this method outputs (via 
//	 * the configured logger) the contents of the database tables.
//	 */
//	@After
//	public void closeEntityManager() throws SQLException {
//		DatabaseUtility.dumpDatabase(null);
//		
//		if(_entityManager.isOpen()) {
//			_entityManager.close();
//		}
//	}

}
