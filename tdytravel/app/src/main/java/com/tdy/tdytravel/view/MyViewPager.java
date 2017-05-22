package com.tdy.tdytravel.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Administrator
 * 自定义ViewPager
 * 左右滑动禁掉
 *
 */
public class MyViewPager extends ViewPager
{
    //onInterceptTouchEvent 返回false, onTouchEvent 返回false;
    public MyViewPager(Context context)
    {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0)
    {
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent arg0)
    {
        return false;
    }

}