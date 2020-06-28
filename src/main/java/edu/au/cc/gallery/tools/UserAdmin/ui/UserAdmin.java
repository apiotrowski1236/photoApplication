package edu.au.cc.gallery.tools.ui;
import edu.au.cc.gallery.tools.data.DB;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Map;
import java.util.HashMap;
public class UserAdmin {
    UserAdminHelper myHelper;
    DB myDatabase;
    RouteMapper mapper;
    public UserAdmin() {
	myHelper = new UserAdminHelper();
	myDatabase = new DB();
	mapper = new RouteMapper();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
	//	admin.myHelper.connectToWebApp();
	admin.mapper.connectToWebApp();
	try {
	   JSONObject printOut =  admin.myDatabase.getSecret();	   
	}
	catch(Exception e) {
	    System.out.println("failed to get secret!");
	    e.printStackTrace();
	}
	        
    }
}
