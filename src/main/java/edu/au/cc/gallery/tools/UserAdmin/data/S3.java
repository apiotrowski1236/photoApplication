package edu.au.cc.gallery.tools.UserAdmin.data;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.nio.file.Paths;

public class S3 {
    private AmazonS3 s3;
    private static final String bucket = "edu.au.cc.cc.image-gallery";
   
    public void connect() {
        s3 = AmazonS3ClientBuilder.standard().withRegion("us-east-2").build();
    }

    private String getKeyFromPath(String path) {
	String key = Paths.get(path).getFileName().toString();
	return key;

    }


    public void addPhoto(String path) {
	try {
	 String key = getKeyFromPath(path);
	 s3.putObject(bucket, key, new File(path));
        }
	
	catch (AmazonServiceException e) {
	    System.err.println(e.getErrorMessage());
	}
    }

    public void listPhotos(String bucket) {
	/*	try {
	 S3Object o = s3.getObject(bucket, key);
	 S3ObjectInputStream s3is = o.getObjectContent();
	 FileOutputStream fos = new FileOutputStream(new File(key_name));
	 byte[] read_buf = new byte[1024];
	 int read_len = 0;
	 while ((read_len = s3is.read(read_buf)) > 0) {
	     fos.write(read_buf, 0, read_len);
	 }
	 s3is.close();
	 fos.close();
	}
    catch (AmazonServiceException e) {
	System.err.println(e.getErrorMessage());
	System.exit(1);
    }

    catch (FileNotFoundException e) {
	System.err.println(e.getMessage());
	System.exit(1);
    }

    catch (IOException e) {
    System.err.println(e.getMessage());
    System.exit(1);
    } */
}

    public void deletePhoto(String path) {
	String key =  getKeyFromPath(path);
	try {
	    s3.deleteObject(bucket, key);
	}

	catch (AmazonServiceException e) {
	    System.err.println(e.getErrorMessage());
	    System.exit(1);
       }
    }
}
