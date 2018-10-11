package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import me.codeminions.attackaircraftproject.R;

/**
 * 创建时间：2018/10/7 5:16
 * 描述：选择对战模式，人人或人机
 */
public class ChooseModeActivity extends BaseActivity {

    RadioButton button1;
    RadioButton button2;

    public static void actionStart(Context context){
        context.startActivity(new Intent(context, ChooseModeActivity.class));
    }

    private String info;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);

        button1 =  (RadioButton) findViewById(R.id.btn_ptp);
        button2 =  (RadioButton) findViewById(R.id.btn_ptm);
        Button button3 =  (Button) findViewById(R.id.btn_start);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                button1.setChecked(true);
                button2.setChecked(false);
                startActivityForResult(new Intent(ChooseModeActivity.this, DeviceLinkActivity.class), 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                button2.setChecked(true);
                button1.setChecked(false);
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(button2.isChecked()){
                    ReadyActivity.actionStart(v.getContext(), null);
                }else if(button1.isChecked()){
                    ReadyActivity.actionStart(v.getContext(), info);
                }else{
                    Toast.makeText(ChooseModeActivity.this, "请选择一个...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        info = data.getStringExtra("device");
        button1.setChecked(true);
    }
}
