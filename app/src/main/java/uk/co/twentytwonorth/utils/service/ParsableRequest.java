package uk.co.twentytwonorth.utils.service;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import uk.co.twentytwonorth.free.parser.LoginParser;

/**
 * Created by colinlight on 30/11/15.  Class<? extends IMechanism>
 */
public class ParsableRequest<T extends IDataParser> extends Request<IDataParser> {

    public static final String TAG = "ParsableRequest";

    private final Map<String, String> headers;
    private final Response.Listener<IDataParser> listener;
    private IDataParser dataParser;

    /**
     *
     * @param method
     * @param url
     * @param headers
     * @param parser
     * @param listener
     * @param errorListener
     */
    public ParsableRequest(int method, String url, Map<String, String> headers, IDataParser parser,
                           Response.Listener<IDataParser> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.listener = listener;
        this.dataParser = parser;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(IDataParser response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<IDataParser> parseNetworkResponse(NetworkResponse response) {
        try{
            dataParser.parseData(response);
            return Response.success(dataParser, HttpHeaderParser.parseCacheHeaders(response));
        }catch(Exception e){
            Log.v(TAG, "there was a problem parsing the data");
            return Response.error(new ParseError(e));
        }
    }
}