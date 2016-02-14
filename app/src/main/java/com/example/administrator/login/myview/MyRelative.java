package com.example.administrator.login.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/2/6.
 */
public class MyRelative extends RelativeLayout {
    public MyRelative(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public MyRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    public MyRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.BLACK);
//        RectF rectF = new RectF(0,0,80,40);
//        canvas.drawArc(rectF,-180,0,true,paint);
        canvas.drawColor(Color.BLACK);
    }
}
