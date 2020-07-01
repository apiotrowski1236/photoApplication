package edu.au.cc.gallery.tools.UserAdmin.data;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import edu.au.cc.gallery.tools.UserAdmin.data.S3;
public class PhotoDAO implements DAO<Photo> {
    private S3 s3;
    private ArrayList<Photo> photos;
    public PhotoDAO() {
	s3 = new S3();
	s3.connect();
    }

    public List<Photo> getAll() {
	return photos;
    }

    public void add(Photo photo) {
	String path  = photo.getFile();
	s3.addPhoto(path);
    }
    public void delete(Photo photo) {
	String path = photo.getFile();
	s3.deletePhoto(path);
    }
  




}
