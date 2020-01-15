package me.codeminions.attackaircraftproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.BackMusic;
import me.codeminions.attackaircraftproject.tool.SoundPoolUtil;

/**
 * 创建时间：2018/10/16 1:34
 * 描述：TODO
 */
public abstract class ClickActivity extends BaseActivity {
    private SoundPoolUtil soundPoolUtil;
    private int soundId = 0;
    //默认播放 music1
    private MusicType musicType = MusicType.FIRST;

    /**
     * 设置点击按钮音乐类型
     *
     * @param musicType FIRST SECOND THIRD三个参数
     */
    public void setMusicType(MusicType musicType) {
        this.musicType = musicType;
    }

    /**
     * 定义枚举来限定按钮音乐类型
     */
    public enum MusicType {
        FIRST, SECOND, THIRD
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BackMusic.play(this, R.raw.back_bgm);
        soundPoolUtil = SoundPoolUtil.getInstance(this);

        ActivityCollector.addActivity(this);
    }


    /**
     * 在该方法中调用setMusicType来控制点击音乐类型
     * @param
     */
    public abstract void click();


    public void setType() {
        click();
        switch (musicType) {
            case FIRST:
                soundId = 1;
                break;
            case SECOND:
                soundId = 2;
                break;
            case THIRD:
                soundId = 3;
                break;
        }
        soundPoolUtil.play();
    }

}
