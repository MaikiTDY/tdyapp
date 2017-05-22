package com.tdy.tdytravel.uitls;


/**
 * Created by Administrator on 2015/11/3.
 */
public class ThreadPoolFactory {

    public static ThreadPoolProxy commonThreadPool;
    public static  final int Common_CORE_POOL_SIZE=5;
    public static  final int Common_MAX_POOL_SIZE=5;
    public static final int Common_KEEP_LIVE_TIME = 1;

    public static ThreadPoolProxy getCommonThreadPool(){
        if(commonThreadPool == null){
            synchronized (ThreadPoolProxy.class){
                if (commonThreadPool == null){
                    commonThreadPool = new ThreadPoolProxy(Common_CORE_POOL_SIZE,Common_MAX_POOL_SIZE,Common_MAX_POOL_SIZE);
                }
            }
        }
        return commonThreadPool;
    }
}
