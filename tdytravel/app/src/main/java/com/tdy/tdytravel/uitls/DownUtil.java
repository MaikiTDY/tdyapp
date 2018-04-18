package com.tdy.tdytravel.uitls;

import android.graphics.Bitmap;
import android.os.Handler;

/**
 * 下载工具类
 * @author Ken
 *
 */
public class DownUtil {
	
	private static Handler handler = new Handler();
	
	/**
	 * 下载完成
	 * @param url
	 */
	public static void downJSON(final String url, final OnDownCompelet onDownCompelet){
		new Thread(){
			public void run() {
				final String str = HttpUtil.getJSONByUrl(url);
				handler.post(new Runnable() {
					@Override
					public void run() {
						onDownCompelet.downSucc(url, str);
					}
				});
			};
		}.start();
	}
	
	
	public static void downImage(final String url, final OnDownCompelet onDownCompelet){
		new Thread(){
			public void run() {
				final Bitmap bitmap = HttpUtil.getBitmapByUrl(url);
				handler.post(new Runnable() {
					@Override
					public void run() {
						onDownCompelet.downSucc(url, bitmap);
					}
				});
			};
		}.start();
	}
	
	public interface OnDownCompelet{
		void downSucc(String url, Object obj);
	};
}
