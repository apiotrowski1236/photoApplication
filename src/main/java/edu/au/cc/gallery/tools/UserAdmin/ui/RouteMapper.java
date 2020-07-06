package edu.au.cc.gallery.tools.UserAdmin.ui;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import edu.au.cc.gallery.tools.UserAdmin.ui.FileHelper;
import edu.au.cc.gallery.tools.UserAdmin.authentication.Authenticator;
import edu.au.cc.gallery.tools.UserAdmin.login.AdminHelper;
import edu.au.cc.gallery.tools.UserAdmin.ui.PhotoHelper;
public class RouteMapper {

    public RouteMapper() {
    }
    
    /**Connection and Routing Methods */
   public void getAllRoutes() {
      getWebAppPages();
      secureRoutes();
      getRoutesToS3();
      getSessionRoutes();
      getAdminOnlyRoutes();
    }


/**This method opens up a connection to the server. It will try to use Jetty as the main server,                     
but if that fails, it'll use nginx as a backup on port 5000. Nginx is running as a proxy server to Jetty.*/
    public  void connectToServer() {
        String portString = System.getenv("JETTY_PORT");
        //If we can't access JETTY_PORT, choose 5000 as a backup                                                     
        if ((portString == null) || (portString.equals(""))) {
            port(5000);
        }
        else {
            port(Integer.parseInt(portString));
        }
    }

    private void secureRoutes() {
	before("/admin/*", (req, res) -> Authenticator.checkAdmin(req, res));
    }

    
    private void getWebAppPages() {
        get("/admin",  (req, res) -> PhotoHelper.adminModelMaker(req, res, "admin.hbs"));
        get("/", (req, res) -> PhotoHelper.modelMaker(req, res, "login.hbs"));
        get ("/hello", (req, res) -> "Hello Testing!");
	get("/login/view", (req, res) -> PhotoHelper.list(req,res));
	get("/login/add", (req, res) -> PhotoHelper.adminModelMaker(req, res, "add.hbs"));
	get("/login/welcome", (req, res) -> PhotoHelper.adminModelMaker(req, res, "welcome.hbs"));
    }

    private void getSessionRoutes() {
	post("/login", (req, res) -> Authenticator.login(req, res));
	get("/sessionDebug", (req, res) -> Authenticator.debug(req, res)); 
    }
    

    private void getRoutesToS3() {
	 get("/login/list", (req, res) -> PhotoHelper.list(req, res));
          get("/login/delete", (req, res) -> PhotoHelper.delete(req, res));
	    }

    private void getAdminOnlyRoutes() {
	get("/admin/listPhotos", (req, res) -> PhotoHelper.list(req, res));
	get("/admin/list", (req, res) -> AdminHelper.list(req, res));
	get("/admin/change", (req, res) -> AdminHelper.adminModelMaker(req, res, "admin_change.hbs"));
	get("/admin/confirmadd", (req, res) -> AdminHelper.adminModelMaker(req, res, "admin_addconfirmation.hbs"));
	post("admin/add", (req, res) -> AdminHelper.add(req, res));
	get("/admin/add", (req, res) -> AdminHelper.adminModelMaker(req, res, "admin_add.hbs"));
	get("/admin/changeconfirmation", (req, res) -> AdminHelper.adminModelMaker(req, res, "admin_changeconfirmation.hbs"));
	get("/admin/deleteconfirmation", (req, res) -> AdminHelper.adminModelMaker(req, res, "admin_deleteconfirmation.hbs"));
   }
    
}
