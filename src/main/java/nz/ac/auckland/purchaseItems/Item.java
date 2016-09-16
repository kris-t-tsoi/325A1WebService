package nz.ac.auckland.purchaseItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name="ITEMS")
public class Item {

	//	@XmlID
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="ITEM_ID")
	private Long id;	
	
	@Column(name="ITEM_NAME", nullable=false, length=20)
	private String name;
	
	@Column(name="PRICE", nullable=false)
	private double price;
	

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item))
            return false;
        if (obj == this)
            return true;

        Item rhs = (Item) obj;
        return new EqualsBuilder().
            append(id, rhs.getId()).
            append(name, rhs.getName()).
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
