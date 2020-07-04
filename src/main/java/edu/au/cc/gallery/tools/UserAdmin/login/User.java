package edu.au.cc.gallery.tools.UserAdmin.login;
public class User {
  private String username;
  private String password;
    private String fullname;

    public User(String username, String password, String fullname) {
	setUserName(username);
	setPassword(password);
	setFullName(fullname);
    }

    public String getUserName() {
	return this.username;
    }

    public String getPassword() {
	return this.password;
    }

    public String getFullName() {
	return this.fullname;
    }

    public void setUserName(String usernameIn) {
	this.username = usernameIn;
    }

    public void setPassword(String passwordIn) {
	this.password = passwordIn;
    }

    public void setFullName(String fullnameIn) {
	this.fullname = fullnameIn;
    }
}
