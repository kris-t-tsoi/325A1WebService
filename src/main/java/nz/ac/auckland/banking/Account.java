package nz.ac.auckland.banking;
import javax.xml.bind.annotation.*;

import nz.ac.auckland.userDetail.User;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	private int accountNumber;
	
	private double amount;
	private ForeignCurrency currency;
	
	private Set<User> accountOwners;
	
	public Account(double intialAmount, User owner) {
		//initalise Acccount Owner Set and add first owner
		accountOwners = new HashSet<User>();
		accountOwners.add(owner);
		
		//add intial amount into account
		amount = intialAmount;
		
		
	}

}
