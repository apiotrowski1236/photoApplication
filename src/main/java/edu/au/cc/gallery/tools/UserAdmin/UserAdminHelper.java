package edu.au.cc.gallery.tools;
import org.json.simple.JSONArray;
import java.util.List;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

public class UserAdminHelper {
    DB myDB;
    

    public String  add(Request req, Response resp) {
	String username, password, fullname;
	myDB = new DB();
	username = req.queryParams("userName");
	password = req.queryParams("password");
	fullname = req.queryParams("fullname");
	try {
	myDB.addUser(username, password, fullname);
    }
	catch(SQLException e) {
	    e.printStackTrace();
	}
	return "Added user" + username;
    }

    
      public String delete(Request req, Response resp) {
	  String username;
	  myDB = new DB();
	  username = req.queryParams("username");
	  try {
	      myDB.deleteUser(username);

	  }
	  catch(SQLException e) {
	      e.printStackTrace();
	  }
	  return "Deleted user " + username;
    }

      public String change(Request req, Response resp) {
        Map<String, Object> model = new HashMap<String, Object>();
	String username;
	username = req.queryParams("username");
	username = req.queryParams("username");
	model.put("username", username);
           return new HandlebarsTemplateEngine()
               .render(new ModelAndView(model, "change.hbs"));
    }

    public String confirmDelete(Request req, Response resp) {
	Map<String, Object> model = new HashMap<String, Object>();
	String username;
	username = req.queryParams("username");
	model.put("username", username);
           return new HandlebarsTemplateEngine()
               .render(new ModelAndView(model, "delete.hbs"));

    }
    



    public String sendPasswordChange(Request req, Response resp) {
	String username, password;
        myDB = new DB();
	
        username = req.queryParams("username");
        password = req.queryParams("password");

        try {
        myDB.updateUserPassword(username, password);
    }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return "Modified " + username + "to" +  password;
    }

public String sendNameChange(Request req, Response resp) {
    String username, fullname;
        myDB = new DB();
        username = req.queryParams("username");
        fullname = req.queryParams("fullname");

        try {
        myDB.updateUserFullName(username, fullname);
    }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return "Modified " + username + "to" +  fullname;
    }




    
    public String list(Request req, Response resp) {
	
       	 JSONArray userArray = null;
           try {
	       myDB = new DB();
            userArray = myDB.listUsers();
        
         }
         catch(SQLException e) {
             e.printStackTrace();
             } 

	   Map<String, Object> model = new HashMap<String, Object>();
	   model.put("users", userArray);
	   return new HandlebarsTemplateEngine()
	       .render(new ModelAndView(model, "userlist.hbs"));
    }


    /*Return the admin home page.*/
     public String admin (Request req, Response resp) {
	
	 Map<String, Object> model = new HashMap<String, Object>();
 	 return new HandlebarsTemplateEngine()
	 .render(new ModelAndView(model, "admin.hbs"));

	     }

    /*Map the GET and POST routes. Open up Port 5000 */
    public void addRoutes() {
	port(5000);
	 //Show the webpage
        get("/admin",  (req, res) -> admin(req, res));

	get ("/hello", (req, res) -> "Hello Testing!");
	//lIST USERS
	get("/admin/list", (req, res) -> list(req, res));
	//Delete a user.
	get("/admin/delete", (req, res) -> delete(req, res)); 
	//Create Users
	post("/admin/add", (req, res) -> add(req, res));
	//Modify users
	get("/admin/change", (req, res) -> change(req, res));
	//Show the webpage
	get("/admin",  (req, res) -> admin(req, res));
	//Show the delete confirmation
	get("/admin/confirmDelete",  (req, res) -> confirmDelete(req, res));
	//Send the password change.
	post("/admin/changePassword", (req,res) -> sendPasswordChange(req, res));
	//Send the name change.
	 post("/admin/changeName", (req,res) -> sendNameChange(req, res));
    }

}
