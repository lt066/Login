package com.example.administrator.login.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.login.R;
import com.example.administrator.login.pagerChird.Second_1;
import com.example.administrator.login.pagerChird.Second_2;
import com.example.administrator.login.pagerChird.Second_3;
import com.example.administrator.login.pagerChird.Second_4;
import com.example.administrator.login.pagerChird.Second_5;
import com.example.administrator.login.pagerChird.Second_6;
import com.example.administrator.login.pagerChird.Second_7;

/**
 * Created by lt413 on 2016/1/2.
 */
public class Second extends Fragment{


    private MyViewPager myViewPager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Second_1 second_1;
    private Second_2 second_2;
    private Second_3 second_3;
    private Second_4 second_4;
    private Second_5 second_5;
    private Second_6 second_6;
    private Second_7 second_7;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View secondView = inflater.inflate(R.layout.viewpager,container,false);
        myViewPager = new MyViewPager(getFragmentManager());
        viewPager = (ViewPager) secondView.findViewById(R.id.paper);
        tabLayout = (TabLayout) secondView.findViewById(R.id.tab);
        second_1 = new Second_1();
        second_2 = new Second_2();
        second_3 = new Second_3();
        second_4 = new Second_4();
        second_5 = new Second_5();
        second_6 = new Second_6();
        second_7 = new Second_7();
        viewPager.setAdapter(myViewPager);
        tabLayout.setupWithViewPager(viewPager);





        return secondView;
    }

    public class MyViewPager extends FragmentPagerAdapter{

        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return second_1;
                case 1:
                    return second_2;
                case 2:
                    return second_3;
                case 3:
                    return second_4;
                case 4:
                    return second_5;
                case 5:
                    return second_6;
                case 6:
                    return second_7;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "待下单";
                case 1:
                    return "待提交";
                case 2:
                    return "待修改";
                case 3:
                    return "待确认";
                case 4:
                    return "待返款";
                case 5:
                    return "已完成";
                case 6:
                    return "已终止";

            }
            return null;
        }
    }
}
