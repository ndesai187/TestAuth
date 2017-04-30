package Auth;

import APIObjects.UserInfo;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
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


    public String getLoginPage (){

        OAuth1RequestToken requestToken;
        String authUrl = null;
        try {
            requestToken = service.getRequestToken();
            authUrl = service.getAuthorizationUrl(requestToken);
            UserAuthUtil.cacheReqToken(requestToken);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return authUrl;
    }

    public String fetchAccessToken(String req_key, String authVerifier){

        if(UserAuthUtil.checkReqKey(req_key)){
            try {
                System.out.println("This are the keys : ");
                System.out.println(UserAuthUtil.printCache("req_token"));

                System.out.println("This is the raw response for fetched token : ");
                System.out.println(UserAuthUtil.getReqToken(req_key).getRawResponse());

                OAuth1AccessToken accessToken = service.getAccessToken(UserAuthUtil.getReqToken(req_key),authVerifier);
                OAuthRequest requestUser = new OAuthRequest(Verb.GET, userEndPoint);
                service.signRequest(accessToken, requestUser);
                Response userInfoResponse = service.execute(requestUser);
                Gson gson = new Gson();
                //Type type = new TypeToken<List<UserInfo>>() {}.getType();
                //List<UserInfo> currentUserInfo = gson.fromJson(userInfoResponse.getBody(), type);
                UserInfo currentUserInfo = gson.fromJson(userInfoResponse.getBody(), UserInfo.class);
                System.out.println(currentUserInfo.getUserId());
                System.out.println(currentUserInfo.getUsername());

                UserAuthUtil.cacheAccToken(UserAuthUtil.getReqToken(req_key), accessToken);

                System.out.println("This is the raw response for ACCESS token : ");
                System.out.println(accessToken.getRawResponse());
                System.out.println("This is user info : ");
                System.out.println(userInfoResponse.getCode());
                System.out.println(userInfoResponse.getBody());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        return "success";
    }





}
