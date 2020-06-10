package edu.au.cc.gallery.tools;
import java.sql.SQLException;
public class UserAdmin {
    DB myDatabase;

    public UserAdmin() {
	myDatabase = new DB();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
	try {
	    admin.myDatabase.connect();
	}
	catch (SQLException ex) {
	    ex.printStackTrace();
	}
	System.out.println("Hello world testing!");
    }
}
