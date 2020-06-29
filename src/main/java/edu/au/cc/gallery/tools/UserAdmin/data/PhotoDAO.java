import java.util.List;
import java.util.ArrayList;
public class PhotoDAO implements DAO<Photo> {
    private List<Photo> photos = new ArrayList<>();

    public PhotoDAO() {

    }

    public List<Photo> getAll() {
	return photos;
    }


    public void add(Photo photo) {

    }

    public void delete(Photo photo) {

    }
  




}
