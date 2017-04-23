import JSONrequests.Hello;

import javax.ws.rs.*;

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
    public Hello sayHelloJSON() {

        Hello hello = new Hello();
        hello.setMessage("Say hello to everyone");

        return hello;

    }


}
