package com.tdy.tdytravel.uitls;



import android.os.Handler;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 *
 * */
public class ThreadPoolUtil {

    public static void runTaskInThread(Runnable task){
        ThreadPoolFactory.getCommonThreadPool().execute(task);
    }
    public static Handler handler = new Handler() ;
    public static void runTaskInUIThread(Runnable task){
        handler.post(task);
    }
}
