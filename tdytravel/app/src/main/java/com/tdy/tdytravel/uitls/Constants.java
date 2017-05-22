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
}
