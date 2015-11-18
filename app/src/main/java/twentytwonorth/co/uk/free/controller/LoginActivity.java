package twentytwonorth.co.uk.free.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import twentytwonorth.co.uk.free.R;

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
