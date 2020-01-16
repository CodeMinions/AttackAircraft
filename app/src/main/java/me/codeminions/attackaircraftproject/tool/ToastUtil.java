package me.codeminions.attackaircraftproject.tool;

import android.content.Context;
import android.widget.Toast;

/**
 * 创建时间：2018/10/10 3:33
 * 描述：中止Toast弹出
 */
public class ToastUtil {

    // Toast对象
    private static Toast toast = null;

    /** * 显示Toast */
    public static void showText(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
