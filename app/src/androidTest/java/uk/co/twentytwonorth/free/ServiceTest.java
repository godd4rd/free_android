package uk.co.twentytwonorth.free;

import android.test.AndroidTestCase;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import uk.co.twentytwonorth.free.helper.FreeServiceRouter;
import uk.co.twentytwonorth.free.parser.LoginParser;
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




    public void test_service_returns_a_valid_access_token() throws InterruptedException {

        CountDownLatch countDown = new CountDownLatch(1);

        Map<String, String> params = new HashMap<>();
        params.put("username", "colin.light@googlemail.com");
        params.put("password", "colinisbest");

        ParsableRequest request = FreeServiceRouter.createRequest(FreeServiceRouter.Action.POSTTOKEN,
                params,
                new Response.Listener<IDataParser>() {
                    @Override
                    public void onResponse(IDataParser response) {
                        Log.v("Success", "Success->");
                        LoginParser loginParser = (LoginParser)response;
                        assertNotNull("post token request should return a token", loginParser.token);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Fail", "Fail->");
                        assertFalse(true);
                    }
                });
        mQueue.add(request);
        countDown.await(6, TimeUnit.SECONDS);

    }


    public void test_service_returns_a_user() throws InterruptedException {

        CountDownLatch countDown = new CountDownLatch(1);

        Map<String, String> params = new HashMap<>();
        params.put("username", "colin.light@googlemail.com");
        params.put("password", "colinisbest");


        ParsableRequest request = FreeServiceRouter.createRequest(FreeServiceRouter.Action.GET_CURRENT_USER,
                params,
                new Response.Listener<IDataParser>() {
                    @Override
                    public void onResponse(IDataParser response) {
                        Log.v("Success", "Success->");
                        //LoginParser loginParser = (LoginParser)response;
                        //assertNotNull("post token request should return a token", loginParser.token);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Fail", "Fail->");
                        assertFalse(true);
                    }
                });
        mQueue.add(request);
        countDown.await(6, TimeUnit.SECONDS);

    }

}
