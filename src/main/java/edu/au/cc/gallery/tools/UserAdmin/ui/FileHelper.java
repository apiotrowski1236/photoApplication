package edu.au.cc.gallery.tools.UserAdmin.ui;
import spark.*;
import spark.utils.IOUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.*;
import static spark.Spark.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileNotFoundException;

public class FileHelper {


     private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
	}
