package com.tdy.tdytravel.uitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by tangdayi on 2017/5/7.
 * 作者:tangdayi
 * 日期:2017年05月07日15时37分
 * 文件:OrderBean.java
 * 工程:tdytravel
 */
public class KeyUtils {
	public static String MD5(String sourceStr) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
			return result.substring(8, 24);
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}
}
