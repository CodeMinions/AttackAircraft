package me.codeminions.attackaircraftproject.tool;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import me.codeminions.attackaircraftproject.ui.DeviceLinkActivity;

/**
 * 创建时间：2018/10/8 23:24
 * 描述：TODO
 */
public class ImMessageHandler extends BmobIMMessageHandler {

    private int count = 0;
    @Override
    public void onMessageReceive(MessageEvent messageEvent) {
        super.onMessageReceive(messageEvent);

        DeviceLinkActivity.tv_message.append("接收到："+messageEvent.getMessage().getContent()+"\n");
        DeviceLinkActivity.enemyId = messageEvent.getMessage().getContent();
    }

    @Override
    public void onOfflineReceive(OfflineMessageEvent offlineMessageEvent) {
        super.onOfflineReceive(offlineMessageEvent);
    }
}
