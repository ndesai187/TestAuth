import Auth.OBPApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;

import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by nirav on 1/5/17.
 */
public class TestAuthErrorCodes {

    final static OAuth10aService service = new ServiceBuilder()
            .apiKey("vlsfva5oncrzy22yvc431v5hafd4gnqnvfsmsg0l")
            .apiSecret("xmczcqs1pvnr0fangy5d4fufxap5v2freomlgoe0")
            .callback("https://www.niravkdesai.com/")
            .build(OBPApi.instance());

    public static void main(String... args) {
        //testAuthTokenValidity();
        //testReqTokenValidity();

        String checknull = "";
        if(checknull.isEmpty()){
            System.out.println("It is null");
        } else {
            System.out.println("Doesn't Work :( ");
        }
    }

    public static void testAuthTokenValidity() {
        OAuth1AccessToken accessToken = new OAuth1AccessToken("Y40ETZBNRH2DIN34M4EWfYGAX3T3F0HXGHDVBHU0", "SLICK4BU153YXVYVX4RUUEI4ODFVRRSHPTYYATRR");

        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://apisandbox.openbankproject.com/obp/v2.0.0/users/current");
        service.signRequest(accessToken, request);
        final Response response;
        try {
            response = service.execute(request);
            System.out.println("Got it! Lets see what we found...");
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            System.out.println(response.getMessage());

            Response dummy = new Response(999,response.getMessage(),response.getHeaders(),"Custom Response");
            System.out.println(dummy.getCode());
            System.out.println(dummy.getBody());
            System.out.println(dummy.getHeaders());
            System.out.println(dummy.getMessage());

        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (OAuthException e){
            System.out.println("OAuthException");
            e.printStackTrace();
        }

    }

    public static void testReqTokenValidity() {

        OAuth1RequestToken requestToken = new OAuth1RequestToken("OBCNX5JS02HB5YSZVWTYAQW523R2UH241RVMKHJM","WIDLBUPCL1WMIJERZGQLI4ALRS4WP4JTGQNQIBMJ");
        OAuth1AccessToken accessToken = null;
        try {
            accessToken = service.getAccessToken(requestToken, "45098");
            System.out.println(accessToken.getRawResponse());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } catch (OAuthException e){
            System.out.println("OAuthException");
            e.printStackTrace();
        }

    }
}
