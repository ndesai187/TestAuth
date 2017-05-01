package Auth;

import APIObjects.UserInfo;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.exceptions.OAuthException;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;

import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by nirav on 23/4/17.
 */
public class APIAccessHandler {


    final OAuth10aService service = new ServiceBuilder()
            .apiKey("qc2iuygsmrqed2mi2bghqjupibvmq0vvhtvqzq44")
            .apiSecret("astbalgsoft1irrfnqf1pmntutlbbfq5qb0tqybw")
            .callback("http://testrecing.pagekite.me/AuthTest1/callBack/")
            .build(OBPApi.instance());
    private String userEndPoint = "https://apisandbox.openbankproject.com/obp/v2.0.0/users/current";


    public String getLoginPage() {

        OAuth1RequestToken requestToken;
        String authUrl = null;
        try {
            requestToken = service.getRequestToken();
            authUrl = service.getAuthorizationUrl(requestToken);
            UserAuthUtil.writeCacheReqToken(requestToken);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (OAuthException e) {
            System.out.println(" <<<>>> THERE is a AUTH EXCEPTION <<<>>>");
            e.printStackTrace();
        }

        return authUrl;
    }

    public String fetchAccessToken(String req_key, String authVerifier) {
        String status = null;

        if (UserAuthUtil.checkCacheReqKey(req_key)) {
            try {
                System.out.println("These are the request keys : ");
                UserAuthUtil.printCachebyName("req_token");
                System.out.println("This is the raw response for fetched token : ");
                System.out.println(UserAuthUtil.readCacheReqToken(req_key).getRawResponse());

                OAuth1AccessToken accessToken = service.getAccessToken(UserAuthUtil.readCacheReqToken(req_key), authVerifier);
                OAuthRequest requestUser = new OAuthRequest(Verb.GET, userEndPoint);
                service.signRequest(accessToken, requestUser);
                Response userInfoResponse = service.execute(requestUser);
                Gson gson = new Gson();
                //== Below code is helpful to parse List of objects in Response
                //Type type = new TypeToken<List<UserInfo>>() {}.getType();
                //List<UserInfo> currentUserInfo = gson.fromJson(userInfoResponse.getBody(), type);
                UserInfo currentUserInfo = gson.fromJson(userInfoResponse.getBody(), UserInfo.class);
                UserAuthUtil.writeCacheAccToken(currentUserInfo.getUsername(), UserAuthUtil.readCacheReqToken(req_key), accessToken);
                //System.out.println(currentUserInfo.getUserId());
                //System.out.println(currentUserInfo.getUsername());


                System.out.println("This is the raw response for ACCESS token : ");
                System.out.println(accessToken.getRawResponse());
                System.out.println("This is user info : ");
                System.out.println(userInfoResponse.getCode());
                System.out.println(userInfoResponse.getBody());
                System.out.println("These are the access keys : ");
                UserAuthUtil.printCachebyName("acc_token");

                status = "success";

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        } else {
            status = "failure";
        }

        return status;
    }

    public Response getAPIResponse(String username, String apiURL) {
        Response applResponse = null;
        OAuth1AccessToken apiAccessToken;
        try {
            if (UserAuthUtil.checkCacheAccToken(username) || username.isEmpty()) {

                OAuthRequest requestResource = new OAuthRequest(Verb.GET, apiURL);

                if (UserAuthUtil.checkCacheAccToken(username)) {
                    apiAccessToken = UserAuthUtil.readCacheAccToken(username);
                    service.signRequest(apiAccessToken, requestResource);
                }

                Response apiResponse = service.execute(requestResource);

                if (apiResponse.getCode() == 200) {
                    //applResponse = apiResponse;
                    applResponse = new Response(apiResponse.getCode(), apiResponse.getMessage() ,apiResponse.getHeaders(), apiResponse.getBody());
                } else if (apiResponse.getCode() == 400 || apiResponse.getCode() == 401) {
                    applResponse = new Response(401, apiResponse.getMessage(),apiResponse.getHeaders(),getLoginPage());
                } else {
                    // create message response here with 999 code.
                    applResponse = new Response(apiResponse.getCode(), apiResponse.getMessage() ,apiResponse.getHeaders(), apiResponse.getBody());
                    ///applResponse = new Response(444, apiResponse.getMessage() ,apiResponse.getHeaders(), apiResponse.getBody());
                }

            } else {
                // redirect to login URL with code 401
                final Map<String, String> headers = null;
                applResponse = new Response(401, "You are not registered to use this App",
                        headers, getLoginPage());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("This is Response " + applResponse);
        return applResponse;
    }


}
