package com.tdy.tdytravel.uitls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.LruCache;
/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时37分
 * 文件:OrderBean.java
 * 工程:tdytravel
 *
 * 图片处理工具类
 */
public class ImageUtils {
    private static LruCache<String, Bitmap> lruCache = null;

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static LruCache<String, Bitmap> getImageLruCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int sizeCache = maxMemory / 4;
        LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(
                sizeCache) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
        return lruCache;
    }

    public static void saveToCache(String url, Bitmap bitmap) {
        if (lruCache == null)
            lruCache = getImageLruCache();
        lruCache.put(KeyUtils.MD5(url), bitmap);
    }

    public static Bitmap getToCache(String url) {
        if (lruCache == null)
            lruCache = getImageLruCache();
        return lruCache.get(KeyUtils.MD5(url));
    }

    public static void addBitmapToSdcard(String url, Bitmap bitmap) { // 将Bitmap缓存到Sdcard
        String name = KeyUtils.MD5(url);
        String imgLocalPath = Constants.PATH.LocalCachePath + name;
        File path = new File(Constants.PATH.LocalCachePath);
        if (!path.isDirectory())
            path.mkdirs();
        try {
            FileOutputStream fos = new FileOutputStream(new File(imgLocalPath));
            bitmap.compress(CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromSdcard(String url) { // 从本地Sdcrad中读取本地文件
        String name = KeyUtils.MD5(url);
        File[] fileList = new File(Constants.PATH.LocalCachePath).listFiles();
        Bitmap bitmap = null;
        if (fileList != null) {
            for (File file : fileList) {
                if (file.getName().equals(name))
                    bitmap = getLoacalBitmap(file.getPath());
            }
        }
        return bitmap;
    }

    private static Bitmap getLoacalBitmap(String url) { // 根据本地地址将图片转换成bitmap对象
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
