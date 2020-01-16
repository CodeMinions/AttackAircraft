package me.codeminions.attackaircraftproject.tool;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 创建时间：2018/10/16 0:03
 * 描述：控制音乐
 */
public class BackMusic {

    private static MediaPlayer mp = null;
    public static boolean isMusic = false;

    public static void play(Context context,int resource){
        stop(context);

        isMusic = true;
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
    }

    public static void stop(Context context) {
        // TODO Auto-generated method stub
        if(mp!= null){
            isMusic = false;
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
