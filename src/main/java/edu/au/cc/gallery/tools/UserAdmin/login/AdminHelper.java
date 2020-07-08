package edu.au.cc.gallery.tools.UserAdmin.login;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONArray;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import edu.au.cc.gallery.tools.UserAdmin.login.Postgres;
import edu.au.cc.gallery.tools.UserAdmin.login.UserDAO;
import edu.au.cc.gallery.tools.UserAdmin.login.DB;
import edu.au.cc.gallery.tools.UserAdmin.login.User;
import edu.au.cc.gallery.tools.UserAdmin.login.PostgresDAO;


public class AdminHelper {

    public static String  parseUserNameOnly(Request req) {
	String username;
	username = req.queryParams("username");
	return username;
    }

    
     public static String add(Request req, Response resp) {
	String username, password, fullname;
	username = req.queryParams("userName");
	password = req.queryParams("password");
	fullname = req.queryParams("fullname");
	try {
            UserDAO dao = Postgres.getUserDAO();
	    dao.addUser(username, password, fullname);
	}
	catch(Exception e) {
	    e.printStackTrace();
	}
	return makeConfirmationPage(username, "admin_addconfirmation.hbs");
    }

    
    public static String delete(Request req, Response resp) {
	  String username = parseUserNameOnly(req);
	  try {
	    UserDAO dao = Postgres.getUserDAO();
            dao.deleteUser(username);
	  }
	  catch(Exception e) {
	      e.printStackTrace();
	  }
	  return makeConfirmationPage(username, "admin_deleteconfirmation.hbs");
    }

    
     public static String  makeConfirmationPage(String username, String fileName) {
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("username", username);
	return new HandlebarsTemplateEngine()
	    .render(new ModelAndView(model, fileName));
    }

    
     public static String change(Request req, Response resp) {
        Map<String, Object> model = new HashMap<String, Object>();
	String username = parseUserNameOnly(req);
	return makeConfirmationPage(username, "admin_change.hbs");
    }

    
    public static String confirmDelete(Request req, Response resp) {
	 String username = parseUserNameOnly(req);
	 return makeConfirmationPage(username, "admin_delete.hbs");
    }

    
     public static String sendPasswordChange(Request req, Response resp) {
	String username, password;
        username = req.queryParams("username");
        password = req.queryParams("password");

        try {
	    UserDAO dao = Postgres.getUserDAO();
	    dao.updateUserPassword(username, password);
	}
        catch(Exception e) {
            e.printStackTrace();
        }
        return "Modified " + username + "to" +  password;
    }

    public static String sendNameChange(Request req, Response resp) {
    String username, fullname;
     username = req.queryParams("username");
     fullname = req.queryParams("fullname");

      try {
	  UserDAO dao = Postgres.getUserDAO();
	  dao.updateUserFullName(username, fullname);
      }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "Modified " + username + "to" +  fullname;
	}


    public static String list(Request req, Response resp) {
	 JSONArray userArray = null;
	 try {
	        UserDAO dao = Postgres.getUserDAO();
	       userArray = dao.getUsers();
          }
         catch(Exception e) {
             e.printStackTrace();
          }
	 Map<String, Object> model = new HashMap<String, Object>();
	   model.put("users", userArray);
	   return new HandlebarsTemplateEngine()
	       .render(new ModelAndView(model, "admin_userlist.hbs"));
    }


    
    public static String adminModelMaker(Request req, Response resp, String fileName) {
	Map<String, Object> model = new HashMap<String, Object>();
	return new HandlebarsTemplateEngine()
	    .render(new ModelAndView(model, fileName));
    }

}
