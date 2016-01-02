package com.example.administrator.login.pagerChird;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.login.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lt413 on 2016/1/2.
 */


public class Second_1 extends Fragment {

    private PullToRefreshListView mPullToRefreshListView;
    private ArrayList<Map<String,Object>> list;
    private SimpleAdapter simpleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View second_1 = inflater.inflate(R.layout.second_1,container,false);
        mPullToRefreshListView = (PullToRefreshListView) second_1.findViewById(R.id.refresh_1);
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        list= new ArrayList<Map<String,Object>>();
        for(int i =0;i<5;i++)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text","text"+i);
            list.add(map);
        }

        simpleAdapter = new SimpleAdapter(getContext(),list,R.layout.tro_view,new String[]{"text"},new int[]{R.id.text_tro});
        mPullToRefreshListView.setAdapter(simpleAdapter);

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

    private class GetData extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            String str="Added after refresh...I add";
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("text","text");
            list.add(map);
            simpleAdapter.notifyDataSetChanged();
            mPullToRefreshListView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }
}
