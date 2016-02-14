package com.example.administrator.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.login.cache.DataCleanManager;

public class Setting extends AppCompatActivity implements View.OnClickListener {

    public static Setting instance = null;
    private  Bundle bundle;
    private  TextView cache;
    private  Switch mySwitch;
    private  Button login_button;
    private  TextView switchText;

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
        init();

        setValue();



    }

    private void init()
    {

        cache = (TextView) findViewById(R.id.cachesize);


        mySwitch = (Switch) findViewById(R.id.switch1);

        login_button = (Button) findViewById(R.id.login_button);
        switchText = (TextView) findViewById(R.id.switchText);


    }

    public void setValue()
    {
        if(cache!=null) {
            try {
                cache.setText(getCacheSize());
            } catch (Exception e) {
                e.printStackTrace();
            }
            RelativeLayout cacheRe = (RelativeLayout) findViewById(R.id.cleanCache);
            cacheRe.setOnClickListener(this);
        }


        if(mySwitch!=null)
        {
            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        switchText.setText("switch打开");
                    }
                    else{
                        switchText.setText("switch关闭");
                    }

                }
            });
        }


        if(login_button!=null) {
            bundle = this.getIntent().getExtras();
            if (bundle.getInt("login_state") == 0)
                login_button.setText("登录");
            else
                login_button.setText("退出登录");
            login_button.setOnClickListener(this);
        }
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
                else {
                    Intent result = new Intent();
                    result.putExtra("login_state",0);
                    setResult(2,result);
                    finish();
                }
                break;
            case R.id.cleanCache:
                Toast.makeText(getApplication(),"正在清除缓存",Toast.LENGTH_SHORT).show();
                DataCleanManager.cleanExternalCache(getApplication());
                break;
        }
    }

    private String getCacheSize() throws Exception {

        return DataCleanManager.getCacheSize(getApplication().getExternalCacheDir());
    }
}
