package com.tdy.tdytravel.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.tdy.house.utils.SystemUtil;

public class doPostRequest {
	private Handler mHandler;
	private List<NameValuePair> datas;

	/**
	 * @param mHandler
	 */
	public doPostRequest(Context context, Handler mHandler, String mUrl,
			List<NameValuePair> datas) {
		this.mHandler = mHandler;
		this.datas = datas;
		if (!SystemUtil.isNetworkConnected(context)) {
			 Message message = Message.obtain();
			 this.mHandler.sendMessage(message);
			Toast.makeText(context, "��ǰ����������", Toast.LENGTH_SHORT).show();
		} else {
			new doPostAsyncTask().execute(mUrl);
		}
	}

	class doPostAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String mUrl = params[0];
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(mUrl);
				if (datas != null)
					httpPost.setEntity(new UrlEncodedFormEntity(datas));
				HttpResponse response = httpClient.execute(httpPost);
				if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
					HttpEntity entity = response.getEntity();
					InputStream is = entity.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					StringBuffer sb = new StringBuffer();
					int temp;
					while ((temp = br.read()) != -1)
						sb.append((char) temp);
					br.close();
					is.close();
					return sb.toString();
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			Message message = Message.obtain();
			message.obj = result;
			mHandler.sendMessage(message);
		}

	}

}
