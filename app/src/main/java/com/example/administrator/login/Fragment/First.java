package com.example.administrator.login.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.login.R;
import com.example.administrator.login.volly.MyAdapter;
import com.example.administrator.login.volly.Trys;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lt413 on 2016/1/2.
 */
public class First extends Fragment {

    private PullToRefreshGridView mPullToRefreshGridView;
    private ArrayList<Map<String, Object>> list;
    private MyAdapter myAdapter;
    private String[] img_url=null;
    private String[][] trys_data=null;
    private int first_num;
    private String url0="http://appserver.shikee.com/trys/try_list?version=2.1.2";
    private int pager_num;
    private int current_pager=0;
    private int current_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View firstView = inflater.inflate(R.layout.firstview, container, false);

        mPullToRefreshGridView = (PullToRefreshGridView) firstView.findViewById(R.id.refresh_grid);
        mPullToRefreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshGridView.getRefreshableView().setNumColumns(2);
        mPullToRefreshGridView.getRefreshableView().setHorizontalSpacing(20);
        mPullToRefreshGridView.getRefreshableView().setVerticalSpacing(20);

        MyThread myThread0 = new MyThread();
        myThread0.start();
        try {
            myThread0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        myAdapter = new MyAdapter(getContext(),R.layout.gridchirdview);
        for(int i =0;i<first_num;i++)
        {
            myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
        }


        mPullToRefreshGridView.setAdapter(myAdapter);
        mPullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                url0="http://appserver.shikee.com/trys/try_list?version=2.1.2";
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                current_pager=current_pager+1;
                Toast.makeText(getContext(),""+current_pager,Toast.LENGTH_SHORT).show();
                if(current_pager<=pager_num)
                    url0="http://appserver.shikee.com/trys/try_list?version=2.1.2"+"&page="+current_pager;
                new GetUPDataTask().execute();
            }
        });
        return firstView;
    }

    private class GetDataTask extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            current_pager=0;

            MyThread myThread1 = new MyThread();
            myThread1.start();
            try {
                myThread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                myAdapter.clear();
            for(int i =0;i<first_num;i++)
            {
                myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
            }
            myAdapter.notifyDataSetChanged();
            mPullToRefreshGridView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }

    private class GetUPDataTask extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            MyThread myThread2 = new MyThread();
            myThread2.start();
            try {
                myThread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i =10*current_pager;i<10*current_pager+current_num;i++)
            {
                myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
            }
            myAdapter.notifyDataSetChanged();
            mPullToRefreshGridView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }

    private void TrysRequest() throws IOException, JSONException {
        URL url = new URL(url0);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
        httpURLConnection.setRequestMethod("GET");
        InputStream is = httpURLConnection.getInputStream();
        InputStreamReader isp = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isp);
        String result="";
        String inputLine = "";
        while ((inputLine = bufferedReader.readLine())!=null) {
            result +=inputLine+"\n";
        }

        JSONObject jsonObject = new JSONObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        pager_num= Integer.parseInt(data.getString("total_pages"));


        JSONArray try_list = data.getJSONArray("try_list");
        JSONObject[] try_jon = new JSONObject[10];
        current_num=try_list.length();
        first_num=current_num+10*current_pager;
        if(img_url==null)
            img_url = new String[Integer.parseInt(data.getString("try_total"))];
        if(trys_data==null)
            trys_data = new String[Integer.parseInt(data.getString("try_total"))][3];
        for(int i=0;i<(current_num);i++)
        {
            try_jon[i]=try_list.getJSONObject(i);
            img_url[i+current_pager*10]=try_jon[i].getString("img_list");
            trys_data[i+current_pager*10][0]=try_jon[i].getString("title");
            trys_data[i+current_pager*10][1]=try_jon[i].getString("price");
            trys_data[i+current_pager*10][2]=try_jon[i].getString("quantity");
        }

    }

    private class MyThread extends Thread{

            @Override
            public void run() {
                super.run();
                try {
                    TrysRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

}
