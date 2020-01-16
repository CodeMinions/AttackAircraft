package me.codeminions.attackaircraftproject.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.tool.BackMusic;

/**
 * 创建时间：2018/10/16 2:05
 * 描述：TODO
 */
public class Voice_Key extends LinearLayout {

    Button key;
    public Voice_Key(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.voice_btn, this);
        key = (Button) findViewById(R.id.voice);

        key.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(BackMusic.isMusic) {
                    key.setBackgroundResource(R.drawable.off_voice);
                    BackMusic.stop((Activity) getContext());
                }else{
//                    key.setBackgroundResource(R.drawable.on_voice);
//                    BackMusic.play((Activity) getContext(), R.raw.back_bgm);
                }
            }
        });
    }
}
