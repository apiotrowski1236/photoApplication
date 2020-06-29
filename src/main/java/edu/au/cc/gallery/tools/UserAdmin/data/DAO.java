package edu.au.cc.gallery.tools.data;
import java.util.List;

public interface DAO<T> {
    //add return types to this!
     List<T> getAll();
    void add(T t);
     void delete(T t);
}
