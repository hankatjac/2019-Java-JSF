package customer;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.sql.DataSource;


public class CustomerDbUtil {
	private static CustomerDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/customer_db";
	
	// Static Singleton method to create ONLY one instance of this class
	public static CustomerDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new CustomerDbUtil();
		}

		return instance;
	}
	
	
	private CustomerDbUtil() throws Exception{	
		dataSource = getDataSource();	
	}
	

	
	private DataSource getDataSource() throws NamingException{
		Context context = new InitialContext();
		// Lookup connection pool that was created by Tomcat
		// JNDI (Java Naming and Directory Interface) give us access to the objects Tomcat has created
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;	
	}
	
	private Connection getConnection () throws Exception{
		Connection theConn = dataSource.getConnection();
		return theConn;
	}
	
	private void close (Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);		
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs){
		
		try {
			if (theRs !=null) {
				theRs.close();	
			}
			
			if (theStmt != null) {
				theStmt.close();
			}
			
			if (theConn != null) {
				theConn.close();
			}
			
		}
			
		catch (Exception exc) {
			exc.printStackTrace();
		}		
	}	
		


	public List<Customer> getCustomerList() throws Exception{
		List<Customer> customerList= new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
			String sql = "select * from customer_table order by LastName";
			myStmt = myConn.createStatement();
			myRs= myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()){
				// retrieve data from result set row
				int id = myRs.getInt("CustID");
				String firstName = myRs.getString("FirstName");
				String lastName = myRs.getString("LastName");
				String address = myRs.getString("Address");
				String phoneNumber = myRs.getString("PhoneNumber");
				String email = myRs.getString("Email");
				String userName = myRs.getString("UserName");
				String passWord = myRs.getString("PassWord");
				String typeOfCar = myRs.getString("TypeOfCar");
				String brand = myRs.getString("Brand");
				String startDate = myRs.getString("StartDate");
				int numberOfDays = myRs.getInt("NumberOfDays");
				int rate = myRs.getInt("Rate");
				int totalPrice = myRs.getInt("TotalPrice");
				
				
				// create new customer object
				Customer tempCustomer = new Customer(id, firstName, lastName, address, phoneNumber, email, userName, passWord, typeOfCar, brand, startDate, numberOfDays, rate, totalPrice);
				// add it to the list of customer
				customerList.add(tempCustomer);
				
			}
			return customerList;	
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
		// Get the customer by customer ID from database .................
		// This method is calling from loadCustomer(int customerId) in CustomerController class 
		// loadCustomer(int customerId) will make returned customer available in memory 
		// for other pages as a part of servlet data
		
	public Customer getCustomer(int customerId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = getConnection();

			String sql = "select * from customer_table where CustID=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, customerId);

			myRs = myStmt.executeQuery();

			Customer theCustomer = null;

			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("CustID");
				String firstName = myRs.getString("FirstName");
				String lastName = myRs.getString("LastName");
				String address = myRs.getString("Address");
				String phoneNumber = myRs.getString("PhoneNumber");
				String email = myRs.getString("Email");
				String userName = myRs.getString("UserName");
				String passWord = myRs.getString("PassWord");
				String typeOfCar = myRs.getString("TypeOfCar");
				String brand = myRs.getString("Brand");
				String startDate = myRs.getString("StartDate");
				int numberOfDays = myRs.getInt("NumberOfDays");
				int rate = myRs.getInt("Rate");
				int totalPrice = myRs.getInt("TotalPrice");

				theCustomer = new Customer(id, firstName, lastName, address, phoneNumber, email, userName, passWord, typeOfCar, brand, startDate, numberOfDays, rate, totalPrice);
			} else {
				throw new Exception("Could not find customer id: " + customerId);
			}

			return theCustomer;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	//...............................................................................
	
	public void addCustomer(Customer theCustomer) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		
		try {
			myConn = getConnection();
			String sql = "insert into customer_table(FirstName, LastName, Address, PhoneNumber, Email, UserName, PassWord, TypeOfCar, Brand, StartDate, NumberOfDays, Rate, TotalPrice) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			myStmt = myConn.prepareStatement(sql);
			// set params
			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getAddress());
			myStmt.setString(4, theCustomer.getPhoneNumber());
			myStmt.setString(5, theCustomer.getEmail());
			myStmt.setString(6, theCustomer.getUserName());
			myStmt.setString(7, theCustomer.getPassWord());
			myStmt.setString(8, theCustomer.getTypeOfCar());
			myStmt.setString(9, theCustomer.getBrand());
			myStmt.setString(10, theCustomer.getStartDate());
			myStmt.setInt(11, theCustomer.getNumberOfDays());
			myStmt.setInt(12, theCustomer.startRate());
			myStmt.setInt(13, theCustomer.startTotalPrice());
			
			myStmt.execute();

		} finally {
			close(myConn, myStmt);
		}
	}
	
	//..............................................................
		public void updateCustomer(Customer theCustomer) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				myConn = getConnection();

				String sql = "update customer_table" + " set FirstName=?, LastName=?, Address=?, PhoneNumber=?, Email=?, UserName=?, PassWord=?, TypeOfCar=?, Brand=?, StartDate=?, NumberOfDays=?, Rate=?, TotalPrice=?" + " where CustID=?";

				myStmt = myConn.prepareStatement(sql);

				// set params
				myStmt.setString(1, theCustomer.getFirstName());
				myStmt.setString(2, theCustomer.getLastName());
				myStmt.setString(3, theCustomer.getAddress());
				myStmt.setString(4, theCustomer.getPhoneNumber());
				myStmt.setString(5, theCustomer.getEmail());
				myStmt.setString(6, theCustomer.getUserName());
				myStmt.setString(7, theCustomer.getPassWord());
				myStmt.setString(8, theCustomer.getTypeOfCar());
				myStmt.setString(9, theCustomer.getBrand());
				myStmt.setString(10, theCustomer.getStartDate());
				myStmt.setInt(11, theCustomer.getNumberOfDays());
				myStmt.setInt(12, theCustomer.startRate());
				myStmt.setInt(13, theCustomer.startTotalPrice());
				
				myStmt.setInt(14, theCustomer.getId());
				
				myStmt.execute();
				
			} finally {
				close(myConn, myStmt);
			}

		}
		//..............................................................
		public void deleteCustomer(int customerId) throws Exception {

			Connection myConn = null;
			PreparedStatement myStmt = null;

			try {
				myConn = getConnection();

				String sql = "delete from customer_table where CustID=?";

				myStmt = myConn.prepareStatement(sql);

				// set params
				myStmt.setInt(1, customerId);

				myStmt.execute();
			} finally {
				close(myConn, myStmt);
			}
		}
		
		// Search method ----------------------------------------------------------------
		public List<Customer> searchCustomers(String theSearchName) throws Exception {

			// 1- Result list
			List<Customer> customerList = new ArrayList<>();

			// 2- Clean attributes
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
//			int customerId;

			try {

				// 3- Get connection to database
				myConn = dataSource.getConnection();

				// 4- Only search by name if theSearchName is not empty
				if (theSearchName != null && theSearchName.trim().length() > 0) {

					// 5- Create sql to search for customers by name
					String sql = "select * from customer_table where lower(FirstName) like ? or lower(LastName) like ?";

					// 6- Create prepared statement
					myStmt = myConn.prepareStatement(sql);

					// 7- Set params
					String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
					myStmt.setString(1, theSearchNameLike);
					myStmt.setString(2, theSearchNameLike);

				} else {
					// 8- Create sql to get all customers
					String sql = "select * from customer_table order by LastName";

					// 9- Create prepared statement
					myStmt = myConn.prepareStatement(sql);
				}

				// 10- Execute statement
				myRs = myStmt.executeQuery();

				// 11- Retrieve data from result set row
				while (myRs.next()) {

					// 12- Retrieve data from result set row
					int id = myRs.getInt("CustID");
					String firstName = myRs.getString("FirstName");
					String lastName = myRs.getString("LastName");
					String address = myRs.getString("Address");
					String phoneNumber = myRs.getString("PhoneNumber");
					String email = myRs.getString("Email");
					String userName = myRs.getString("UserName");
					String passWord = myRs.getString("Password");
					String typeOfCar = myRs.getString("TypeOfCar");
					String brand = myRs.getString("Brand");
					String startDate = myRs.getString("StartDate");
					int numberOfDays = myRs.getInt("NumberOfDays");
					int rate = myRs.getInt("Rate");
					int totalPrice = myRs.getInt("TotalPrice");
					// 13- Create new customer object
					Customer tempCustomer = new Customer(id, firstName, lastName, address, phoneNumber, email, userName, passWord, typeOfCar, brand, startDate, numberOfDays, rate, totalPrice);

					// 14- Add it to the list of customers
					customerList.add(tempCustomer);
				}

				return customerList;
			} finally {

				// 15- Clean up JDBC objects
				close(myConn, myStmt, myRs);
			}
		}
		// ------------------------------------------------------------------------------

	}


