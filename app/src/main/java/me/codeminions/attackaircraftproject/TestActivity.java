package me.codeminions.attackaircraftproject;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import me.codeminions.attackaircraftproject.ui.BaseActivity;
import me.codeminions.attackaircraftproject.tool.L;

public class TestActivity extends BaseActivity {

    public static TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String info = getIntent().getStringExtra("device");
        text = (TextView) findViewById(R.id.lvText);
        text.setText(info);

        BmobPushManager bmobPushManager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        query.addWhereEqualTo("installationId", info);
        bmobPushManager.setQuery(query);
        bmobPushManager.pushMessage("消息内容", new PushListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    L.e("推送成功！");
                    Toast.makeText(TestActivity.this, "推送成功！", Toast.LENGTH_SHORT).show();
                } else {
                    L.e("异常：" + e.getMessage());
                    Toast.makeText(TestActivity.this, "推送成功！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
