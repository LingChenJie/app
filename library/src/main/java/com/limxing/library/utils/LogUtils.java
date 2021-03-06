package com.limxing.library.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * 日志输出控制
 * @author limxing
 */
public class LogUtils {
	/** 日志输出级别NONE */
	public static final int LEVEL_NONE = 0;
	/** 日志输出级别V */
	public static final int LEVEL_VERBOSE = 1;
	/** 日志输出级别D */
	public static final int LEVEL_DEBUG = 2;
	/** 日志输出级别I */
	public static final int LEVEL_INFO = 3;
	/** 日志输出级别W */
	public static final int LEVEL_WARN = 4;
	/** 日志输出级别E */
	public static final int LEVEL_ERROR = 5;

	/** 日志输出时的TAG */
	private static String mTag = "limxingg";
	/** 是否允许输出log */
	private static int mDebuggable = LEVEL_ERROR;

	/** 用于记时的变�?*/
	private static long mTimestamp = 0;
	/** 写文件的锁对�?*/
	private static final Object mLogLock = new Object();

	/** 以级别为 d 的形式输出LOG */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/** 以级别为 d 的形式输出LOG */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	/** 以级别为 i 的形式输出LOG */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出LOG */
	public static void w(String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}

	/** 以级别为 w 的形式输出Throwable */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/** 以级别为 w 的形式输出LOG信息和Throwable */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/** 以级别为 e 的形式输出LOG */
	public static void e(String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}

	/** 以级别为 e 的形式输出Throwable */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/** 以级别为 e 的形式输出LOG信息和Throwable */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}

	/**
	 * 把Log存储到文件中
	 * @param log  �?��存储的日�?
	 * @param path 存储路径
	 */
	public static void log2File(String log, String path) {
		log2File(log, path, true);
	}

	public static void log2File(String log, String path, boolean append) {
		synchronized (mLogLock) {
			FileUtils.writeFile(log + "\r\n", path, append);
		}
	}

	/**
	 * 以级别为 e 的形式输出msg信息,附带时间戳，用于输出�?��时间段起始点
	 * @param msg �?��输出的msg
	 */
	public static void msgStartTime(String msg) {
		mTimestamp = System.currentTimeMillis();
		if (!TextUtils.isEmpty(msg)) {
			//e("[Started�? + mTimestamp + "]" + msg);
		}
	}

	/** 以级别为 e 的形式输出msg信息,附带时间戳，用于输出�?��时间段结束点* @param msg �?��输出的msg */
	public static void elapsed(String msg) {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - mTimestamp;
		mTimestamp = currentTime;
		//e("[Elapsed�? + elapsedTime + "]" + msg);
	}

	public static <T> void printList(List<T> list) {
		if (list == null || list.size() < 1) {
			return;
		}
		int size = list.size();
		i("---begin---");
		for (int i = 0; i < size; i++) {
			i(i + ":" + list.get(i).toString());
		}
		i("---end---");
	}

	public static <T> void printArray(T[] array) {
		if (array == null || array.length < 1) {
			return;
		}
		int length = array.length;
		i("---begin---");
		for (int i = 0; i < length; i++) {
			i(i + ":" + array[i].toString());
		}
		i("---end---");
	}

	/**
	 * 添加日志打印是否上线
	 * @param b
     */
	public void isOnline(boolean b){
		if(b){
		 mDebuggable = LEVEL_NONE;
		}else{
			mDebuggable = LEVEL_ERROR;
		}
	}

	/**
	 * 添加两个带类名得日志结果输出
	 * @param activity
	 * @param msg
     */
	public static void i(Activity activity, String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, activity.getClass().getSimpleName()+":"+msg);
		}
	}
	public static void i(Class classd, String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag,classd.getSimpleName()+":"+msg);
		}
	}
}
