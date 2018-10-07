package me.codeminions.attackaircraftproject.application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.bmob.newim.BmobIM;
import cn.bmob.v3.Bmob;
import me.codeminions.attackaircraftproject.ui.ImMessageHandler;
import me.codeminions.attackaircraftproject.until.StaticClass;


/**
 * 创建时间：2018/10/4 11:06
 * 描述：TODO
 */
public class BaseApplication extends Application {

    private static Context  context;

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, StaticClass.BMOB_ID);
        BmobIM.init(this);
        BmobIM.registerDefaultMessageHandler(new ImMessageHandler());
    }

    public static Context getContext() {
        return context;
    }
}
