package com.tdy.tdytravel.uitls;




import android.widget.Toast;

import com.tdy.tdytravel.base.MyAppliaction;

/**
 * Created by Administrator on 2015/11/3.
 */
public class ToastUtil {

    public static void showToastInUIThread(String content){
        Toast.makeText(MyAppliaction.getContext(),content,Toast.LENGTH_SHORT).show();
    }

    /**
     * @param content
     */
    public static void showToastInThread(final String content){
        ThreadPoolUtil.runTaskInThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyAppliaction.getContext(),content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
