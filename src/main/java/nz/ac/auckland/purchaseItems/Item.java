package nz.ac.auckland.purchaseItems;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Entity
@Table(name="ITEMS")
public class Item {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ITEM_ID")
	private long id;	
	

	@Column(name="ITEM_NAME", nullable=false, length=20)
	private String name;
	
	@Column(name="PRICE", nullable=false)
	private double price;
	
	@OneToMany
	private Set<Category> categories = new HashSet<Category>();
	
	public Item() {}
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public void setId(long id) {
		this.id = id;
	}
	public Item(long id,String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
