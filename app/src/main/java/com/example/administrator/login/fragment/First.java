package com.example.administrator.login.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
public class First extends Fragment implements View.OnClickListener{

    private PullToRefreshGridView mPullToRefreshGridView;
    private ImageView go_top;
    private ArrayList<Map<String, Object>> list;
    private MyAdapter myAdapter;
    private String[] img_url=null;
    private String[][] trys_data=null;
    private int first_num;
    private String url0="http://appserver.shikee.com/trys/try_list?version=2.1.2";
    private int pager_num;
    private int current_pager=1;
    private int current_num;
    private String result;
    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private boolean ques_ok = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View firstView = inflater.inflate(R.layout.firstview, container, false);

        go_top= (ImageView) firstView.findViewById(R.id.go_top_button);
        go_top.setOnClickListener(this);

        mPullToRefreshGridView = (PullToRefreshGridView) firstView.findViewById(R.id.refresh_grid);
        mPullToRefreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshGridView.getRefreshableView().setNumColumns(2);
        mPullToRefreshGridView.getRefreshableView().setHorizontalSpacing(20);
        mPullToRefreshGridView.getRefreshableView().setVerticalSpacing(20);
        requestQueue = Volley.newRequestQueue(getActivity());
        UrlRequest();

        MyThread myThread0 = new MyThread();


        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        myThread0.start();



        myAdapter = new MyAdapter(getActivity(),R.layout.gridchirdview);
//        for(int i =0;i<first_num;i++)
//        {
//            myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
//        }


        mPullToRefreshGridView.setAdapter(myAdapter);

        ques_ok=false;
        mPullToRefreshGridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                url0="http://appserver.shikee.com/trys/try_list?version=2.1.2";
                stringRequest.setRedirectUrl(url0);
                requestQueue.cancelAll("trys");
                UrlRequest();
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                current_pager=current_pager+1;
//                Toast.makeText(getContext(),""+current_pager,Toast.LENGTH_SHORT).show();
                if(current_pager<=pager_num)
                    url0="http://appserver.shikee.com/trys/try_list?version=2.1.2"+"&page="+current_pager;
                stringRequest.setRedirectUrl(url0);
                requestQueue.cancelAll("trys");
                UrlRequest();
                new GetUPDataTask().execute();
            }
        });

        mPullToRefreshGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem>=10)
                    go_top.setVisibility(View.VISIBLE);
                else
                    go_top.setVisibility(View.GONE);
            }
        });

        return firstView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.go_top_button:

                mPullToRefreshGridView.getRefreshableView().setSelection(0);
                break;

        }
    }

    private class GetDataTask extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            current_pager=1;
            MyThread myThread1 = new MyThread();
            myThread1.start();
            try {
                myThread1.join();
                Thread.sleep(500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

//            for(int i =0;i<first_num;i++)
//            {
//                myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
//            }
            mPullToRefreshGridView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }

    private class GetUPDataTask extends AsyncTask<Void,Void,String>
    {

        @Override
        protected String doInBackground(Void... params) {
            MyThread myThread2 = new MyThread();
            myThread2.start();
            try {
                myThread2.join();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

//            for(int i =10*(current_pager-1);i<10*(current_pager-1)+current_num;i++)
//            {
//                myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
//            }
            mPullToRefreshGridView.onRefreshComplete();
            super.onPostExecute(s);
        }
    }

    private void TrysRequest() throws IOException, JSONException {

        /*
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
        String inputLine = "";
        while ((inputLine = bufferedReader.readLine())!=null) {
            result +=inputLine+"\n";
        }
        */


        JSONObject jsonObject = new JSONObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        pager_num= Integer.parseInt(data.getString("total_pages"));


        JSONArray try_list = data.getJSONArray("try_list");
        JSONObject[] try_jon = new JSONObject[10];
        current_num=try_list.length();
        first_num=current_num+10*(current_pager-1);
        if(img_url==null)
            img_url = new String[Integer.parseInt(data.getString("try_total"))];
        if(trys_data==null)
            trys_data = new String[Integer.parseInt(data.getString("try_total"))][3];
        for(int i=0;i<current_num;i++)
        {
            try_jon[i]=try_list.getJSONObject(i);
            img_url[i+(current_pager-1)*10]=try_jon[i].getString("img_list");
            trys_data[i+(current_pager-1)*10][0]=try_jon[i].getString("title");
            trys_data[i+(current_pager-1)*10][1]=try_jon[i].getString("price");
            trys_data[i+(current_pager-1)*10][2]=try_jon[i].getString("quantity");
        }

        if(current_pager==1)
        {
            myAdapter.clear();
        }
        for(int i =10*(current_pager-1);i<10*(current_pager-1)+current_num;i++)
        {
            myAdapter.add(new Trys(img_url[i],trys_data[i][0],trys_data[i][1],trys_data[i][2]));
        }
        myAdapter.notifyDataSetChanged();
        ques_ok=false;

    }

    private void UrlRequest()
    {
        stringRequest = new StringRequest(url0, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                result = s;
                try {
                    TrysRequest();
                    ques_ok=true;
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof TimeoutError) {
                    Toast.makeText(getActivity(), "连接超时，请重试", Toast.LENGTH_SHORT).show();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    if(mPullToRefreshGridView.isRefreshing())
                        mPullToRefreshGridView.onRefreshComplete();
                }
                else if (volleyError instanceof NetworkError) {
                    Toast.makeText(getActivity(), "网络连接错误，请重试", Toast.LENGTH_SHORT).show();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    if(mPullToRefreshGridView.isRefreshing())
                        mPullToRefreshGridView.onRefreshComplete();
                }

            }
        });
        stringRequest.setTag("trys");
        requestQueue.add(stringRequest);
    }

    private class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            result = null;
            requestQueue.start();
            while(!ques_ok)
            {

            }
        }
    }
}
