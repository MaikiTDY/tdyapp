package com.tdy.tdytravel.uitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时37分
 * 文件:OrderBean.java
 * 工程:tdytravel
 *
 * 网络工具类
 */
public class HttpUtil {
	private static final String TAG = "travel";

	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */

	public static boolean isNetWorkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			NetworkInfo info = connectivityManager.getActiveNetworkInfo();
			if (info == null) {
				return false;
			} else {
				if (info.isAvailable()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifiStatus(Context context) {
		boolean isWifiConnect = true;
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
		for (int i = 0; i < networkInfos.length; i++) {
			if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
				if (networkInfos[i].getType() == cm.TYPE_MOBILE) {
					isWifiConnect = false;
				}
				if (networkInfos[i].getType() == cm.TYPE_WIFI) {
					isWifiConnect = true;
				}
			}
		}
		return isWifiConnect;
	}

	/**
	 * 发送 url get请求 创建URL，并使用URLConnection/HttpURLConnection
	 * @param url
	 * @return  获取请求字符串
	 */
	public static String sendGetByURLConn(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(10000);
			conn.addRequestProperty("User-Agent", "J2me/MIDP2.0");
			conn.addRequestProperty("Accept-Encoding", "gzip");
			conn.connect();
			if (null != conn.getHeaderField("Content-Encoding")) {// 支持压缩
				in = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			}
			String line="";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG,"发送GET请求出现异常！");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 获取网络图片资源
	 * 
	 * @param url
	 * @return
	 */
	public static Bitmap getHttpBitmap(String url) {

		URL mUrl;
		Bitmap bitmap = null;
		try {
			mUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6000);
			// conn.setDoInput(true);//连接设置获得数据流
			// conn.setUseCaches(false);//不使用缓存
			conn.connect();
			InputStream is = conn.getInputStream();

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			// is.close();
			byte[] data = outStream.toByteArray();
			Log.i(TAG,"=============img byte============== :" + data.length);
			// bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			InputStream is2 = new ByteArrayInputStream(data);
			BitmapFactory.Options options = new BitmapFactory.Options();
			bitmap = BitmapFactory.decodeStream(is2, null, options);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	/**
	 * 获取网络html代码
	 * 
	 * @param url
	 * @return
	 */
	public String getHtmlCode(String url) {
		String html = null;
		try {
			URL htmlURL = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) htmlURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);

			InputStream inStream = conn.getInputStream();

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			int len = 0;

			while ((len = inStream.read(buffer)) != -1) {

				outStream.write(buffer, 0, len);

			}

			inStream.close();

			byte[] data = outStream.toByteArray();

			html = new String(data, "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

	/**
	 * 将url参数转换成map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, Object> getUrlParams(String param) {
		Map<String, Object> map = new HashMap<String, Object>(0);
		if ("".equals(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}


    private static byte[] getByteFromURL(String url) {
        URL mUrl;
        HttpURLConnection conn;
        try {
            mUrl = new URL(url);
            conn = (HttpURLConnection) mUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] tempByte = new byte[1024 * 2];
            int len = 0;
            while ((len = is.read(tempByte)) != -1)
                baos.write(tempByte, 0, len);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void getStringFromURL(final String url,
                                        final OnRequestListener<String> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                listener.RequestResult(url, new String(getByteFromURL(url)));
            }
        }).start();
    }

    public static void getJObjectFromURL(final String url,
                                         final OnRequestListener<JSONObject> listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listener.RequestResult(url, new JSONObject(new String(
                            getByteFromURL(url))));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static JSONArray getJArrayFromURL(String url) {
        byte[] tempByte = getByteFromURL(url);
        if (tempByte != null) {
            try {
                return new JSONArray(new String(tempByte));
            } catch (JSONException e) {
                return null;
            }
        }
        return null;
    }

    public interface OnRequestListener<T> {
        void RequestResult(String url, T result);
    }


    /**
     * 根据URL返回byte[]数组
     * @param url
     * @return
     */
    private static byte[] getByteInfoByUrl(String url){
        try {
            URL ul = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) ul.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = conn.getInputStream();
            int len = 0;
            byte[] buffer = new byte[1024 * 2];
            while((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过url下载String字符串
     * @param url
     * @return
     */
    public static String getJSONByUrl(String url){
        byte[] buffer = getByteInfoByUrl(url);
        if(buffer != null){
            try {
                return new String(buffer, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过url下载图片对象
     * @param url
     * @return
     */
    public static Bitmap getBitmapByUrl(String url){
        Bitmap bitmap = null;
        byte[] buffer = getByteInfoByUrl(url);
        if(buffer != null){
            bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
        }
        return bitmap;
    }

    /**
     * 获取json数据
     * @return
     */
    public static String getScenicJsonByTxt(){

        File file = new File("");

        return null;
    }


    /**
     * 读取assets下的txt文件，返回utf-8 String
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context,String fileName){
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }
}