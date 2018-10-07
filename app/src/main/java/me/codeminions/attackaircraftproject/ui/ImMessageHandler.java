package me.codeminions.attackaircraftproject.ui;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import me.codeminions.attackaircraftproject.MainActivity;
import me.codeminions.attackaircraftproject.TestActivity;

/**
 * 创建时间：2018/10/7 17:05
 * 描述：TODO
 */
public class ImMessageHandler extends BmobIMMessageHandler {

    private int count = 0;
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);

        count++;
        if(count == 2){
            Man_ManActivity.BmobData = messageEvent.getMessage().getContent();
        }else if(count == 1){
            TestActivity.text.append("接收到："+messageEvent.getMessage().getContent()+"\n");
        }else{
            Man_ManActivity.be_Boom_loc = messageEvent.getMessage().getContent();
        }

    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }
}
