package nz.ac.auckland.purchaseItems;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "CATEGORIES")
public class Category {
	
	@XmlID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private int id;
	
	@XmlElement(name="category-name")
	@Column(name = "NAME", nullable = false)
	private String name;
	
	
	// Required by JPA.
	protected Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Item))
			return false;
		if (obj == this)
			return true;

		Category cat = (Category) obj;
		return new EqualsBuilder().append(id, cat.getId())
				.append(name, cat.getName()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(getClass().getName())
				.append(id).append(name).toHashCode();
	}
	

}
