package com.tdy.tdytravel.uitls;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tdy.house.application.LHApplication;
import com.tdy.house.data.User;

public class SystemUtil {
	private static String path = "me.haloxin.haodou.spdata";

	public static void SaveUserInfo(Context context, User mUser) {
		SharedPreferences sPreferences = context.getSharedPreferences(path,
				Context.MODE_PRIVATE);
		LHApplication.setUser(mUser);
		Editor editor = sPreferences.edit();
		editor.putString("username", mUser.getUsername());
		editor.commit();
	}

	public static User ReadUserInfo(Context context) {
		SharedPreferences sPreferences = context.getSharedPreferences(path,
				Context.MODE_PRIVATE);
		User mUser = new User();
		mUser.setUsername(sPreferences.getString("username", null));
		return mUser;
	}

	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}
}
