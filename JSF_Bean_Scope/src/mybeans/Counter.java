package mybeans;




// be careful to import ManagedBean form faces NOt javax.annotation


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ApplicationScoped;

// add @ManagedBean annotation
@ManagedBean
/*
 * @ApplicationScoped
 * 
 * @RequestScoped
 */
@SessionScoped

public class Counter {

	private int value = 0;
	
	//3- no-argument constructor

	public Counter() {
		
		System.out.println("Bean constructor is called");
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	// increase the counter (value ) and return the name of JSP file which is the current 
	
	public String increment() {
		
		value++;
		return "application_counter";
	}
	


	

	
}

