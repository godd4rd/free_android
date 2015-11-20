package uk.co.twentytwonorth.free.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.co.twentytwonorth.free.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApp();
    }

    private void initApp(){
        //showLogin();
        this.loggedIn();
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
