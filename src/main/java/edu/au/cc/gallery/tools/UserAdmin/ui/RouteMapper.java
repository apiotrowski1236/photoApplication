package edu.au.cc.gallery.tools.UserAdmin.ui;
import static spark.Spark.*;
import spark.Request;
import spark.Response;
import edu.au.cc.gallery.tools.UserAdmin.ui.FileHelper;
import edu.au.cc.gallery.tools.UserAdmin.authentication.Authenticator;
public class RouteMapper {

    public RouteMapper() {
    }
    
    /**Connection and Routing Methods */
   public void getAllRoutes() {
      getWebAppPages();
      secureRoutes();
      getRoutesToS3();
      getSessionRoutes();
      // getUploadRoutes();
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
        get("/admin",  (req, res) -> UserAdminHelper.adminModelMaker(req, res, "admin.hbs"));
        get("/", (req, res) -> UserAdminHelper.modelMaker(req, res, "login.hbs"));
        get ("/hello", (req, res) -> "Hello Testing!");
	get("/admin/view", (req, res) -> UserAdminHelper.list(req,res));
	get("/admin/add", (req, res) -> UserAdminHelper.adminModelMaker(req, res, "add.hbs"));
	get("/user", (req, res) -> UserAdminHelper.modelMaker(req, res, "regular.hbs"));
    }

    private void getSessionRoutes() {
	post("/login", (req, res) -> Authenticator.login(req, res));
	get("/sessionDebug", (req, res) -> Authenticator.debug(req, res)); 
    }
    

    private void getRoutesToS3() {
	 get("/admin/list", (req, res) -> UserAdminHelper.list(req, res));
          get("/admin/delete", (req, res) -> UserAdminHelper.delete(req, res));
	    }

}
