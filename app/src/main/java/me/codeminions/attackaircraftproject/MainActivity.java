package me.codeminions.attackaircraftproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.codeminions.attackaircraftproject.ui.ChooseModeActivity;
import me.codeminions.attackaircraftproject.tool.Tools;

/**
 * 创建时间：2018/10/3 14:02
 * 描述：主界面
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.btn_start) Button start_button;
    @BindView(R.id.btn_txt) Button txt_button;
    @BindView(R.id.btn_finish) Button out_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        start_button.setOnClickListener(this);
        txt_button.setOnClickListener(this);
        out_button.setOnClickListener(this);
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
                AlertDialog.Builder dialog = Tools.setDialog(this, "确定退出？", null);
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
