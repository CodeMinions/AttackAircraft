package me.codeminions.attackaircraftproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.BackMusic;
import me.codeminions.attackaircraftproject.tool.SoundPoolUtil;

/**
 * 创建时间：2018/10/4 11:08
 * 描述：TODO
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static String BmobData = null;
    public static boolean isStartGame = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BackMusic.play(this, R.raw.back_bgm);

        ActivityCollector.addActivity(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
//        BackMusic.play(this, R.raw.back_bgm);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackMusic.stop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


}
