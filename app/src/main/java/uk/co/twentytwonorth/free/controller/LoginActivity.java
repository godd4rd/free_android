package uk.co.twentytwonorth.free.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import uk.co.twentytwonorth.free.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login( View view){
        this.startActivity(new Intent(this, TypeSelectActivity.class));
        this.finish();
    }

}
