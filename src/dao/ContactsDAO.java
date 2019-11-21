package dao;
import java.util.*;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

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
}
