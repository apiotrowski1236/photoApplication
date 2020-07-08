 package edu.au.cc.gallery.tools.UserAdmin.authentication;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import java.util.Map;
import java.util.HashMap;

import edu.au.cc.gallery.tools.UserAdmin.login.DB;
import edu.au.cc.gallery.tools.UserAdmin.login.Postgres;
import edu.au.cc.gallery.tools.UserAdmin.login.UserDAO;
import edu.au.cc.gallery.tools.UserAdmin.login.User;


public class Authenticator {

    public static String debug(Request req, Response resp) {
	StringBuffer sb = new StringBuffer();
	for(String key: req.session().attributes()) {
	    sb.append(key+"->"+req.session().attribute(key)+"<br />");
	}
	return sb.toString();
    }
    

    public static String login(Request req, Response resp) {
	String username = "";
	String password = "";
	
	Map<String, Object> model = new HashMap<String, Object>();
	try {
	    username = req.queryParams("username");
	    password = req.queryParams("password");
	}
	catch(Exception e) {
	    e.printStackTrace();
	}

	
	try {
	     UserDAO dao = Postgres.getUserDAO();

	     if (dao.searchForUser(username, password) == false){
		 resp.redirect("/login/loginerror");
		}
	      else {
		   //If admin and password is correct.
		   if ((username.equals("admin")))  {
			 req.session().attribute("user", username);
			 resp.redirect("/admin");
		     }


		   //If admin and password is correct for the TA user.                                                                                                                                                      
                   else if ((username.equals("dongji")))  {
                         req.session().attribute("user", username);
                         resp.redirect("/admin");
                     }



		   //If regular user and password is correct. 
		     else  {
			 req.session().attribute("user", username);
			 resp.redirect("/login/welcome");
		     }
	       } 
	}
	catch(Exception e) {
	    e.printStackTrace();
	    } 
	return "";
    }


   public static boolean isAdmin(String username) {
	if ((username != null) && (username.equals("admin"))) {
		return true;
	    }
	return false;
    }

    public static String checkAdmin(Request req, Response resp) {
	if (!(isAdmin(req.session().attribute("user")))) {
		return "Whoa there, not an admin!";
	 }
	return "";
    }

}
