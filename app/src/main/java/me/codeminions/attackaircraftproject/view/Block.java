package me.codeminions.attackaircraftproject.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import me.codeminions.attackaircraftproject.until.StaticClass;

/**
 * 创建时间：2018/10/3 16:26
 * 描述：button的包装类
 */
public class Block extends AppCompatButton {

    private int line, list;

    //标记
    public boolean isPlane = false;

    public boolean isTop = false;
    //标识方向，上下左右0
    public int isDirection = StaticClass.NULL;


    public int getLine() {
        return line;
    }

    public int getList() {
        return list;
    }

    public Block(Context context, int x, int y){
        super(context);
        line = x;
        list = y;
    }
}