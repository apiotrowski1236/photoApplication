package edu.au.cc.gallery.tools.data;
import java.util.List;
import java.util.ArrayList;
public class PhotoDAO implements DAO<Photo> {
    private S3 s3;
    private List<Photo> photos = new ArrayList<>();

    public PhotoDAO() {
	s3 = new S3();
	s3.connect();
    }

    public List<Photo> getAll() {
	return photos;
    }


    public void add(Photo photo) {

    }

    public void delete(Photo photo) {

    }
  




}
