package com.tdy.tdytravel.uitls;


import android.annotation.SuppressLint;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/11/3.
 */
/*
    线程池代理
 */
public class ThreadPoolProxy {

    private ThreadPoolExecutor mExecutor;
    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,long keepAliveTime){
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }
    @SuppressLint("NewApi") 
    public ThreadPoolExecutor initThreadPoolExecutor(){
        if(mExecutor == null){//双重加锁机制
            synchronized (ThreadPoolProxy.class){
                if(mExecutor == null){
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    mExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.SECONDS,workQueue,threadFactory,handler);
                }
            }
        }
        return mExecutor;
    }

    //执行任务
    public void execute(Runnable task){
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    //移除任务
    public void remove(Runnable task){
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }

    //提交任务
    public Future<?> summit(Runnable task){
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

}
