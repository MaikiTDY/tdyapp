package com.tdy.tdytravel.uitls;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyUtils {
	
	private static VolleyUtils mInstance;
	private RequestQueue mQueue;

	private VolleyUtils(Context context){
		mQueue = Volley.newRequestQueue(context);
	}
	
	/**
	 * 懒汉式单例模式
	 * @param context
	 * @return
	 */
	public static VolleyUtils getInstance(Context context){
		if (mInstance == null) {
			synchronized (VolleyUtils.class) {
				if (mInstance == null) {
					mInstance = new VolleyUtils(context);
				}
			}
		}
		return mInstance;
	}
	
	public void sendRequest(Request res){
		mQueue.add(res);
	}
}
