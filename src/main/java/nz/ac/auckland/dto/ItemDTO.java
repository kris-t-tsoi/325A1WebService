package nz.ac.auckland.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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

	@XmlAttribute(name="id")
	private long id;	
	
	@XmlElement(name="item_name")
	private String name;
	
	@XmlElement(name="price")
	private double price;
	
	@XmlElement(name="categories")
	private Set<CategoryDTO> categories = new HashSet<CategoryDTO>();
	
	protected ItemDTO(){}
	
	public ItemDTO(String name, double price,Set<CategoryDTO> cat){
		this(0,name,price,cat);
	}
	
	public ItemDTO(long id, String name, double price,Set<CategoryDTO> cat){
		this.id = id;
		this.name = name;
		this.price = price;
//		this.categories = cat;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}	
	public Set<CategoryDTO> getCategories() {
		return categories;
	}
	
	public void addCategory(CategoryDTO category){
		categories.add(category);
	}
	
	public void removeCategory (CategoryDTO category){
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
