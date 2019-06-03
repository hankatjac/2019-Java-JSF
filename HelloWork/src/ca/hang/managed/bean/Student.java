package ca.hang.managed.bean;

// be careful to import ManagedBean form faces NOt javax.annotation
import javax.faces.bean.ManagedBean;
// add @ManagedBean annotation
@ManagedBean
public class Student {
	private String firstName;
	private String lastName;
	
	
	public Student() {
		System.out.println("Bean constructor is called");
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}

