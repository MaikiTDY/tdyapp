package com.tdy.tdytravel.uitls;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class VolleyImageLoader  {
	
	private static VolleyImageLoader mInstance;
	private ImageLoader mLoader;
	private RequestQueue mQueue;
	//表示队列是否停止状态
	private boolean isStop = false;


	private VolleyImageLoader(Context context){
		mQueue = Volley.newRequestQueue(context);
		mLoader = new ImageLoader(mQueue, VolleyCache.getInstance());
	}
	
	public static VolleyImageLoader getInstance(Context context){
		if (mInstance == null) {
			synchronized (VolleyImageLoader.class) {
				if (mInstance == null) {
					mInstance = new VolleyImageLoader(context);
				}
			}
		}
		return mInstance;
	}
	/**
	 * 加载图片
	 * @param url
	 * @param iv
	 */
	public void loadImage(String url,NetworkImageView iv){
		if(isStop){
			mQueue.start();
			isStop =false;
		}
		iv.setImageUrl(url,mLoader);
	}
	/**
	 *  清空图片缓存
	 */
	public void clearCache(){
		mQueue.getCache().clear();
	}
	/**
	 * 停止图片加载的队列
	 */
	public void stopImageLoader(){
		if (!isStop) {
			mQueue.stop();
			isStop =true;
		}
	}
}
