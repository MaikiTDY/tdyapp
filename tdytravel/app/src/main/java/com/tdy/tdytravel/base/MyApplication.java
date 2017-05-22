package com.tdy.tdytravel.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * 上下文和共享参数处理
 * @author Administrator
 *
 */
public class MyApplication extends Application{
    private static Context context;
    private static SharedPreferences share;
    private static SharedPreferences.Editor editor;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initShare();
    }
    /**
     * 获取上下文
     * @return
     */
    public static Context getContext(){
        return context;
    }

    /**
     * 共享参数初始化
     */
    private void initShare() {
        //获取share对象
        share = getSharedPreferences("FamilyManagerMoney", MODE_PRIVATE);
        //获取编辑对象
        editor = share.edit();
    }
    /**
     * 存放字符串
     * @param value
     */
    public static void putString(String key,String value){
        editor.putString(key, value);
        editor.commit();
    }
    /**
     * 获取字符串
     * @param key
     * @return
     */
    public static String getString(String key){
        return share.getString(key,null);
    }


    /**
     * 存放id
     * @param key
     * @param value
     */
    public static void putInt(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 获取id
     * @param key
     * @return
     */
    public static int getInt(String key){
        return share.getInt(key,-1);
    }





}
