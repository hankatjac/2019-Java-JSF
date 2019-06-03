package myBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StudentController {
	
	private List <Student> students;
	private StudentDbUtil studentDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public StudentController() throws Exception{
		students = new ArrayList<>();
		studentDbUtil = studentDbUtil.getInstance();
		
	}
	
	public List <Student> getStudents(){
		return students;
	}
	
	public void loadStudents() {
		logger.info("\n\n------------- Loading students");
		students.clear();
		try {
			// get all students from database
			students = studentDbUtil.getStudents();
			
		}catch(Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading students", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}	
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error:" + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);	
	}
	
	
	// Add ----------------------------------------------------------
	public String addStudent(Student theStudent) {
		logger.info("\n\n------------- Adding students:" + theStudent);
		
	
		try {
			// add student to the database
			studentDbUtil.addStudent(theStudent);
			
		}catch(Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding students", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}	
		
		return "list-students?faces-redirect=true";
	}

	// --------------------------------------------------------------
	
	// UPDATE ---------------------------------------------------------------------------------------

		// Get selected student and make it available in memory 
		// for other pages as a part of servlet data
		// This method is calling from list-students.xhtml
		public String loadStudent(int studentId) {

			logger.info("\n\n-------------------- loading student: " + studentId);

			try {
				// 1- get student from database
				Student theStudent = studentDbUtil.getStudent(studentId);

				// 2- Create an externalContext object ........................................
				/*
				 * ExternalContext can be consider as a memory space we use 
				 * to store data and have access to it
				 * 
				 * FacesContext contains all of the per-request state information 
				 * related to the processing of a single JavaServer Faces request, 
				 * and the rendering of the corresponding response.
				 * 
				 * A FacesContext instance is associated with a particular request 
				 * at the beginning of request processing, 
				 * by a call to the getFacesContext() method of the 
				 * FacesContextFactory instance associated with the 
				 * current web application. The instance remains active until 
				 * its release() method is called, after which no further 
				 * references to this instance are allowed. 
				 * While a FacesContext instance is active, 
				 * it must not be referenced from any thread other than 
				 * the one upon which the servlet container executing 
				 * this web application utilizes for the processing of 
				 * this request.
				*/
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

				// 3- Return request object Map attribute from ExternalContext object
				Map<String, Object> requestMap = externalContext.getRequestMap();

				// 4- Put selected student to request object Map attributes
				requestMap.put("student", theStudent);
				//.............................................................................
				
			} catch (Exception exc) {
				// send this to server logs
				logger.log(Level.SEVERE, "Error loading student id:" + studentId, exc);

				// add error message for JSF page
				addErrorMessage(exc);

				return null;
			}

			// 5- Go to the next page to update this selected student
			// By calling update-student-form.xhtml.xtml "externalContext" will be forwarded to that page, and
			// update-student-form will be populated with requestMap object
			return "update-student-form.xhtml";
		}

		// This method is calling from update-student-form.xhtml
		public String updateStudent(Student theStudent) {

			logger.info("\n\n-------------------- updating student: " + theStudent);

			try {

				// 1- Update student in the database
				studentDbUtil.updateStudent(theStudent);

			} catch (Exception exc) {
				// 2- Send this to server logs
				logger.log(Level.SEVERE, "Error updating student: " + theStudent, exc);

				// 3- Add error message for JSF page
				addErrorMessage(exc);

				return null;
			}
			// redirect: Browser URL will be updated. It is like sending an other GET request for a specific page
			// forward:  Browser URL will not be updated
			return "list-students?faces-redirect=true";
		}
		// -----------------------------------------------------------------------------------------------
}
