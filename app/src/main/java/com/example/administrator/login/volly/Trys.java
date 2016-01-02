package com.example.administrator.login.volly;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lt413 on 2016/1/2.
 */
public class Trys {
    private String img_url;
    private String price;
    private String try_title;
    private String nums;
    public Trys(String img_url,String try_title,String price,String nums)
    {
        this.img_url=img_url;
        this.price=price;
        this.try_title=try_title;
        this.nums=nums;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getPrice() {
        return price;
    }

    public String getTry_title() {
        return try_title;
    }

    public String getNums() {
        return nums;
    }
}
