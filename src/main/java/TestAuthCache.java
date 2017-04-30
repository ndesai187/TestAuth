import Auth.OBPApi;
import Auth.UserAuthUtil;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import net.sf.ehcache.Cache;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by nirav on 30/4/17.
 */
public class TestAuthCache {

    public static void main(String... args) {

        final OAuth10aService service = new ServiceBuilder()
                .apiKey("vlsfva5oncrzy22yvc431v5hafd4gnqnvfsmsg0l")
                .apiSecret("xmczcqs1pvnr0fangy5d4fufxap5v2freomlgoe0")
                .callback("https://www.niravkdesai.com/")
                .build(OBPApi.instance());


        OAuth1RequestToken requestToken;
        try {
            UserAuthUtil.addStartupCache("req_token");
            UserAuthUtil.addStartupCache("acc_token");
            String authUrl;
            String req_token;
            String auth_verifier;
            OAuth1AccessToken accessToken = null;
            OAuthRequest request;
            Response response;
            final Scanner in = new Scanner(System.in, "UTF-8");
            System.out.println("=== OAuth Workflow ===");
            System.out.println();
            System.out.println("How many times you want to test it >> ");
            int count = in.nextInt();
            // Skip the newline
            in.nextLine();

            for(int i=1; i<= count; i++ ) {
                requestToken = service.getRequestToken();

                // Obtain the Authorization URL
                System.out.println("Fetching the Authorization URL...");
                authUrl = service.getAuthorizationUrl(requestToken);
                UserAuthUtil.cacheReqToken(requestToken);
                System.out.println("(if your curious requestToken looks like this: " +
                        "'rawResponse'='" + requestToken.getRawResponse() + "')");
                System.out.println("(if your curious requestToken looks like this: " +
                        "'req key'='" + requestToken.getToken() + "')");
                System.out.println("Got the Authorization URL!");
                System.out.println("Now go and authorize ScribeJava here:");
                System.out.println(authUrl);


                // Fetch request token from Cache
                System.out.println("And paste the auth_token here");
                System.out.print(">>");
                req_token = in.nextLine();
                System.out.println();
                System.out.println("And paste the auth_verifier here");
                System.out.print(">>");
                auth_verifier = in.nextLine();
                System.out.println();

                // PRint Cache
                System.out.println(UserAuthUtil.printCache("req_token"));

                // Get access Token
                System.out.println("Trading the Request Token for an Access Token...");
                System.out.println(UserAuthUtil.getReqToken(req_token).getRawResponse());
                accessToken = service.getAccessToken(UserAuthUtil.getReqToken(req_token), auth_verifier);
                System.out.println("Got the Access Token!");
                System.out.println("(if your curious it looks like this: " + accessToken
                        + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
                System.out.println();


                // Now let's go and ask for a protected resource!
                System.out.println("Now we're going to access a protected resource...");
                request = new OAuthRequest(Verb.GET, "https://apisandbox.openbankproject.com/obp/v2.0.0/users/current");
                service.signRequest(accessToken, request);
                response = service.execute(request);
                System.out.println("Got it! Lets see what we found...");
                System.out.println();
                System.out.println(response.getCode());
                System.out.println(response.getBody());
            }

            System.out.println("Now we're going to access a protected resource...");
            final OAuthRequest request1 = new OAuthRequest(Verb.GET, "https://apisandbox.openbankproject.com/obp/v2.0.0/users/brucebat1/entitlements");
            service.signRequest(accessToken, request1);
            final Response response1 = service.execute(request1);
            System.out.println("Got it! Lets see what we found...");
            System.out.println();
            System.out.println(response1.getCode());
            System.out.println(response1.getBody());


            UserAuthUtil.shutdownCache();
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
