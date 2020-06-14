package edu.au.cc.gallery.tools;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
public class UserAdmin {
    UserAdminHelper myHelper;
    DB myDatabase;
    public UserAdmin() {
	myHelper = new UserAdminHelper();
	myDatabase = new DB();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
       	admin.myHelper.addRoutes();
	
	try {
	   JSONObject printOut =  admin.myDatabase.getSecret();	   
	}
	catch(Exception e) {
	    System.out.println("failed to get secret!");
	    e.printStackTrace();
	}
	/*	try {
	    admin.myDatabase.connect();
	    JSONArray  myTest = admin.myDatabase.listUsers();
	    System.out.println(myTest); 
	    admin.myDatabase.close();
          }

	catch (SQLException ex) {
	    ex.printStackTrace();
	    } */ 

	        
    }
}
