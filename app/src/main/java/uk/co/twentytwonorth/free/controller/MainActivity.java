package uk.co.twentytwonorth.free.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import junit.framework.Assert;

import uk.co.twentytwonorth.free.R;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Hello", "Hello-->");
        initApp();

    }

    private void initApp(){
        showLogin();

//        mQueue = Volley.newRequestQueue(this);//new RequestQueue(null, network);
//
//        String url = "https://demo.isitfree.co/oauth/token?grant_type=password";
//        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Assert.assertNotNull(response);
//                        Log.v("Success", "Success->");
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error){
//                        Log.e("Error", "Failed-->");
//                    }
//                });
//        mQueue.add(stringRequest);

//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://www.google.com";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.v("Success", "Response is: " + response.substring(0, 500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", "That didn't work!\"");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);




    }

    private void showLogin(){
        this.startActivity( new Intent(this, LoginActivity.class));
        this.finish();
    }

    private void loggedIn(){
        this.startActivity(new Intent(this, RootActivity.class));
        this.finish();
    }
}
