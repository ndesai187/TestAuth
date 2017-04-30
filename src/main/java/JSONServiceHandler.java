import Auth.APIAccessHandler;
import JSONResponse.AuthRequestResponse;
import JSONResponse.MessageResponse;

import javax.ws.rs.*;

/**
 * Created by nirav on 23/4/17.
 */

@Path("/")
public class JSONServiceHandler {
    APIAccessHandler session = new APIAccessHandler();

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
        hello.setMessage("Hello, I am online!");

        return hello;

    }

    @GET
    @Path("/initiate")
    @Produces("application/json")
    public AuthRequestResponse goToLoginPage(){
        AuthRequestResponse loginPage = new AuthRequestResponse();

        loginPage.setLoginURL(session.getLoginPage());
        if (loginPage.getLoginURL().equals(null)){
            loginPage.setStatus("Failure");
        } else {
            loginPage.setStatus("Success");
        }
        return loginPage;
    }

    @GET
    @Path("/callBack")
    @Produces("application/json")
    public MessageResponse welcomePage(
            @QueryParam("oauth_token") String auth_token,
            @QueryParam("oauth_verifier") String auth_verifier){

        String status = session.fetchAccessToken(auth_token, auth_verifier);

        MessageResponse welcomeText = new MessageResponse();
        welcomeText.setMessage("you have successfully logged on! but I don't have anything for you now :)" + status);

        return welcomeText;
    }

    @GET
    @Path("/userInfo")
    @Produces("application/json")
    public void getUserDetails(
            @QueryParam("user") String username){



    }
}
