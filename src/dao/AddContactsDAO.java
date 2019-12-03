package dao;

import core.Contacts;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class AddContactsDAO {
    private Connection myConn;

    public AddContactsDAO() throws Exception{

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

    public void save(String fname,String lname,String email,int num){
        int status=0;
        try{

            PreparedStatement ps=myConn.prepareStatement("insert into contacts (firstName, lastName, email, number) values(?,?,?,?)");
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,email);
            ps.setInt(4,num);
            status=ps.executeUpdate();
            myConn.close();
        }catch(Exception e){System.out.println(e);}
        //return status;
    }
}
