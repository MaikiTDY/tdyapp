package com.tdy.tdytravel.uitls;

import android.os.Environment;

/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时37分
 * 文件:OrderBean.java
 * 工程:tdytravel
 *
 * 常量
 */
public interface Constants {
	interface PATH {
		public static final String LocalCachePath = Environment
				.getExternalStorageDirectory().getPath()
				+ "/";
	}

	interface NAME {
		public static final String SPNAME = "ws";
	}

	interface URL {
		public static final String HomePageURL = "http://ikft.house.qq.com/index.php?act=homepage&mod=appkft&devua=appkft_800_1280_MeizuM045_2.3_Android21&cityid=3";

	}

    interface ScenicBeanAPI{
        public final static String image = "image";
        public final static String p_time = "p_time";
        public final static String p_name = "p_name";
        public final static String p_desc = "p_desc";
        public final static String p_id = "p_id";
    }

    interface UserBeanAPI{
        public final static String username = "username";
        public final static String password = "password";
        public final static String imageUrl = "imageUrl";
    }

    public final static String hotle_url ="http://58.16.86.13:9001/Interface/api/findHotelList";

    public final static String home_head_vp_image_url = "http://58.16.86.13:9001/Interface/api/getIndex";

    public final static String scenicJson = "../src/data/scenic.txt";
}
