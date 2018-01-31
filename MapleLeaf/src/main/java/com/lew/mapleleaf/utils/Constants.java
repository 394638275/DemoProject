package com.lew.mapleleaf.utils;

import android.os.Environment;


public final class Constants {
	
	public static final int HTTP_CONN_TIME_OUT = 8000; // HTTP 连接超时时间
	public static final int HTTP_READ_TIME_OUT = 8000; // HTTP 读取数据超时时间

	public static final class URL {

	}

	public static final class PREFS {
		public static final String USER_LOGIN_INFO = "user_login_info";

	}

	public static final class FILE {
		// SDCard路径
		public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		// 主目录
		public static final String BASE_DIR = SD_PATH + "/mapleleaf";
	}
}
