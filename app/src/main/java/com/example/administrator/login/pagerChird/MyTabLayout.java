package com.example.administrator.login.pagerChird;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.TableLayout;

/**
 * Created by Administrator on 2016/2/4.
 */
public class MyTabLayout extends TableLayout {
    public MyTabLayout(Context context, SlidingTabStrip mTabStrip) {
        super(context);
        this.mTabStrip = mTabStrip;
    }

    public MyTabLayout(Context context, AttributeSet attrs, SlidingTabStrip mTabStrip) {
        super(context, attrs);
        this.mTabStrip = mTabStrip;
    }

    public interface TabColorizer {

        /**
         * @return return the color of the indicator used when {@code position} is selected.
         */
        int getIndicatorColor(int position);

        /**
         * @return return the color of the divider drawn to the right of {@code position}.
         */
        int getDividerColor(int position);

    }

    private static final int TITLE_OFFSET_DIPS = 24;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;

    private int mTitleOffset;

    private int mTabViewLayoutId;
    private int mTabViewTextViewId;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;

    private final SlidingTabStrip mTabStrip;

    public void setDividerColors(int... colors) {
        mTabStrip.setDividerColors(colors);
    }

}
