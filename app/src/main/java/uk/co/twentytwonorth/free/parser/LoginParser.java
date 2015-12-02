package uk.co.twentytwonorth.free.parser;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import uk.co.twentytwonorth.utils.service.IDataParser;

/**
 * Created by colinlight on 02/12/15.
 */
public class LoginParser implements IDataParser {

    public static final String TAG = "LoginParser";
    public String errorMessage;
    public String token;

    public void parseData( NetworkResponse response )  throws Exception, IOException{
        try{
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            JSONObject jsonObject = new JSONObject(json);
            if ( jsonObject.has("errorMessage") ){
                errorMessage = jsonObject.getString("errorMessage");
            }else{
                 token = jsonObject.getString("access_token");
            }

        }catch(JSONException e){
            Log.e(TAG, "Login Parser failed");
        }
    }
}
