package com.example.administrator.login.volly;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.login.R;
import com.example.administrator.login.trys_info.Try_detail;


/**
 * Created by lt413 on 2016/1/2.
 */
public class MyAdapter extends ArrayAdapter<Trys> {
    private Context context;
    private int resource;
    public MyAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Trys trys = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resource, null);
        ImageView img = (ImageView) view.findViewById(R.id.grid_img);
        TextView try_title = (TextView) view.findViewById(R.id.trys_title);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView nums = (TextView) view.findViewById(R.id.nums);
        View try_item = view.findViewById(R.id.try_item);
        CacheImage(img,trys.getImg_url());
        try_title.setText(trys.getTry_title());
        price.setText(trys.getPrice());
        nums.setText(trys.getNums());
        try_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent try_intent = new Intent(getContext(), Try_detail.class);
                Bundle bundle = new Bundle();
                bundle.putString("imgurl", trys.getImg_url());
                try_intent.putExtras(bundle);
                context.startActivity(try_intent);
            }
        });

        return view;
    }

    public void CacheImage(ImageView view,String url){
        Bitmap defaultImage=BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.iconfont_starfull);
        Bitmap errorImage= BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.noiconfont_user);
        ImageCacheManager.loadImage(getContext(), url, view, defaultImage, errorImage);
    }
}
