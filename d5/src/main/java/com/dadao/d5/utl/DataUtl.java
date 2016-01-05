package com.dadao.d5.utl;

/*
 *  处理网络请求返回数据的静态方法
 */

public class DataUtl {
	public static boolean isNullorBlank(String s) {
		return s == null || s.length() <= 0;
	}
}
