package edu.au.cc.gallery.tools.UserAdmin.ui;
import static spark.Spark.*;
import java.sql.SQLException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Map;
import java.io.File;
import java.util.HashMap;


import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import static spark.Spark.*;
import edu.au.cc.gallery.tools.UserAdmin.ui.UserAdminHelper;
import edu.au.cc.gallery.tools.UserAdmin.ui.RouteMapper;


public class UserAdmin {
    
    RouteMapper mapper;
    UserAdminHelper helper;
    public UserAdmin() {

	mapper = new RouteMapper();
	helper = new UserAdminHelper();
    }
    
    public static void main(String[] args) {
	UserAdmin admin = new UserAdmin();
	 admin.mapper.connectToServer();

	File uploadDir = new File("upload");
        uploadDir.mkdir(); 
	staticFiles.externalLocation("upload");
	admin.mapper.getAllRoutes();

	post("/admin/add/upload", (req, res) -> {

	    Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { 
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
	    
	    String path = tempFile.toString();
	    admin.helper.add(path);
	    return admin.helper.makeConfirmationPage(path, "addconfirmation.hbs");

        });
	
           }

   private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
