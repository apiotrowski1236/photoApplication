package edu.au.cc.gallery.tools.UserAdmin.login;
import java.util.List;

public interface UserDAO<T> {

    List<T> getUsers() throws Exception;
    void addUser(String userIn, String passwordIn, String nameIn) throws Exception;
    void updateUserFullName(String userIn, String fullNameIn) throws Exception;
    void updateUserPassword(String userIn, String passwordIn) throws Exception;
    void deleteUser(String userIn) throws Exception;
    boolean searchForUser(String userIn, String passwordIn) throws Exception;
}
