package mybeans;


// be careful to import ManagedBean form faces NOT javax.annotation


import javax.faces.bean.ManagedBean;


// add @ManagedBean annotation
@ManagedBean

public class Student {
	private String firstName;
	private String lastName;

	private String[] favouriteLanguage;
	
	//3- no-argument constructor

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


	public String[] getFavouriteLanguage() {
		return favouriteLanguage;
	}


	public void setFavouriteLanguage(String[] favouriteLanguage) {
		this.favouriteLanguage = favouriteLanguage;
	}




	


	

	
}

