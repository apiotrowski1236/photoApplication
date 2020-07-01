package edu.au.cc.gallery.tools.UserAdmin.data;
import java.io.File;
import java.nio.file.Paths; 
public class Photo {
    private String file;
    private String owner;

    public Photo(String file, String owner) {
	setFile(file);
	setOwner(owner);
    }

    private String createKeyFromPath(String path) {
	String key = Paths.get(path).getFileName().toString();
	return key;
    }
    
    
    public String getFile() {
        return this.file;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setFile(String fileIn) {
        this.file = fileIn;
    }

    public void setOwner(String ownerIn) {
	this.owner = ownerIn;
    }

    
}
