package customer;

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
public class CustomerController {
	
	private List <Customer> customerList;
	private CustomerDbUtil customerDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// 1- Add theSearchName attribute -----------------------------------------------
	private String theSearchName;
	// ------------------------------------------------------------------------------

	// 2- theSearchName getter and setter -------------------------------------------
	public String getTheSearchName() {
		return theSearchName;
	}

	public void setTheSearchName(String theSearchName) {
		this.theSearchName = theSearchName;
	}
	
	
	// ------------------------------------------------------------------------------
	
	public List <Customer> getCustomerList(){
		return customerList;
	}
	
	public CustomerController() throws Exception{
		customerList = new ArrayList<>();
		customerDbUtil = customerDbUtil.getInstance();
		
	}
	public void loadCustomerList() {
		logger.info("\n\n------------- Loading customers");
		customerList.clear();
		try {
			// ------------------------------------------------------------------------------
			if (theSearchName != null && theSearchName.trim().length() > 0) {
				// Search for customer by name
				customerList = customerDbUtil.searchCustomers(theSearchName);
			}
			// ------------------------------------------------------------------------------
			else {
			// get all customerList from database
			customerList = customerDbUtil.getCustomerList();
			}
		}catch(Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading customers", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}	
	}
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error:" + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);	
	}
	
	
	// Add ----------------------------------------------------------
	public String addCustomer(Customer theCustomer) {
		logger.info("\n\n------------- Adding customers:" + theCustomer);
		
		try {
			// add customer to the database
			customerDbUtil.addCustomer(theCustomer);
			
		}catch(Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding customers", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}	
		
		return "show_all?faces-redirect=true";
	}

	// --------------------------------------------------------------
	
	// UPDATE ---------------------------------------------------------------------------------------

		// Get selected customer and make it available in memory 
		// for other pages as a part of servlet data
		// This method is calling from list-customerList.xhtml
		public String loadCustomer(int customerId) {

			logger.info("\n\n-------------------- loading customer: " + customerId);

			try {
				// 1- get customer from database
				Customer theCustomer = customerDbUtil.getCustomer(customerId);

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

				// 4- Put selected customer to request object Map attributes
				requestMap.put("customer", theCustomer);
				//.............................................................................
				
			} catch (Exception exc) {
				// send this to server logs
				logger.log(Level.SEVERE, "Error loading customer id:" + customerId, exc);

				// add error message for JSF page
				addErrorMessage(exc);

				return null;
			}

			// 5- Go to the next page to update this selected customer
			// By calling update-customer-form.xhtml "externalContext" will be forwarded to that page, and
			// update-customer-form will be populated with requestMap object
			return "update-customer-form.xhtml";
		}

		// This method is calling from update-customer-form.xhtml
		public String updateCustomer(Customer theCustomer) {
			

			logger.info("\n\n-------------------- updating customer: " + theCustomer);

			try {

				// 1- Update customer in the database
				customerDbUtil.updateCustomer(theCustomer);

			} catch (Exception exc) {
				// 2- Send this to server logs
				logger.log(Level.SEVERE, "Error updating customer: " + theCustomer, exc);

				// 3- Add error message for JSF page
				addErrorMessage(exc);

				return null;
			}
			// redirect: Browser URL will be updated. It is like sending an other GET request for a specific page
			// forward:  Browser URL will not be updated
			return "show_all?faces-redirect=true";
		}
		
		
		// Delete -------------------------------------------------------------------
		public String deleteCustomer(int customerId) {

			logger.info("\n\n-------------------- Deleting customer id: " + customerId);

			try {

				// delete the customer from the database
				customerDbUtil.deleteCustomer(customerId);
				
			} catch (Exception exc) {
				// send this to server logs
				logger.log(Level.SEVERE, "Error deleting customer id: " + customerId, exc);

				// add error message for JSF page
				addErrorMessage(exc);

				return null;
			}

			return "show_all";
		}
}
