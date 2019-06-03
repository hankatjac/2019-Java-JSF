package model;

// be careful to import ManagedBean form faces NOT javax.annotation

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;



// add @ManagedBean annotation
@ManagedBean
@ApplicationScoped


public class Model {
	private String firstName;
	private String lastName;
	private int id;
	private String email;
	private String phoneNumber;
	private String address;
	private String userName;
	private String passWord;
	private String[] favorite;
	private String profileSelect;
	private String profileConfirmation;
	
	
	
	public Model() {
		
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


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


	public String[] getFavorite() {
		return favorite;
	}

	public void setFavorite(String[] favorite) {
		this.favorite = favorite;
	}

	public String getProfileSelect() {
		return profileSelect;
	}

	public void setProfileSelect(String profileSelect) {
		this.profileSelect = profileSelect;
	}
	

	public String getProfileConfirmation() {
		return profileConfirmation;
	}

	public void setProfileConfirmation(String profileConfirmation) {
		this.profileConfirmation = profileConfirmation;
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
	
		
				
	
	public String startProfileConfirmation() {
		if (profileConfirmation != null && profileConfirmation.equals("shopping")) {
			return "shopping";
		} else {
			return "registration";
		}
	}
				
		
		
}



