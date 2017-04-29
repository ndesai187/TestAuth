package Auth;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by nirav on 29/4/17.
 */
public final class UserAuthUtil {

    static CacheManager cm = CacheManager.getInstance();
    static Cache cache_acc_token = cm.getCache("acc_token");

    public static void addStartupCache(String cachename){
        cm.addCache(cachename);
        System.out.println("Added : " + cm.getCache(cachename));
    }

    public static void cacheReqToken(OAuth1RequestToken reqToken){
        //Cache cache_req_token = cm.getCache("req_token");
        cm.getCache("req_token").put(new Element(reqToken.getToken(), reqToken));
    }

    public static boolean checkReqKey(String reqKey){
        return cm.getCache("req_token").isKeyInCache(reqKey);
    }

    public static OAuth1RequestToken getReqToken(String reqKey){
        return (OAuth1RequestToken) cm.getCache("req_token").get(reqKey).getObjectValue();
    }

    public static void cacheAccToken(OAuth1AccessToken accToken){
        Cache cache_acc_token = cm.getCache("acc_token");
        Cache cache_req_token = cm.getCache("req_token");

        //if(cache_req_token.isKeyInCache())

    }

}
