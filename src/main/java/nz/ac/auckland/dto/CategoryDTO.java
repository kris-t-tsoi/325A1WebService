package nz.ac.auckland.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.purchaseItems.Item;

@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDTO {

	@XmlID
	private String id;
	
	@XmlElement(name="category-name")
	private String name;
	
	
	// Required by JPA.
	protected CategoryDTO(String name) {
		this(0,name);
	}

	public CategoryDTO(long id,String name) {
		this.id =Long.toString(id);
		this.name = name;
	}

	
	public String getId() {
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
