 package edu.au.cc.gallery.tools.UserAdmin.authentication;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import java.util.Map;
import java.util.HashMap;

public class Authenticator {

    public static String debug(Request req, Response resp) {
	StringBuffer sb = new StringBuffer();
	for(String key: req.session().attributes()) {
	    sb.append(key+"->"+req.session().attribute(key)+"<br />");
	}
	return sb.toString();
    }

    public static String login(Request req, Response resp) {
	String username;
	String password;
	Map<String, Object> model = new HashMap<String, Object>();
	try {
	    username = req.queryParams("username");
	    password = req.queryParams("password");
	    if ((username.equals("admin")) && (password.equals("password"))) {
		req.session().attribute("user", username);
		resp.redirect("/admin");
	    }
	    else if ((username.equals("user")) && (password.equals("password"))) {
		req.session().attribute("user", username);
		resp.redirect("/user");
	    }
	    
	    else {
		resp.redirect("/login");
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
