package com.example.administrator.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int  login_state = 0;
    private Intent setting_in;
    public static MainActivity instance = null;
    private Bundle set_bu = new Bundle();
    private boolean isExit = false;
    private View fram1;
    private View fram2;
    private View fram3;
    private View fram4;
    private ImageView imgfram1;
    private ImageView imgfram2;
    private ImageView imgfram3;
    private ImageView imgfram4;
    private TextView textfram1;
    private TextView textfram2;
    private TextView textfram3;
    private TextView textfram4;

    private Handler mhandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        toolbar.setTitle("");
        TextView title = (TextView) findViewById(R.id.mainbar_text);
        title.setText("主界面");
        setSupportActionBar(toolbar);

        init();


    }

    private void init()
    {
        instance =this;
        fram1 =findViewById(R.id.fragment1);
        fram2 =findViewById(R.id.fragment2);
        fram3 =findViewById(R.id.fragment3);
        fram4 =findViewById(R.id.fragment4);
        imgfram1= (ImageView) findViewById(R.id.imgfram1);
        imgfram2= (ImageView) findViewById(R.id.imgfram2);
        imgfram3= (ImageView) findViewById(R.id.imgfram3);
        imgfram4= (ImageView) findViewById(R.id.imgfram4);
        textfram1= (TextView) findViewById(R.id.textfram1);
        textfram2= (TextView) findViewById(R.id.textfram2);
        textfram3= (TextView) findViewById(R.id.textfram3);
        textfram4= (TextView) findViewById(R.id.textfram4);

        fram1.setOnClickListener(this);
        fram2.setOnClickListener(this);
        fram3.setOnClickListener(this);
        fram4.setOnClickListener(this);

        setting_in = new Intent(MainActivity.this,Setting.class);

        try {
            Bundle getbu = getIntent().getExtras();
            login_state =getbu.getInt("success");
        }
        catch(Exception e)
        {

        }
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
            set_bu.putInt("login_state",login_state);
            setting_in.putExtras(set_bu);
            startActivityForResult(setting_in, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==2)
        {
            login_state=0;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exit0();
            return  true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit0(){
        if(!isExit)
        {
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出",Toast.LENGTH_SHORT).show();
            mhandler.sendEmptyMessageDelayed(0,2000);
        }
        else
        {
            System.exit(0);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.fragment1:
                clearframSetting();
                imgfram1.setImageResource(R.drawable.iconfont_starfull);
                textfram1.setTextColor(getResources().getColor(R.color.framText));
                break;
            case R.id.fragment2:
                clearframSetting();
                imgfram2.setImageResource(R.drawable.iconfont_user);
                textfram2.setTextColor(getResources().getColor(R.color.framText));
                break;
            case R.id.fragment3:
                clearframSetting();
                imgfram3.setImageResource(R.drawable.iconfont_mail);
                textfram3.setTextColor(getResources().getColor(R.color.framText));
                break;
            case R.id.fragment4:
                clearframSetting();
                imgfram4.setImageResource(R.drawable.iconfont_onedrive);
                textfram4.setTextColor(getResources().getColor(R.color.framText));
                break;

        }
    }

    private void clearframSetting()
    {
        imgfram1.setImageResource(R.drawable.noiconfont_starfull);
        textfram1.setTextColor(getResources().getColor(R.color.framTextde));
        imgfram2.setImageResource(R.drawable.noiconfont_user);
        textfram2.setTextColor(getResources().getColor(R.color.framTextde));
        imgfram3.setImageResource(R.drawable.noiconfont_mail);
        textfram3.setTextColor(getResources().getColor(R.color.framTextde));
        imgfram4.setImageResource(R.drawable.noiconfont_onedrive);
        textfram4.setTextColor(getResources().getColor(R.color.framTextde));
    }
}
