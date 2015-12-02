package uk.co.twentytwonorth.free.helper;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;

import java.io.IOException;
import java.util.Map;

import uk.co.twentytwonorth.free.parser.CurrentUserParser;
import uk.co.twentytwonorth.free.parser.LoginParser;
import uk.co.twentytwonorth.utils.service.IDataParser;
import uk.co.twentytwonorth.utils.service.ParsableRequest;

/**
 * Created by colinlight on 02/12/15.
 */
public class FreeServiceRouter {

    static final String TAG = "FreeServiceRouter";
    static final String baseURL = "https://demo.isitfree.co/";
    static String oAuthToken;

    public enum Action{
        POSTTOKEN, GET_CURRENT_USER, GET_ALL_SPACES;

        private String path( Map<String, String> params ) throws Exception {
            String currentPath = "";

                switch (this) {
                    case POSTTOKEN:
                        currentPath =  "oauth/token?grant_type=password";
                        break;
                    case GET_CURRENT_USER:
                        currentPath =  "api/users/current";
                        break;
                    case GET_ALL_SPACES:
                        if (params.containsKey("type") == false) {
                            throw new IOException( "Couldn't set action params" );
                        }
                        currentPath =  "api/v1.0/" + params.get("type") + "/all";
                        break;
                }

            return currentPath;
        }

        private int method() {
            switch (this) {
                case POSTTOKEN:
                    return Request.Method.POST;
                case GET_CURRENT_USER:
                    return Request.Method.GET;
            }
            return Request.Method.GET;
        }

        private IDataParser parser(){
            switch (this) {
                case POSTTOKEN:
                    return new LoginParser();
                case GET_CURRENT_USER:
                    return new CurrentUserParser();
                default:
                    return null;
            }
        }

        public Map<String, String> headers(){
            return null;
        }

    }

    public static ParsableRequest createRequest( Action action, final Map<String, String> params,
                                                 Response.Listener<IDataParser> listener,
                                                 Response.ErrorListener errorListener){
        String url = "";
        try{
           url = baseURL + action.path( null );
        }catch( Exception e ){
            Log.e(TAG, "Couldn't set action params");
        }
        return new ParsableRequest(  action.method(), url, action.headers(),
                action.parser(), listener, errorListener ){
            protected Map<String, String>getParams() throws AuthFailureError{
                return params;
            }
        };
    }


}
