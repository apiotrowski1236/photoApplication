package edu.au.cc.gallery.tools.UserAdmin.login;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import edu.au.cc.gallery.tools.UserAdmin.login.User;
import edu.au.cc.gallery.tools.UserAdmin.login.DB;

public class PostgresDAO implements UserDAO {
    private DB database;

    public PostgresDAO() throws SQLException {
	database = new DB();
	database.connect();
    }

    
 public ArrayList<User>  getUsers() throws SQLException {
     ArrayList<User> users = new ArrayList<User>();
     ResultSet rs = database.executePreparedStatement("select * from users");

     while(rs.next()) {
	 String username = rs.getString(1);
	 String password = rs.getString(2);
	 String fullname = rs.getString(3);
	 User user = new User(username, password, fullname);
	 users.add(user);
      }
     rs.close();
     return users;
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

    public boolean searchForUser(String userIn, String passwordIn) throws SQLException {
	return database.searchForUser(userIn, passwordIn);
    }
}