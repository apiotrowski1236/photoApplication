package edu.au.cc.gallery.tools.UserAdmin.data;

public class daoMaker {
    public static DAO getPhotoDAO() {
	return new PhotoDAO();
    }
}
