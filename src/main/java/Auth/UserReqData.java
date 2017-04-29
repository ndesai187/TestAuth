package Auth;

/**
 * Created by nirav on 30/4/17.
 */
public class UserReqData {
    String oauth_req_Token;
    String oauth_req_Secret;
    String oauth_Callback_flag;

    public String getOauth_req_Token() {
        return oauth_req_Token;
    }

    public void setOauth_req_Token(String oauth_req_Token) {
        this.oauth_req_Token = oauth_req_Token;
    }

    public String getOauth_req_Secret() {
        return oauth_req_Secret;
    }

    public void setOauth_req_Secret(String oauth_req_Secret) {
        this.oauth_req_Secret = oauth_req_Secret;
    }

    public String getOauth_Callback_flag() {
        return oauth_Callback_flag;
    }

    public void setOauth_Callback_flag(String oauth_Callback_flag) {
        this.oauth_Callback_flag = oauth_Callback_flag;
    }
}
