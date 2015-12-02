package uk.co.twentytwonorth.free.model.vo;

/**
 * Created by colinlight on 30/11/15.
 */
public class LoginVO {
    String access_token;
    String token_type;
    int expires_in;

    public LoginVO(  String access_token,  String token_type, int expires_in){
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }


}
