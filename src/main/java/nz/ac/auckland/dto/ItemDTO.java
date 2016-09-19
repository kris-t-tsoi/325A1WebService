package nz.ac.auckland.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.userDetail.Address;
import nz.ac.auckland.userDetail.CreditCardDetails;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDTO {

	@XmlID
	private int id;	
	
	@XmlElement(name="item_name")
	private String name;
	
	@XmlElement(name="price")
	private double price;
	
	@XmlElement(name="categories")
	private Set<Category> categories = new HashSet<Category>();
	
	public ItemDTO(String name, double price){
		this(0,name,price);
	}
	
	public ItemDTO(int id, String name, double price){
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}	
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}
	
	public void addCategory(Category category){
		categories.add(category);
	}
	
	public void removeCategory (Category category){
		categories.remove(category);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item))
            return false;
        if (obj == this)
            return true;

        Item itm = (Item) obj;
        return new EqualsBuilder().
            append(id, itm.getId()).
            append(name, itm.getName()).
            isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). 
				append(getClass().getName()).
	            append(id).
	            append(name).
	            toHashCode();
	}
	
}
