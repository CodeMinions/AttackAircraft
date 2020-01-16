package me.codeminions.attackaircraftproject;

import android.content.DialogInterface;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.annotations.Until;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.codeminions.attackaircraftproject.tool.SoundPoolUtil;
import me.codeminions.attackaircraftproject.ui.ChooseModeActivity;
import me.codeminions.attackaircraftproject.tool.UtilTools;
import me.codeminions.attackaircraftproject.ui.TextActivity;

/**
 * 创建时间：2018/10/3 14:02
 * 描述：主界面
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_start) Button start_button;
    @BindView(R.id.btn_txt) Button txt_button;
    @BindView(R.id.btn_finish) Button out_button;
    @BindView(R.id.name)
    TextView name_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        start_button.setOnClickListener(this);
        txt_button.setOnClickListener(this);
        out_button.setOnClickListener(this);

        UtilTools.setFont(this, name_text ,"name1");
        UtilTools.setFont(this, start_button, "title");
        UtilTools.setFont(this, txt_button, "title");
        UtilTools.setFont(this, out_button, "title");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                ChooseModeActivity.actionStart(this);
                break;
            case R.id.btn_txt:
                TextActivity.actionStart(this);
                break;
            case R.id.btn_finish:
                AlertDialog.Builder dialog = UtilTools.setDialog(this, "确定退出？", null);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.setCancelable(true);
                dialog.show();
                break;
        }
    }
}
