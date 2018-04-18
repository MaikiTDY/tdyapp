package com.tdy.tdytravel.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tdy.house.utils.ImageUtils;

public class ImageLoader {
	private String mUrl;
	private ImageView mImageView;
	private boolean isWidthStretch = false;

	/**
	 * @param mUrl
	 * @param mUrl
	 * @param mImageView
	 * @param mImageView
	 */
	public ImageLoader(String mUrl, ImageView mImageView) {

		this.mUrl = mUrl;
		this.mImageView = mImageView;
		this.mImageView.setTag(this.mUrl);
		if (!TextUtils.isEmpty(this.mUrl.toString().trim()))
			new imageAsynTaskLoader().execute(mUrl);
	}

	public ImageLoader(String mUrl, ImageView mImageView, boolean isWidthStretch) {
		this.mUrl = mUrl;
		this.mImageView = mImageView;
		this.mImageView.setTag(this.mUrl);
		this.isWidthStretch = isWidthStretch;
		if (!TextUtils.isEmpty(this.mUrl.toString().trim()))
			new imageAsynTaskLoader().execute(mUrl);
	}

	class imageAsynTaskLoader extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... paramVarArgs) {
			URL url;
			try {
				Bitmap bitmap;
				if (ImageUtils.getToCache(mUrl) == null) {
					if (ImageUtils.getBitmapFromSdcard(mUrl) == null) {
						url = new URL(mUrl);
						InputStream is = url.openStream();
						bitmap = BitmapFactory.decodeStream(is);
						ImageUtils.saveToCache(mUrl, bitmap);
						ImageUtils.addBitmapToSdcard(mUrl, bitmap);
					} else {
						ImageUtils.saveToCache(mUrl,
								ImageUtils.getBitmapFromSdcard(mUrl));
						bitmap = ImageUtils.getBitmapFromSdcard(mUrl);
					}
				} else {
					bitmap = ImageUtils.getToCache(mUrl);
				}
				return bitmap;
			} catch (MalformedURLException e) {
				System.out.println("URLErro:" + mUrl);
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (mImageView != null && mImageView.getTag().equals(mUrl)) {
				if (isWidthStretch) {
					float width = mImageView.getWidth();
					float height = width * 1.0f / result.getWidth()
							* result.getHeight();
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							(int) width, (int) height);
					mImageView.setLayoutParams(lp);
				}
				mImageView.setImageBitmap(result);
			}
		}

	}

}
