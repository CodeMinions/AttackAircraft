package me.codeminions.attackaircraftproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.codeminions.attackaircraftproject.application.ActivityCollector;

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
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
