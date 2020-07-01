 package edu.au.cc.gallery.tools.UserAdmin.ui;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONArray;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import edu.au.cc.gallery.tools.UserAdmin.data.Photo;
import edu.au.cc.gallery.tools.UserAdmin.data.DAO;
import edu.au.cc.gallery.tools.UserAdmin.data.daoMaker;
import edu.au.cc.gallery.tools.UserAdmin.data.PhotoDAO;
import edu.au.cc.gallery.tools.UserAdmin.data.S3;
public class UserAdminHelper {

   public static String  parsePathOnly(Request req) {
        String path;
	path = req.queryParams("path");
	return path;
    }



    public static void add(String path) {
       String owner = "TESTFORNOWBUTREPLACELATER";
       Photo photo = new Photo(path, owner);
       try {
	   DAO dao = daoMaker.getPhotoDAO();
	   dao.add(photo);
       }
       catch(Exception e) {
	   e.printStackTrace();
       }
    }
    
    

      public static String delete(Request req, Response resp) {
	  String path;
	  String owner = "TESTFORNOWBUTREPLACELATER";
	  path = req.queryParams("path");
	  Photo photo = new Photo(path, owner);
	  try {
	    DAO dao = daoMaker.getPhotoDAO();
            dao.delete(photo);
	  }
	  catch(Exception e) {
	      e.printStackTrace();
	  }
	  return "deleted";
	  //return makeConfirmationPage(username, "deleteconfirmation.hbs");
	  } 

    public static String  makeConfirmationPage(String path, String fileName) {
	Map<String, Object> model = new HashMap<String, Object>();
	model.put("path", path);
	return new HandlebarsTemplateEngine()
	    .render(new ModelAndView(model, fileName));
    }

    /*

      public static String change(Request req, Response resp) {
        Map<String, Object> model = new HashMap<String, Object>();
	String username = parseUserNameOnly(req);
	return makeConfirmationPage(username, "change.hbs");
    }

    public static String confirmDelete(Request req, Response resp) {
	 String username = parseUserNameOnly(req);
	 return makeConfirmationPage(username, "delete.hbs");
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
	       .render(new ModelAndView(model, "userlist.hbs"));
	       } */

    
    public static String modelMaker(Request req, Response resp, String fileName) {
	Map<String, Object> model = new HashMap<String, Object>();
	return new HandlebarsTemplateEngine()
	    .render(new ModelAndView(model, fileName));
    }



  }
