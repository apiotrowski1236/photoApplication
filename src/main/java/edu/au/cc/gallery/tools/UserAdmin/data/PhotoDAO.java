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

    public ArrayList<Photo> listPhotos(String searcher) {
	photos = s3.listPhotos(searcher);
	return photos;
    }

    public void add(Photo photo) {
	String path  = photo.getFile();
	String owner = photo.getOwner();
	s3.addPhoto(path, owner);
    }
    public void delete(Photo photo) {
	String path = photo.getFile();
	String key = path.substring(62);
	System.out.println(key);
	s3.deletePhoto(key);
    }
}
