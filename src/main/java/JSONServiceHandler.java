import Auth.APIAccessHandler;
import Auth.OBPApi;
import JSONrequests.AuthRequestResponse;
import JSONrequests.MessageResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by nirav on 23/4/17.
 */

@Path("/")
public class JSONServiceHandler {

    @GET
    @Path("/welcome")
    @Produces("application/json")
    public String sayHiJSON() {

        String hi = "Welcome to new Reckoning";
        return hi;

    }

    @GET
    @Path("/hello")
    @Produces("application/json")
    public MessageResponse sayHelloJSON() {

        MessageResponse hello = new MessageResponse();
        hello.setMessage("Say hello to everyone");

        return hello;

    }

    @GET
    @Path("/initiate")
    @Produces("application/json")
    public AuthRequestResponse goToLoginPage(){
        AuthRequestResponse loginPage = new AuthRequestResponse();
        APIAccessHandler session = new APIAccessHandler();

        loginPage.setLoginURL(session.getLoginPage());
        if (session.getLoginPage().equals(null)){
            loginPage.setStatus("Failed");
        } else {
            loginPage.setStatus("Success");
        }
        return loginPage;
    }

    @GET
    @Path("/callBack")
    @Produces("application/json")
    public MessageResponse welcomePage(){
        MessageResponse welcomeText = new MessageResponse();
        welcomeText.setMessage("you have successfully logged on! but I don't have anything for you now :)");

        return welcomeText;
    }
}
