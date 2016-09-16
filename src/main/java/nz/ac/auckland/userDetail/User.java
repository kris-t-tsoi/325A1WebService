package nz.ac.auckland.userDetail;
import java.util.Set;

import javax.persistence.*;
//import javax.xml.bind.annotation.*;


@Entity
@Table(name="Users")
public class User {
	
//	@XmlID
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name="userId")
	private Long id;	
	
	private String firstName;
	private String lastName;
	
	@Column(name="USERNAME", nullable=false, length=30)
	private String userName;
	

}
