package edu.au.cc.gallery.tools.UserAdmin.login;
import java.util.ArrayList;
import org.json.simple.parser.*;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import edu.au.cc.gallery.tools.UserAdmin.authentication.Secrets;

public class DB {
    Secrets secretManager = new Secrets();
    private Connection connection;

    /*Connnect to the database. */ 
    public void connect() throws SQLException {
	
	try {
	    Class.forName("org.postgresql.Driver");
	     String host = System.getenv("PG_HOST");
	     String port = System.getenv("PG_PORT");
	    String dbURL;

	        if ((host == null) || (port == null)) {
		dbURL = "jdbc:postgresql://db1.cgtykvv08hlh.us-east-2.rds.amazonaws.com:5432/";
		  }
		
	    else {
		dbURL = "jdbc:postgresql://" + System.getenv("PG_HOST") + ":" + System.getenv("PG_PORT") +"/";
	    }

	    
	    String user = System.getenv("IG_USER");
            String password = System.getenv("IG_PASSWD");
		
	      if ((user == null) || (password == null)) {
		 user = "image_gallery";
		 password = getPassword(getSecret());
		 }

	    connection = DriverManager.getConnection(dbURL, user, password);
	}
	catch (Exception ex) {
	    connection.close();
	    ex.printStackTrace();
	    System.exit(1);
	  
	}
 }

    /*************************************Secret Retrieving and Parsing Functions*******************/

    //Retrieve the secret 
    public JSONObject getSecret() {
	String jsonString = secretManager.getImageGallerySecret();
	JSONObject jo = null;
	try {
	    Object obj = new JSONParser().parse(jsonString);
	    jo = (JSONObject) obj;
	}
	catch (ParseException p) {
	    p.printStackTrace();
	}
	if (jo == null) {
	    System.out.println("Secret not retrieved.");
	}
	    return jo;
    }

    //Retrieve the password from the secret.
	public  String getPassword(JSONObject secret) {
	    String password = (String) secret.get("password");
	    return password;
	}

    //Retrieve the username from the secret.
    public String getUserName(JSONObject secret) {
	String userName = (String) secret.get("username");
	return userName;
	} 



    /************************DB Search/Change Functions*************************************/


    public ResultSet executePreparedStatement(String ps) throws SQLException {
	PreparedStatement statement = connection.prepareStatement(ps);
	ResultSet results = statement.executeQuery();
	return results;
    }


    public void executeVoidPreparedStatement(PreparedStatement ps) throws SQLException {
	ps.execute();
    }


    public void addUser(String userIn, String passwordIn, String nameIn) throws SQLException {
	String sql = "INSERT INTO users(username, password, fullname)" + "VALUES(?,?,?)";
	PreparedStatement statement = connection.prepareStatement(sql);
	statement.setString(1, userIn);
	statement.setString(2, passwordIn);
	statement.setString(3, nameIn);
	statement.execute();
    }    

    /*Search for a user: helpful for edits and deletes. */
    public boolean searchForUser(String userIn) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("select * from users");
	ResultSet results = statement.executeQuery();
	while (results.next()) {
	    String username = results.getString(1);
	    if (username.equals(userIn)) {
	       return true;
	    }
	}
	return false;
    }

    /*Search for a username and password */
    public boolean searchForUser(String userIn, String passwordIn) throws SQLException {
	PreparedStatement statement = connection.prepareStatement("select * from users");
	ResultSet results = statement.executeQuery();
	while (results.next()) {
	    String username = results.getString(1);
	    String password = results.getString(2);
	    if ((username.equals(userIn)) && (password.equals(passwordIn))) {
		  return true;
	     }
	}
     	return false;
    }
    

    

    /*Delete a user*/
    public void deleteUser(String userIn) throws SQLException {
	if (searchForUser(userIn) == true) {
	    String sql = "DELETE FROM users WHERE username=?";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setString(1, userIn);
	    statement.execute();
	}
    }

    
    /*Change an existing user's password.*/
    public void updateUserPassword(String userIn, String passwordIn) throws SQLException {
	if (searchForUser(userIn) == true) {
	   String sql = "UPDATE users SET password=? WHERE username=?";
	   PreparedStatement statement = connection.prepareStatement(sql);
	   statement.setString(1, passwordIn);
	   statement.setString(2,userIn);
	   statement.execute();
	}
    }

    /*Change an existing user's full name.*/
    public void updateUserFullName(String userIn, String fullNameIn) throws SQLException {
	if (searchForUser(userIn) == true) {
	    String sql = "UPDATE users SET fullname=? WHERE username=?";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setString(1, fullNameIn);
	    statement.setString(2,userIn);
	    statement.execute();
	}
    }

    /*Close the connection.*/
    public void close() throws SQLException {
	connection.close();
    }
}
