package Auth;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


/**
 * Created by nirav on 16/4/17.
 */
public class TestAuth {

    public static void main(String... args) {

        final OAuth10aService service = new ServiceBuilder()
                .apiKey("vlsfva5oncrzy22yvc431v5hafd4gnqnvfsmsg0l")
                .apiSecret("xmczcqs1pvnr0fangy5d4fufxap5v2freomlgoe0")
                .callback("https://www.niravkdesai.com/")
                .build(OBPApi.instance());

        final OAuth1RequestToken requestToken;
        try {
            requestToken = service.getRequestToken();


            final Scanner in = new Scanner(System.in, "UTF-8");

            System.out.println("=== OAuth Workflow ===");
            System.out.println();

            // Obtain the Authorization URL
            System.out.println("Fetching the Authorization URL...");
            String authUrl = service.getAuthorizationUrl(requestToken);
            System.out.println("Got the Authorization URL!");
            System.out.println("Now go and authorize ScribeJava here:");
            System.out.println(authUrl);
            System.out.println("And paste the authorization auth_verifier here");
            System.out.print(">>");
            final String auth_verifier = in.nextLine();
            System.out.println();

            //System.out.print(">>");
            //final String value = in.nextLine();

            // Trade the Request Token and Verfier for the Access Token
            System.out.println("Trading the Request Token for an Access Token...");

            final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, auth_verifier);

            System.out.println("Got the Access Token!");
            System.out.println("(if your curious it looks like this: " + accessToken
                    + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
            System.out.println();

            // Now let's go and ask for a protected resource!
            System.out.println("Now we're going to access a protected resource...");
            final OAuthRequest request = new OAuthRequest(Verb.GET, "https://apisandbox.openbankproject.com/obp/v2.0.0/users/current");
            service.signRequest(accessToken, request);
            final Response response = service.execute(request);
            System.out.println("Got it! Lets see what we found...");
            System.out.println();
            System.out.println(response.getCode());
            System.out.println(response.getBody());

            System.out.println();
            System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
