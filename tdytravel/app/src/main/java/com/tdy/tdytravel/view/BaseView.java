package com.tdy.tdytravel.view;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by tangdayi on 2017/9/19.
 * 作者:tangdayi
 * 日期:2017年09月19日08时03分
 * 文件:BaseView.java
 * 工程: 视图基类
 */
public abstract class BaseView extends RelativeLayout{

    public Context mContext;
    public BaseView(Context context) {
        super(context);
        this.mContext = context;
        initView();
        initDate();
        initListener();
    }

    /**
     * 初始化 view
     */
    public abstract void initView();

    /**
     * 初始化 数据
     */
    public abstract void initDate();


    /**
     * 初始化 监听器
     */
    public abstract void initListener();
}
