package nz.ac.auckland.purchaseItems;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "CATEGORIES")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private Set<Item> items = new HashSet<Item>();
	
	// Required by JPA.
	protected Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Item> getItems() {
		return items;
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
