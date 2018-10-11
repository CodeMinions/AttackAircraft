package me.codeminions.attackaircraftproject.application;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.logging.Logger;

import cn.bmob.newim.BmobIM;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;
import me.codeminions.attackaircraftproject.until.ImMessageHandler;
import me.codeminions.attackaircraftproject.until.L;
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

        /**
         * 初始化比目数据SDK
         */

        //TODO 集成：1.4、初始化数据服务SDK、初始化设备信息并启动推送服务
        // 初始化BmobSDK
        Bmob.initialize(this, StaticClass.BMOB_ID);

        BmobIM.init(this);
        BmobIM.registerDefaultMessageHandler(new ImMessageHandler());

        // 使用推送服务时的初始化操作
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    L.i(bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    L.e(e.getMessage());
                }
            }
        });
// 启动推送服务
        BmobPush.startWork(this);
    }

    public static Context getContext() {
        return context;
    }
}
