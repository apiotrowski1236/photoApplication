 package edu.au.cc.gallery.tools.UserAdmin.ui;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import org.json.simple.JSONArray;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import edu.au.cc.gallery.tools.UserAdmin.data.Photo;
import edu.au.cc.gallery.tools.UserAdmin.data.DAO;
import edu.au.cc.gallery.tools.UserAdmin.data.daoMaker;
import edu.au.cc.gallery.tools.UserAdmin.data.PhotoDAO;
import edu.au.cc.gallery.tools.UserAdmin.data.S3;
public class PhotoHelper {

   public static String  parsePathOnly(Request req) {
        String path;
	path = req.queryParams("path");
	return path;
    }


        
    public static void add(String path, String owner) {
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
	   path = req.queryParams("path");
	   String user =  req.session().attribute("user");
           Photo photo = new Photo(path, user);
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

   
       
    public static String list(Request req, Response resp) {
        List<Photo> photoList = new ArrayList<Photo>();
	String owner;
	 String user =  req.session().attribute("user");
	try {
               DAO dao = daoMaker.getPhotoDAO();
	        photoList = dao.listPhotos(user);
          }

	catch(Exception e) {
             e.printStackTrace();
          }
	 Map<String, Object> model = new HashMap<String, Object>();
	 model.put("photos", photoList);
	   return new HandlebarsTemplateEngine()
	       .render(new ModelAndView(model, "view.hbs"));
	       } 



    public static String adminModelMaker(Request req, Response resp, String fileName) {
	 Map<String, Object> model = new HashMap<String, Object>();
	 String user =  req.session().attribute("user");
	 model.put("user", user);
	 return new HandlebarsTemplateEngine()
	     .render(new ModelAndView(model, fileName));
    }


    
    public static String modelMaker(Request req, Response resp, String fileName) {
	Map<String, Object> model = new HashMap<String, Object>();
	return new HandlebarsTemplateEngine()
	    .render(new ModelAndView(model, fileName));
    }



  }
