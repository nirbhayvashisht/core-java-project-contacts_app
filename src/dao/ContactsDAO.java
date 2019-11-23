package dao;
import java.util.*;

import core.Contacts;

import java.sql.*;
import java.io.*;

public class ContactsDAO {
	private Connection myConn;
	
	public ContactsDAO() throws Exception{
		
		//get database properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public List<Contacts> getAllContacts() throws Exception {
		List<Contacts> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from contacts");
			
			while (myRs.next()) {
				Contacts tempContact = convertRowToContacts(myRs);
				list.add(tempContact);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Contacts> searchContacts(String firstName) throws Exception {
		List<Contacts> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			firstName += "%";
			myStmt = myConn.prepareStatement("select * from contacts where firstName like ?");
			
			myStmt.setString(1, firstName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Contacts tempContact = convertRowToContacts(myRs);
				list.add(tempContact);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private Contacts convertRowToContacts(ResultSet myRs) throws SQLException {
	    //this function takes the result set and creates a Contacts object with it
		//and returns that object
		String lastName = myRs.getString("lastName");
		String firstName = myRs.getString("firstName");
		String email = myRs.getString("email");
		int number = myRs.getInt("number");
		
		Contacts tempEmployee = new Contacts(firstName,lastName,  email, number);
		
		return tempEmployee;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		ContactsDAO dao = new ContactsDAO();
		System.out.println(dao.searchContacts("Nirbh"));

		System.out.println(dao.getAllContacts());
	}

}
