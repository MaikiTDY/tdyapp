package com.tdy.tdytravel.uitls;




import android.widget.Toast;

import com.tdy.tdytravel.base.MyApplication;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 * */
public class ToastUtil {

    public static void showToastInUIThread(String content){
        Toast.makeText(MyApplication.getContext(),content,Toast.LENGTH_SHORT).show();
    }

    /**
     * @param content
     */
    public static void showToastInThread(final String content){
        ThreadPoolUtil.runTaskInThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyApplication.getContext(),content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
