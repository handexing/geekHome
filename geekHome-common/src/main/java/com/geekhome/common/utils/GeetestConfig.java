package com.geekhome.common.utils;

/**
 * GeetestWeb配置文件
 */
public class GeetestConfig {

	private static final String geetest_id = "bb6bdfcfe2cffaa85a35f379e3203f80";
	private static final String geetest_key = "e651c3be94cc3f8c8418b4d547a94021";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
