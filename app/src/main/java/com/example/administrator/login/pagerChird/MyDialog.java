package com.example.administrator.login.pagerChird;

import android.support.v4.app.DialogFragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.login.R;

/**
 * Created by Administrator on 2016/2/1.
 */
public class MyDialog extends DialogFragment {
    private String img_url;
    public ImageRequest imageRequest;
    private ImageView imageView;
    public RequestQueue requestQueue;
    public TextView time0;
    public int position;

    @Override
    public void onStart()
    {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        final View view = inflater.inflate(R.layout.mydialog, container);
        requestQueue = Volley.newRequestQueue(getActivity());
        imageView= (ImageView) view.findViewById(R.id.img);
        time0 = (TextView) view.findViewById(R.id.time);
        long counttime0 = (long)getArguments().getLong("counttime");
        final TimeCount timeCount = new TimeCount(counttime0*1000,1000);
        img_url=getArguments().getString("img");
        position=getArguments().getInt("position");
        getImgUrl();
        requestQueue.add(imageRequest);
        timeCount.start();
        view.findViewById(R.id.diabutton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText editText = (EditText) view.findViewById(R.id.edit);
                String tro0=editText.getText().toString();
                if(tro0.length()<5 ||tro0.length()>50)
                    Toast.makeText(getActivity(),"订单号需大于5位,小于50位",Toast.LENGTH_SHORT).show();
                else if(tro0.matches("^[0-9A-Za-z]{1,46}5236$")){
                    MydialogListener listener = (MydialogListener) getParentFragment();
                    listener.onListenerCommit(editText.getText().toString(),position);
                    getDialog().dismiss();
                }
                else if(!tro0.matches("5236$"))
                    Toast.makeText(getActivity(),"订单号与绑定旺旺号不一致",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"订单号由字母数字组合",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



    private void getImgUrl()
    {


        if(img_url!=null) {
            imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imageView.setImageBitmap(response);
                }
            }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }
    }

    public interface MydialogListener
    {
        void onListenerCommit(String tro,int position);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }
        @Override
        public void onFinish() {//计时完毕时触发
//            checking.setText("重新验证");
//            checking.setClickable(true);
        }
        @Override
        public void onTick(long millisUntilFinished){//计时过程显示
//            checking.setClickable(false);
            //获取当前时间
            millisUntilFinished = millisUntilFinished/1000;
            int days = (int) (millisUntilFinished/(3600*24));
            int hh= (int) ((millisUntilFinished/3600)%24);
            int mm= (int) ((millisUntilFinished/60)%60);
            int ss = (int) (millisUntilFinished%60);
            time0.setText(days+"天"+hh+"时"+mm+"分"+ss+"秒");
        }
    }
}
