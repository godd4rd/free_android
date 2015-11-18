package twentytwonorth.co.uk.free.controller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import twentytwonorth.co.uk.free.R;

public class TypeSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_select);
    }

    public void desksSelected( View view){
        this.startRootActivity();
    }

    public void roomSelected( View view){
        this.startRootActivity();
    }

    private void startRootActivity(){
        this.startActivity(new Intent(this, RootActivity.class));
        this.finish();
    }

}
