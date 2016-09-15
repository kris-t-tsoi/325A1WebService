package nz.ac.auckland.userDetail;

import javax.persistence.Embeddable;

@Embeddable
public class UserAge {
	
	private int age;
	private boolean student;

	public UserAge(int age) {
		this.age = age;
		
		if(age<16){
			this.student = true;
		}else{
			this.student = false;
		}
	}

	public UserAge(int age, boolean isStudent) {
		this.age = age;
		this.student = isStudent;
	}
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isStudent() {
		return student;
	}

	public void setStudent(boolean student) {
		this.student = student;
	}

}
