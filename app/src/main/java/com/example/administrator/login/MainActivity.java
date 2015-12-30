package com.example.administrator.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private int  login_state = 0;
    private Intent setting_in;
    public static MainActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主界面");
        setSupportActionBar(toolbar);
        instance =this;
        setting_in = new Intent(MainActivity.this,Setting.class);
//        Bundle getbundle = this.getIntent().getExtras();
//        if(getbundle.getInt("success")==1)
//            login_state=getbundle.getInt("success");
        Bundle set_bu = new Bundle();
        try {
            Bundle getbu = getIntent().getExtras();
            login_state =getbu.getInt("success");
        }
        catch(Exception e)
        {

        }

        set_bu.putInt("login_state",login_state);
        setting_in.putExtras(set_bu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(setting_in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
