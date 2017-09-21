package com.tdy.tdytravel.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by tangdayi on 2017/9/18.
 * 作者:tangdayi
 * 日期:2017年09月18日19时44分
 * 文件:MyGirdViewList.java
 * 工程:tdytravel
 */
public class MyGirdViewList extends GridView{

    public MyGirdViewList(Context context) {
        super(context);
    }

    public MyGirdViewList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGirdViewList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyGirdViewList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
