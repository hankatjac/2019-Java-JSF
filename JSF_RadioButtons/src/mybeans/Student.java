package mybeans;


// 0- be careful to import ManagedBean form faces NOt javax.annotation


import javax.faces.bean.ManagedBean;
// add @ManagedBean annotation
@ManagedBean

public class Student {
	private String firstName;
	private String lastName;
	private String favoriteLanguage;
	
	
	//3- no-arg constructor
	
	
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

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}




	
	
	
}

