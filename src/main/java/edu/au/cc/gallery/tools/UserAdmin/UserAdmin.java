package edu.au.cc.gallery.tools;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.Map;
import java.util.HashMap;
public class UserAdmin {
    DB myDatabase;

    public UserAdmin() {
	myDatabase = new DB();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
	System.out.println("Hello world testing!");
	port(5000);
	get("/hello", (req, res) -> "Hello World");
	get("/goodbye", (req, res) -> "Goodbye!");

	get("/greet/:name", (req, res) -> "Nice to meet you " + req.params(":name"));
	get("/add", (req, res) -> "the sum is " + (Integer.parseInt(req.queryParams("x")) + Integer.parseInt(req.queryParams("y"))));

	get("/calculator", (req, res) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "Fred");
		return new HandlebarsTemplateEngine()
		    .render(new ModelAndView(model, "calculator.hbs"));
	    });
	/*try {
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
	    } */

	        
    }
}
