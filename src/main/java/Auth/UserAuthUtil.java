package Auth;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.util.List;

/**
 * Created by nirav on 29/4/17.
 */
public final class UserAuthUtil {

    static CacheManager cm = CacheManager.getInstance();
    //static Cache cache_acc_token = cm.getCache("acc_token");

    public static void addStartupCache(String cachename){
        cm.addCache(cachename);
        System.out.println("Added : " + cm.getCache(cachename));
    }

    public static void cacheReqToken(OAuth1RequestToken reqToken){
        cm.getCache("req_token").put(new Element(reqToken.getToken(), reqToken));
    }

    public static boolean checkReqKey(String reqKey){
        return cm.getCache("req_token").isKeyInCache(reqKey);
    }

    public static OAuth1RequestToken getReqToken(String reqKey){
        return (OAuth1RequestToken) cm.getCache("req_token").get(reqKey).getObjectValue();
    }

    public static void cacheAccToken(OAuth1RequestToken reqToken, OAuth1AccessToken accToken){


        //cm.getCache("acc_token").put(new Element(username, UserAuthData1));
        //if(cache_req_token.isKeyInCache())

    }

    public static void shutdownCache(){
        cm.shutdown();
    }

    public static List printCache(String cachename){
        return cm.getCache(cachename).getKeys();
    }

}
