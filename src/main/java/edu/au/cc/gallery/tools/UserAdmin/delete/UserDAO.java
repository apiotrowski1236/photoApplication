package edu.au.cc.gallery.tools.data;
import org.json.simple.JSONArray;

public interface UserDAO {

    JSONArray getUsers() throws Exception;
    void addUser(String userIn, String passwordIn, String nameIn) throws Exception;
    void updateUserFullName(String userIn, String fullNameIn) throws Exception;
    void updateUserPassword(String userIn, String passwordIn) throws Exception;
    void deleteUser(String userIn) throws Exception;
}
