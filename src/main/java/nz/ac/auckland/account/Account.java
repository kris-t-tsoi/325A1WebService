package nz.ac.auckland.account;
import javax.xml.bind.annotation.*;

import nz.ac.auckland.userDetail.User;

import java.awt.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.ws.rs.GET;

@Entity
@Table(name="Accounts")
public class Account {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int accountNumber;
	
	private double balance;
	private String bankName;
	
	@ManyToMany(mappedBy="userId", cascade = CascadeType.PERSIST)
//	@JoinColumns
	private Set<User> accountOwners;
	
//	private List<>
	
	public Account(double intialAmount, User owner) {
		//initalise Acccount Owner Set and add first owner
		accountOwners = new HashSet<User>();
		accountOwners.add(owner);
		
		//add intial amount into account
		balance = intialAmount;		
	}
	
	
	
	double getBalance(){
		return balance;
	}
	
	public Set<User> getAccountOwners() {
		return accountOwners;
	}

	public void setAccountOwners(Set<User> accountOwners) {
		this.accountOwners = accountOwners;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return if account balance is in deficit
	 */
	boolean isOverDraft(){
		return (balance<0);
	}
	
	

}
