package com.example.administrator.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    public static Setting instance = null;
    private  Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.anotoolbar);
        toolbar.setTitle("");
        TextView title = (TextView) findViewById(R.id.bar_text);
        title.setText("设置");
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        instance=this;
        Button login_button = (Button) findViewById(R.id.login_button);
        bundle = this.getIntent().getExtras();
        if(bundle.getInt("login_state")==0)
            login_button.setText("登录");
        else
            login_button.setText("退出登录");
        login_button.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ano, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_button:
                if(bundle.getInt("login_state")==0)
                    startActivity(new Intent(Setting.this,Login.class));
                else
                    finish();
        }
    }
}
