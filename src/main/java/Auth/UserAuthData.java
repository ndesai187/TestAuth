package Auth;

/**
 * Created by nirav on 29/4/17.
 */
public class UserAuthData {
    String username;
    String oauth_acc_Token;
    String oauth_acc_Secret;
    UserReqData reqData;

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOauth_acc_Token() {
        return oauth_acc_Token;
    }

    public void setOauth_acc_Token(String oauth_acc_Token) {
        this.oauth_acc_Token = oauth_acc_Token;
    }

    public String getOauth_acc_Secret() {
        return oauth_acc_Secret;
    }

    public void setOauth_acc_Secret(String oauth_acc_Secret) {
        this.oauth_acc_Secret = oauth_acc_Secret;
    }

    public UserReqData getReqData() {
        return reqData;
    }

    public void setReqData(UserReqData reqData) {
        this.reqData = reqData;
    }
}
