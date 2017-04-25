package Auth;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.Scanner;
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

    public String getLoginPage (){

        final OAuth1RequestToken requestToken;
        String authUrl = null;
        try {
            requestToken = service.getRequestToken();
            authUrl = service.getAuthorizationUrl(requestToken);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return authUrl;
    }





}
