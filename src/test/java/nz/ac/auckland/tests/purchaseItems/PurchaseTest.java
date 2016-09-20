package nz.ac.auckland.tests.purchaseItems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.services.ItemMapper;
import nz.ac.auckland.services.UserMapper;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.User;

public class PurchaseTest {
	
	
	

}
