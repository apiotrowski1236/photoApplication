package edu.au.cc.gallery.tools;
import java.sql.SQLException;
import org.json.simple.JSONObject;
public class UserAdmin {
    DB myDatabase;

    public UserAdmin() {
	myDatabase = new DB();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
	System.out.println("Hello world testing!");
	

	try {
	   JSONObject printOut =  admin.myDatabase.getSecret();
	   // String password = admin.myDatabase.getPassword(printOut);
	   //String user = admin.myDatabase.getUserName(printOut);
	   System.out.println(printOut);
	   //  System.out.println(user);
	}
	catch(Exception e) {
	    System.out.println("failed to get secret!");
	    e.printStackTrace();
	}
		try {
	    admin.myDatabase.connect();
	    admin.myDatabase.listUsers();
	    admin.myDatabase.close();
	}
	catch (SQLException ex) {
	    ex.printStackTrace();
	    } 

	        
    }
}
