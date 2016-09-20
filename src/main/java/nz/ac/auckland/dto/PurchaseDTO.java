package nz.ac.auckland.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.userDetail.User;

@XmlRootElement(name="purchases")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseDTO {
	
	@XmlAttribute(name="id")
	private long id;
	
	@XmlElement(name="buyer")
	private User buyer;
	
	@XmlElement(name="items")
	private Collection<ItemDTO> items = new ArrayList<ItemDTO>();
	
	

	@XmlElement(name="total_cost")
	private double totalCost;

	protected PurchaseDTO(){}
	
	
	public PurchaseDTO(User buyer, Collection<ItemDTO> items) {
		this(0,buyer,items);
	}
	
	public PurchaseDTO (long id, User buyer, Collection<ItemDTO> items) {
		this.id = id;
		this.buyer = buyer;
		this.items = items;
		
		double cost = 0;		
		for(ItemDTO i :items){
			cost = cost + i.getPrice();
		}		
		this.totalCost = cost;
	}
	
	public void setItems(Collection<ItemDTO> items) {
		this.items = items;
	}
	public long getId() {
		return id;
	}

	public User getBuyer() {
		return buyer;
	}

	public Collection<ItemDTO> getItems() {
		return items;
	}

	public double getTotalCost() {
		return totalCost;
	}
	
	public void addItem(ItemDTO i){
		items.add(i);
	}
	
	public void removeItem(ItemDTO i){
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
