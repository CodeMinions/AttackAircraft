package me.codeminions.attackaircraftproject.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.L;

/**
 * 创建时间：2018/10/4 11:08
 * 描述：TODO
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static String BmobData = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


}
