package edu.au.cc.gallery.tools.UserAdmin.data;
import java.util.List;

public interface DAO<T> {
    List<T> listPhotos(String o);
    void add(T t);
    void delete(T t);
}
