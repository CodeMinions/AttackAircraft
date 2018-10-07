package me.codeminions.attackaircraftproject.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2018/10/6 14:49
 * 描述：管理所有的activity
 */
public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        activity.finish();
        activityList.remove(activity);
    }
    public static void finishAll(){
        for(Activity a:activityList){
            if(a.isFinishing()){
                a.finish();
            }
        }
        activityList.clear();
    }
}
