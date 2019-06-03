package ca.hang.managed.bean;

// be careful to import ManagedBean form faces NOt javax.annotation
import java.util.ArrayList;

import java.util.List;

import javax.faces.bean.ManagedBean;
// add @ManagedBean annotation
@ManagedBean

public class Student2 {
	private String firstName;
	private String lastName;
	private String country;
	
	
	//2- list of countries for the drop-down list 
	List<String> countryOptionArrayList;
	
	//3- no-argument constructor
	
	
	public Student2() {
		//4- populate the list of countries
		// also we can populate our drop down menu in JSF page instead of this bean
		countryOptionArrayList = new ArrayList<>();
		countryOptionArrayList.add("Brazil");
		countryOptionArrayList.add("France");
		countryOptionArrayList.add("Germany");
		countryOptionArrayList.add("India");
		countryOptionArrayList.add("Turkey");
		countryOptionArrayList.add("United Kindom");
		countryOptionArrayList.add("United States");
		System.out.println("Bean constructor is called");
	}
	
	public List <String> getCountryOptionArrayList(){	
		return countryOptionArrayList;
	}
	
	
	public void setCountryOptionArrayList(List<String> countryOptionArrayList) {
		this.countryOptionArrayList = countryOptionArrayList;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
	
}

