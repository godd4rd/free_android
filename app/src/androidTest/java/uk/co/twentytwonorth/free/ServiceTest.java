package uk.co.twentytwonorth.free;

import android.test.AndroidTestCase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import uk.co.twentytwonorth.free.model.vo.LoginVO;
import uk.co.twentytwonorth.utils.service.GsonRequest;
import uk.co.twentytwonorth.utils.service.IDataParser;
import uk.co.twentytwonorth.utils.service.ParsableRequest;


/**
 * Created by colinlight on 26/11/15.
 */
public class ServiceTest extends AndroidTestCase {

    RequestQueue mQueue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        //Cache cahce = new DiskBasedCache(getC)
       // Network network = new BasicNetwork(new HurlStack());
        //mQueue = new RequestQueue(null, network);
        mQueue = Volley.newRequestQueue(this.getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_login_returns_a_valid_user() throws InterruptedException{

        CountDownLatch countDown = new CountDownLatch(1);

        String url = "https://demo.isitfree.co/oauth/token?grant_type=password";
        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Success", "Success->");
                        assertTrue(true);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.v("Fail", "Fail->");
                        assertFalse(true);
                    }
                }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username", "colin.light@googlemail.com");
                params.put("password", "colinisbest");
                return params;
            }
        };

        mQueue.add(stringRequest);
        countDown.await(5, TimeUnit.SECONDS);

    }


    public void test_login_returns_a_valid_json_user() throws InterruptedException{

        CountDownLatch countDown = new CountDownLatch(1);

        String url = "https://demo.isitfree.co/oauth/token?grant_type=password";

        GsonRequest request = new GsonRequest(  Request.Method.POST, url, null, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Success", "Success->");
                        assertTrue(true);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.v("Fail", "Fail->");
                        assertFalse(true);
                    }
                }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username", "colin.light@googlemail.com");
                params.put("password", "colinisbest");
                return params;
            }
        };

        mQueue.add(request);
        countDown.await(30, TimeUnit.SECONDS);

    }


    public void test_login_returns_a_valid_json_user_using_parser() throws InterruptedException{

        CountDownLatch countDown = new CountDownLatch(1);

        String url = "https://demo.isitfree.co/oauth/token?grant_type=password";

        ParsableRequest request = new ParsableRequest(  Request.Method.POST, url, null,
                new Response.Listener<IDataParser>() {
                    @Override
                    public void onResponse(IDataParser response) {
                        Log.v("Success", "Success->");
                        assertTrue(true);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.v("Fail", "Fail->");
                        assertFalse(true);
                    }
                }){
            protected Map<String, String>getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username", "colin.light@googlemail.com");
                params.put("password", "colinisbest");
                return params;
            }
        };

        mQueue.add(request);
        countDown.await(30, TimeUnit.SECONDS);

    }
}
