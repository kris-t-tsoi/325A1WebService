package nz.ac.auckland.account;
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
	
	private double balance;
	private ForeignCurrency currency;
	
	private Set<User> accountOwners;
	
	public Account(double intialAmount, User owner) {
		//initalise Acccount Owner Set and add first owner
		accountOwners = new HashSet<User>();
		accountOwners.add(owner);
		
		//add intial amount into account
		balance = intialAmount;		
	}
	
	/**
	 * @return balance of the account
	 */
	double checkBalance(){
		return balance;
	}
	
	/**
	 * @return if account balance is in deficit
	 */
	boolean isOverDraft(){
		return (balance<0);
	}
	
	

}
