package mybeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
@ApplicationScoped
public class Student {

	private String firstName;
	private String lastName;
	private String email;
	private String courseCode;

	// create no-arg constructor
	public Student() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	// Custom Validation method
	public void validateTheCourseCode(FacesContext context, 
									  UIComponent component, 
									  Object value)throws ValidatorException {
		if (value == null) { 
			return; 
		}
		
		String data = value.toString();
		
		// Course code must start with MAS ... if not, throw exception
		if (!data.startsWith("MAS")) {
			FacesMessage message = new FacesMessage("Course code must start with MAS");
			throw new ValidatorException(message);
		}
	}
}
