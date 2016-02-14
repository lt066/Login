package com.example.administrator.login.pagerChird;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.login.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lt413 on 2016/1/2.
 */


public class Second_1 extends Fragment implements MyDialog.MydialogListener {

    private PullToRefreshListView mPullToRefreshListView;
    private SeconAdapter seconAdapter;
    private String countime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View second_1 = inflater.inflate(R.layout.second_1,container,false);
        mPullToRefreshListView = (PullToRefreshListView) second_1.findViewById(R.id.refresh_1);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        seconAdapter = new SeconAdapter(getActivity(),R.layout.second_1_tro);
        seconAdapter.add(new Join_view(R.drawable.iconfont_starfull,"1533563256"));
        mPullToRefreshListView.setAdapter(seconAdapter);

        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetData().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetData().execute();
            }
        });

        return second_1;
    }



    private class SeconAdapter extends ArrayAdapter<Join_view>
    {
        private int mResource;
        public SeconAdapter(Context context, int resource) {
            super(context, resource);
            mResource=resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Join_view join_view = getItem(position);
            View view = LayoutInflater.from(getActivity()).inflate(mResource, null);
            ImageView item_imageView = (ImageView) view.findViewById(R.id.tro_img);
            TextView item_text = (TextView) view.findViewById(R.id.count_time);
            item_imageView.setImageResource(join_view.getmImgid());
            long currenttime =System.currentTimeMillis()/1000;
            long gettime = (long)Integer.parseInt(join_view.getmCountTime());
            final long counttime0 = gettime-currenttime;
            TimeCount timeCount = new TimeCount(counttime0*1000,1000,item_text);
            timeCount.start();
            view.findViewById(R.id.second_1_button2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog myDialog  = new MyDialog();
                    Bundle bundle =new Bundle();
                    bundle.putString("img","http://img1.shikee.com/try/2016/01/25/11414620921113454324.jpg");
                    bundle.putInt("position", position);
                    bundle.putLong("counttime", counttime0);
                    myDialog.setArguments(bundle);
                    myDialog.show(getChildFragmentManager(),"");
                }
            });
            return view;
        }
    }

    private class Join_view
    {
        private int mImgid;
        private String mCountTime;
        public Join_view(int imgId,String countTime)
        {
            mImgid=imgId;
            mCountTime=countTime;
        }

        public int getmImgid() {
            return mImgid;
        }

        public String getmCountTime() {
            return mCountTime;
        }
    }
    private class GetData extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {

            return "1457366400";
        }

        @Override
        protected void onPostExecute(String s) {
            seconAdapter.add(new Join_view(R.drawable.noiconfont_starfull,s));
            seconAdapter.notifyDataSetChanged();
            mPullToRefreshListView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }

    @Override
    public void onListenerCommit(String tro,int position) {

        Toast.makeText(getActivity(),tro,Toast.LENGTH_SHORT).show();
        seconAdapter.remove(seconAdapter.getItem(position));
    }

    class TimeCount extends CountDownTimer {
        private TextView mtext;
        public TimeCount(long millisInFuture, long countDownInterval,TextView textView) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
            mtext=textView;
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
            mtext.setText(days+"天"+hh+"时"+mm+"分"+ss+"秒");
        }
    }

}
