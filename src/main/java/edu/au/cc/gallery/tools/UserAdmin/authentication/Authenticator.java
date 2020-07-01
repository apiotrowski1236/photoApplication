package edu.au.cc.gallery.tools.UserAdmin.authentication;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import java.util.Map;
import java.util.HashMap;

public class Authenticator {


    public static String makeDemo(Request req, Response resp) {
	if (req.session().isNew()) {
	    req.session().attribute("value", 0);
	    	}
	else {
	    req.session().attribute("value", (int)req.session().attribute("value") + 1);
	}

	return "<h1>" + req.session().attribute("value") + "</h1>";
    }

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
	    if ((username.equals("user")) && (password.equals("password"))) {
		req.session().attribute("user", username);
		resp.redirect("/sessionDebug");
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

}
