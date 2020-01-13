package me.codeminions.attackaircraftproject.tool;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;
import me.codeminions.attackaircraftproject.ui.BaseActivity;
import me.codeminions.attackaircraftproject.ui.DeviceLinkActivity;
import me.codeminions.attackaircraftproject.ui.Man_ManActivity;

/**
 * 创建时间：2018/10/7 17:05
 * 描述：TODO
 */

//TODO 集成：1.3、创建自定义的推送消息接收器，并在清单文件中注册
public class MyPushMessageReceiver extends BroadcastReceiver {

    String jsonStr;
    String content;
    String flag;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "客户端收到推送内容：" + intent.getStringExtra("msg"));
            L.d("客户端收到的的消息： " + intent.getStringExtra("msg"));


            jsonStr = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
            content = null;

            try {
                // 处理JSON
                JSONObject jsonObject = new JSONObject(jsonStr);
                content = jsonObject.getString("alert");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            flag = content.substring(0, 3);
            content = content.substring(3);
            BaseActivity.BmobData = content;

            //调用man中的方法直接更改界面
            Message msg = new Message();
            if(flag.equals("loc")) {
                String arr[] = content.split("#");
                if(!arr[0].equals(DeviceLinkActivity.simple_Id)) {
                    BaseActivity.BmobData = arr[1];
                    msg.what = 4;
                    Man_ManActivity.handler.sendMessage(msg);
                }
            }

        }

    }

}