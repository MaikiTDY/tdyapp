package com.tdy.tdytravel.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
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