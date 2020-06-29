package edu.au.cc.gallery.tools.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class PostgresDAO implements UserDAO {
    private DB database;

    public PostgresDAO() throws SQLException {
	database = new DB();
	database.connect();
    }

    
 public JSONArray  getUsers() throws SQLException {
     JSONArray ja = new JSONArray();
     ResultSet rs = database.executePreparedStatement("select * from users");

     while(rs.next()) {
	 String username = rs.getString(1);
	 String password = rs.getString(2);
	 String fullname = rs.getString(3);
	 JSONObject jo = new JSONObject();
	 jo.put("username", username);
	 jo.put("password", password);
	 jo.put("fullname", fullname);
	 ja.add(jo);  
      }
     rs.close();
     return ja;
    }

    public void addUser(String userIn, String passwordIn, String nameIn) throws SQLException {
	database.addUser(userIn, passwordIn, nameIn);
    }

     public void updateUserFullName(String userIn, String fullNameIn) throws SQLException {
	 database.updateUserFullName(userIn, fullNameIn);
     }

    public void updateUserPassword(String userIn, String passwordIn) throws SQLException {
	database.updateUserPassword(userIn, passwordIn);
    }

     public void deleteUser(String userIn) throws SQLException {
	 database.deleteUser(userIn);
     }
    
    
}
