package com.tdy.tdytravel.uitls;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 图片缓存工具类
 */
public class VolleyCache implements ImageCache {
    private static VolleyCache instance;
    private LruCache<String, Bitmap> mCache;

    private VolleyCache() {
        //maxsize 如果你不重写Lrucache里面的sizeOf方法
        //maxsize的值就代表这个缓存容器里面的对像个数,如果重写了sizeOf方法
        //那么maxsize的值就代表的是缓存容器所占用的字节总数
//		mCache = new LruCache<String, Bitmap>(100);

        mCache = new LruCache<String, Bitmap>(20 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight() * value.getRowBytes();
            }
        };
    }

    public static VolleyCache getInstance() {
        if (instance == null) {
            synchronized (VolleyCache.class) {
                if (instance == null) {
                    instance = new VolleyCache();
                }
            }
        }
        return instance;
    }

    @Override
    public Bitmap getBitmap(String url) {
//        if (mCache != null) {
//            return mCache.get(url);
//
//        } else {
            L.d("image instance and load");
            return ImageFileCacheUtils.getInstance().getImage(url);
//        }
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
        ImageFileCacheUtils.getInstance().saveBitmap(bitmap, url);
    }


}
