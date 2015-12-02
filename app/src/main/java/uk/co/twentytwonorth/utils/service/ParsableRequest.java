package uk.co.twentytwonorth.utils.service;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONException;

import java.util.Map;

/**
 * Created by colinlight on 30/11/15.
 */
public class ParsableRequest<IDataParser> extends Request<IDataParser> {
    private final Map<String, String> headers;
    private final Response.Listener<IDataParser> listener;
    private final IDataParser dataParser;

    /**
     *
     * @param method
     * @param url
     * @param headers
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

        //this.dataParser
//        try {
//
//        }catch ( JSONException e){
//
//        }

//        try {
//            String json = new String(
//                    response.data, HttpHeaderParser.parseCharset(response.headers));
//            return Response.success(
//                    gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
//        } catch (UnsupportedEncodingException e) {
//            return Response.error(new ParseError(e));
//        } catch (JsonSyntaxException e) {
//            return Response.error(new ParseError(e));
//        }

        return null;
    }
}