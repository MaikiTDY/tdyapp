package com.tdy.tdytravel.uitls;



import android.os.Handler;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ThreadPoolUtil {

    //�ڷ�ui�߳�������
    public static void runTaskInThread(Runnable task){
        ThreadPoolFactory.getCommonThreadPool().execute(task);
    }

    public static Handler handler = new Handler() ;
    //��ui�߳�������
    public static void runTaskInUIThread(Runnable task){
        handler.post(task);
    }
}
