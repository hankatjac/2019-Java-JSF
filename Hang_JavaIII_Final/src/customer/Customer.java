package customer;

// be careful to import ManagedBean form faces NOT javax.annotation

import javax.faces.bean.ManagedBean;



import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;



// add @ManagedBean annotation
@ManagedBean



public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String email;
	private String userName;
	private String passWord;
	private String typeOfCar;
	private String brand;
	private String startDate;
	private int numberOfDays;
	private int rate;
	private int totalPrice;
	
	
	public Customer() {
		
	}
	

	public Customer(int id, String firstName, String lastName, String address, String phoneNumber, String email,
			String userName, String passWord, String typeOfCar, String brand, String startDate, int numberOfDays,
			int rate, int totalPrice) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.userName = userName;
		this.passWord = passWord;
		this.typeOfCar = typeOfCar;
		this.brand = brand;
		this.startDate = startDate;
		this.numberOfDays = numberOfDays;
		this.rate = rate;
		this.totalPrice = totalPrice;
	}













	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	/*
	 * @Override public String toString() { return "Customer [id=" + id +
	 * ", firstName=" + firstName + ", lastName=" + lastName + ", address=" +
	 * address + ", phoneNumber =" + phoneNumber + ", email=" + email + "]"; }
	 */
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	
	public String getTypeOfCar() {
		return typeOfCar;
	}




	public void setTypeOfCar(String typeOfCar) {
		this.typeOfCar = typeOfCar;
	}




	public String getBrand() {
		return brand;
	}




	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getStartDate() {
		return startDate;
	}




	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	public int getNumberOfDays() {
		return numberOfDays;
	}




	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	
	

	public int getRate() {
		return rate;
	}


	public void setRate(int rate) {
		this.rate = rate;
	}

	
	

	public int getTotalPrice() {
		return totalPrice;
	}




	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int startRate() { 
		if (typeOfCar.equals("Small"))
			rate = 50;
		else if (typeOfCar.equals("SUV"))
			rate = 60;
		else 
			rate = 70;
		
		return rate;
	}




	public int startTotalPrice() {
		
		totalPrice = (int) ((rate * numberOfDays)*1.15);
		return totalPrice;
	}



	// Custom Validation method
	public void validateTheUserName(FacesContext context, 
									  UIComponent component, 
									  Object value)throws ValidatorException {
		if (value == null) { return; }
		String data = value.toString();
		
		// User name must start with IPD17 ... if not, throw exception
		if (!data.startsWith("IPD17")) {
			FacesMessage message = new FacesMessage("Usernames must start with IPD17");
			throw new ValidatorException(message);
		}
	}




}



