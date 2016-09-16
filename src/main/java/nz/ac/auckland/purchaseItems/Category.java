package nz.ac.auckland.purchaseItems;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	private Set<Item> items = new HashSet<Item>();
	
}
