package me.codeminions.attackaircraftproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.exception.BmobException;
import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.tool.L;
import me.codeminions.attackaircraftproject.tool.ToastUtil;

/**
 * 创建时间：2018/10/7 5:26
 * 描述：TODO
 */
public class DeviceLinkActivity extends BaseActivity implements View.OnClickListener {

    public static TextView tv_message;
    public static String simple_Id;
    TextView et_myid;
    EditText et_connect_id;
    EditText et_receiver_id;
    EditText et_message;
    Button btn_connect;
    Button btn_send;
    Button btn_start;
    Button btn_sendId;
    boolean isConnect = false;
    boolean isOpenConversation = false;

    BmobIMConversation mBmobIMConversation;

    BmobIMUserInfo info;
    String message;

    public static String enemyId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_link);

        tv_message = (TextView) findViewById(R.id.tv_message);
        et_connect_id = (EditText) findViewById(R.id.et_connect_id);
        et_myid = (TextView) findViewById(R.id.tv_myid);
        et_receiver_id = (EditText) findViewById(R.id.et_receiver_id);
        et_message = (EditText) findViewById(R.id.et_message);
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_sendId = (Button) findViewById(R.id.btn_sendid);
        btn_sendId.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_connect.setOnClickListener(this);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        info = new BmobIMUserInfo();
        et_myid.append(BmobInstallationManager.getInstallationId());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent();
        intent.putExtra("device", enemyId);
        setResult(RESULT_CANCELED, intent);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_connect:
                link();
                break;
            case R.id.btn_send:
                send(et_message.getText().toString());
                break;
            case R.id.btn_sendid:
                send(BmobInstallationManager.getInstallationId());
                btn_send.setClickable(false);
                btn_sendId.setVisibility(View.GONE);
                btn_start.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_start:
                Intent intent = new Intent();
                intent.putExtra("device", enemyId);
                setResult(RESULT_OK, intent);

                finish();
                break;
        }
    }

    private void link(){
        if (TextUtils.isEmpty(et_connect_id.getText().toString())){
            Toast.makeText(this, "请填写连接id", Toast.LENGTH_SHORT).show();
            return;
        }

        simple_Id = et_connect_id.getText().toString();
        BmobIM.connect(et_connect_id.getText().toString(), new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    isConnect = true;
                    L.i("服务器连接成功");
                    btn_connect.setClickable(false);

                    ToastUtil.showText(DeviceLinkActivity.this, "服务器连接成功");
                    ToastUtil.showText(DeviceLinkActivity.this, "可通过发送会话确认");

                    btn_sendId.setVisibility(View.VISIBLE);
                }else {
                    L.i(e.getMessage() + "  " + e.getErrorCode());
                }
            }
        });
    }


    private void send(final String xiaoxi){

        if (!isConnect){
            Toast.makeText(this, "未连接状态不能打开会话", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isOpenConversation){
            info.setAvatar("接收者的头像");
            info.setUserId(et_receiver_id.getText().toString());
            info.setName("接收者的名字");

            BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
                @Override
                public void done(BmobIMConversation c, BmobException e) {
                    if(e == null){
                        isOpenConversation = true;
                        //在此跳转到聊天页面或者直接转化
                        mBmobIMConversation = BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
                        tv_message.append("发送者：" + xiaoxi + "\n");
                        BmobIMTextMessage msg =new BmobIMTextMessage();
                        msg.setContent(xiaoxi);
                        mBmobIMConversation.sendMessage(msg, new MessageSendListener() {
                            @Override
                            public void done(BmobIMMessage msg, BmobException e) {
                                if (e != null) {
                                    et_message.setText("");
                                }else{
                                }
                            }
                        });
                    }else{
                        Toast.makeText(DeviceLinkActivity.this, "开启会话出错", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            BmobIMTextMessage msg1 = new BmobIMTextMessage();
            msg1.setContent(xiaoxi);
            tv_message.append("发送者：" + xiaoxi + "\n");
            mBmobIMConversation.sendMessage(msg1, new MessageSendListener() {
                @Override
                public void done(BmobIMMessage msg, BmobException e) {
                    if (e != null) {
                        et_message.setText("");
                    }else{
                    }
                }
            });
        }
    }

}


