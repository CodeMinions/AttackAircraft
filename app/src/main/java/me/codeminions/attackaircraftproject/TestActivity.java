package me.codeminions.attackaircraftproject;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;
import me.codeminions.attackaircraftproject.ui.BaseActivity;
import me.codeminions.attackaircraftproject.until.L;
import me.codeminions.attackaircraftproject.until.Location;

public class TestActivity extends BaseActivity {

    EditText edit;
    public static TextView text;
    boolean isConnect = false;

    boolean isOpenConversation = false;

    BmobIMConversation mBmobIMConversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edit = (EditText) findViewById(R.id.lvDevices);
        text = (TextView) findViewById(R.id.lvText);

        BmobIM.connect(edit.getText().toString(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    isConnect = true;
                    L.i("服务器连接成功...");
                } else {
                    L.i(e.getMessage() + "  " + e.getErrorCode());
                }
            }
        });


        if (!isConnect){
            Toast.makeText(this, "未连接状态不能打开会话", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isOpenConversation){
            BmobIMUserInfo info =new BmobIMUserInfo();
            info.setAvatar("填写接收者的头像");
            info.setUserId(edit.getText().toString());
            info.setName("填写接收者的名字");
            BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
                @Override
                public void done(BmobIMConversation c, BmobException e) {
                    if(e==null){
                        isOpenConversation = true;
                        //在此跳转到聊天页面或者直接转化
                        mBmobIMConversation=BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
                        text.append("发送者："+text.getText().toString()+"\n");
                        BmobIMTextMessage msg =new BmobIMTextMessage();
                        msg.setContent(text.getText().toString());
                        mBmobIMConversation.sendMessage(msg, new MessageSendListener() {
                            @Override
                            public void done(BmobIMMessage msg, BmobException e) {
                                if (e != null) {
                                    text.setText("");
                                }else{
                                }
                            }
                        });
                    }else{
                        Toast.makeText(TestActivity.this, "开启会话出错", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            BmobIMTextMessage msg = new BmobIMTextMessage();
            msg.setContent(text.getText().toString());
            text.append("发送者：" + text.getText().toString() + "\n");
            mBmobIMConversation.sendMessage(msg, new MessageSendListener() {
                @Override
                public void done(BmobIMMessage msg, BmobException e) {
                    if (e != null) {
                        text.setText("");
                    }else{
                    }
                }
            });

        }
    }
}
