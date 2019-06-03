package mybeans;

/**
 * 
 */
// 0- be careful to import ManagedBean form faces NOt javax.annotation

import javax.faces.bean.ManagedBean;
// add @ManagedBean annotation
@ManagedBean

public class Student {
	private String firstName;
	private String lastName;
	private String email;
	private int freePasses;
	private String postalCode;
	
	//3- no-arg constructor
	
	
	public Student() {
		
		System.out.println("Bean constructor is called");
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getFreePasses() {
		return freePasses;
	}

	public void setFreePasses(int freePasses) {
		this.freePasses = freePasses;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	

	
}

