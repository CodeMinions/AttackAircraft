package me.codeminions.attackaircraftproject.tool;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import me.codeminions.attackaircraftproject.R;

/**
 * 创建时间：2018/10/16 1:23
 * 描述：TODO
 */
public class SoundPoolUtil {
    private static SoundPoolUtil soundPoolUtil;
    private SoundPool soundPool;

    int number;

    //单例模式
    public static SoundPoolUtil getInstance(
            Context context) {
        if (soundPoolUtil == null)
            soundPoolUtil = new SoundPoolUtil(context);
        return soundPoolUtil;
    }

    private SoundPoolUtil(Context context) {
//        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0);
        soundPool = new SoundPool(3, 1, 5);

//        soundPool = new SoundPool.Builder()
//                .setMaxStreams(3)
//                .build();

        //加载音频文件
        number = soundPool.load(context, R.raw.attach_bgm, 1);
    }

    public void play() {
        L.i( "number " + number);
        //播放音频
        soundPool.play(number, 1, 1, 0, 0, 1);
    }

}
