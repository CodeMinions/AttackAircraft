package me.codeminions.attackaircraftproject.until;

import android.content.Context;
import android.util.Log;

/**
 * 创建时间：2018/10/3 16:51
 * 描述：Log的包装类
 */
public class L {

    //DEBUG开关
    public static final boolean DEBUG = true;
    //标签
    public static final String TAG = "AttackAircraftProject";

    public static void d(String str) {
        if(DEBUG)
            Log.d(TAG, str);
    }
    public static void i(String str) {
        if(DEBUG)
            Log.i(TAG, str);
    }
    public static void w(String str) {
        if(DEBUG)
            Log.w(TAG, str);
    }
    public static void e(String str) {
        if(DEBUG)
            Log.e(TAG, str);
    }
}
