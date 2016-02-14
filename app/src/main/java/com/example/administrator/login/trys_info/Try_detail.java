package com.example.administrator.login.trys_info;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.login.R;
import com.example.administrator.login.volly.ImageCacheManager;

public class Try_detail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_detail);
        ImageView try_img = (ImageView) findViewById(R.id.try_img);
        Bundle getBundle = getIntent().getExtras();
        CacheImage(try_img,getBundle.getString("imgurl"));
        findViewById(R.id.open_try_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                Uri url = Uri.parse("https://detail.m.tmall.com/item.htm?spm=0.0.0.0.43gu1z&id=41756957380&sid=cc6070187f18ee2c1dfbc0d005d918fe&rn=dc3770d48d0861e31373a99e254c83a2&abtest=23");
                intent.setData(url);

//                intent.setClassName("com.taobao.taobao", "com.taobao.tao.homepage.MainActivity3");
//                ClipboardManager cmb = (ClipboardManager)Try_detail.this.getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
//                cmb.setText("复制这条信息，打开[表情]手机淘宝[表情]即可看到【 帽子男士秋冬季户外韩版嘻哈帽平顶帽女军帽棒球帽鸭舌帽潮时尚】 http://b.mashort.cn/h.jj4sU?cv=AACkepfP&sm=be3f71 [表情]淘口令 [表情]");
                try {
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"安装淘宝APP才能查看",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void CacheImage(ImageView view,String url){
        Bitmap defaultImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.iconfont_starfull);
        Bitmap errorImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.noiconfont_user);
        ImageCacheManager.loadImage(getApplicationContext(), url, view, defaultImage, errorImage);
    }
}
