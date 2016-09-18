package nz.ac.auckland.purchaseItems;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement(name="item")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="ITEMS")
public class Item {

	@XmlID
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ITEM_ID")
	private int id;	
	
	@XmlElement(name="item_name")
	@Column(name="ITEM_NAME", nullable=false, length=20)
	private String name;
	
	@XmlElement(name="price")
	@Column(name="PRICE", nullable=false)
	private double price;
	
	@XmlElement(name="categories")
	@ManyToMany(mappedBy = "items")
	private Set<Category> categories = new HashSet<Category>();
	

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
