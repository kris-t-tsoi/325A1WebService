package nz.ac.auckland.purchaseItems;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.userDetail.User;


@Entity
@Table(name = "PURCHASES")
public class Purchase {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="PURCHASE_ID")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User buyer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Collection<Item> items = new ArrayList<Item>();
	
	@Column(name="TOTAL_COST")
	private double totalCost;
	
	
	public Purchase() {}
	
	public Purchase(long id,User buyer, Collection<Item> collection) {
		this.id = id;
		this.buyer = buyer;
		this.items = collection;
		
		double cost = 0;		
		for(Item i :collection){
			cost = cost + i.getPrice();
		}		
		this.totalCost = cost;
	}
	
	public Purchase(User buyer, Collection<Item> items) {
		this.buyer = buyer;
		this.items = items;
		
		double cost = 0;		
		for(Item i :items){
			cost = cost + i.getPrice();
		}		
		this.totalCost = cost;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public double getTotalCost() {
		return totalCost;
	}
	
	public void addItem(Item i){
		items.add(i);
	}
	
	public void removeItem(Item i){
		items.remove(i);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Purchase))
            return false;
        if (obj == this)
            return true;

        Purchase rhs = (Purchase) obj;
        return new EqualsBuilder().
            append(id, rhs.getId()).
            append(items, rhs.getItems()).
            append(buyer, rhs.getBuyer()).
            isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). 
				append(getClass().getName()).
	            append(id).
	            append(items).
	            append(buyer).
	            toHashCode();
	}



}
