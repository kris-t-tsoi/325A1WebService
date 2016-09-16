package nz.ac.auckland.purchaseItems;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

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
	@JoinColumn(name = "USER_ID", nullable = false)
	private long buyerID;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Collection<Item> items = new ArrayList<Item>();
	
	private double totalAmount;
	
	
	public Purchase() {}
	
	public Purchase(User buyer, ArrayList<Item> items) {
		this.buyerID = buyer.getId();
		this.items = items;
		
		double cost = 0;		
		for(Item i :items){
			cost = cost + i.getPrice();
		}		
		this.totalAmount = cost;
	}
	
	
	public long getId() {
		return id;
	}

	public long getBuyerID() {
		return buyerID;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public double getTotalAmount() {
		return totalAmount;
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
            append(buyerID, rhs.getBuyerID()).
            isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). 
				append(getClass().getName()).
	            append(id).
	            append(items).
	            append(buyerID).
	            toHashCode();
	}



}
