package nz.ac.auckland.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.purchaseItems.Item;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemDTO {

	@XmlID
	private String id;	
	
	@XmlElement(name="item_name")
	private String name;
	
	@XmlElement(name="price")
	private double price;
	
	@XmlElement(name="categories")
	private Set<Category> categories = new HashSet<Category>();
	
	public ItemDTO(String name, double price){
		this(0,name,price);
	}
	
	public ItemDTO(long id, String name, double price){
		this.id = Long.toString(id);
		this.name = name;
		this.price = price;
	}
	
	public String getId() {
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
