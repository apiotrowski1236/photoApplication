package edu.au.cc.gallery.tools.UserAdmin.data;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.EmailAddressGrantee;
import com.amazonaws.services.s3.model.Permission;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;


public class S3 {

    private AmazonS3 s3;
    //    private static final String bucket = System.getenv("S3_IMAGE_BUCKET");
    private String bucket = "edu.au.cc.cc.image-gallery";
    private static final String access = "Read";

    //Connect to S3 service.
    public void connect() {
        s3 = AmazonS3ClientBuilder.standard().withRegion("us-east-2").build();
        if (System.getenv("S3_Bucket") != null) {
	    bucket = System.getenv("S3_Bucket");
	}
    }


    //Helper method.
    private String getKeyFromPath(String path, String owner) {
	String key = owner + "/" + Paths.get(path).getFileName().toString();
	return key;
    }


    //Add a photo to your personal files on the S3 bucket.
    public void addPhoto(String path, String owner) {
	try {
	    String key = getKeyFromPath(path, owner);
	    s3.putObject(bucket, key, new File(path));
	    //setACL(bucket, owner, key);
        }
	
	catch (AmazonServiceException e) {
	    System.err.println(e.getErrorMessage());
	}
    }
    

    //List all the photos that you own from the S3 bucket.
    
    public ArrayList<Photo> listPhotos(String searcher) {
	ListObjectsV2Result result = s3.listObjectsV2(bucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
	return makePhotoArray(objects, searcher);
     }
    
    private ArrayList<Photo> makePhotoArray(List<S3ObjectSummary> objects, String searcher) {
       ArrayList<Photo> photos = new ArrayList<Photo>();
	for (S3ObjectSummary os : objects) {
	    String path = "https://s3-us-east-2.amazonaws.com/" + bucket + "/";
	    String key = os.getKey();
	     int endOfOwner = key.indexOf('/');
	    String owner = key.substring(0, endOfOwner);
	       if (owner.equals(searcher)) {
		path += key;
		Photo photo = new Photo(path, searcher);
		photos.add(photo);
	    } 
	}
	return photos;
    }

   
    public void deletePhoto(String key) {
	//String key =  getKeyFromPath(path, owner);
	try {
	    s3.deleteObject(bucket, key);
	}

	catch (AmazonServiceException e) {
	    System.err.println(e.getErrorMessage());
	        }
    }

    //Helper methods that set and search for ACLS.

        public  void setACL(String bucket, String owner, String key) {
	    try {
		// get the current ACL
		AccessControlList acl = s3.getObjectAcl(bucket, key);
		// set access for the grantee
		EmailAddressGrantee grantee = new EmailAddressGrantee(owner);
		Permission permission = Permission.valueOf(access);
		acl.grantPermission(grantee, permission);
		s3.setObjectAcl(bucket, key, acl);
	    }

	    catch (AmazonServiceException e) {
		System.err.println(e.getErrorMessage());
		    }
    }


    public  boolean searchForACL(String bucket, String searcher, String key) {
        try {
            AccessControlList acl = s3.getObjectAcl(bucket,key);
            List<Grant> grants = acl.getGrantsAsList();
            for (Grant grant : grants) {
                String owner = grant.getGrantee().getIdentifier().toString();
                if (owner.equals(searcher)) {
                    return true;
                }
            }
        }
	catch (AmazonServiceException e) {
	    System.err.println(e.getErrorMessage());
		}
	return false;
    }
}
