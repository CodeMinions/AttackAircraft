package me.codeminions.attackaircraftproject.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import java.io.Serializable;

import me.codeminions.attackaircraftproject.until.StaticClass;

/**
 * 创建时间：2018/10/3 16:26
 * 描述：button的包装类
 */
public class Block extends AppCompatButton implements Serializable{

    private int line, list;

    //标记为飞机
    public boolean isPlane = false;
    //标记为机头
    public boolean isTop = false;
    //标识方向，上下左右0
    public int isDirection = StaticClass.NULL;
    //标记为错误位置
    public boolean isFalse = false;
    ////标记为被选中
    public boolean isSelect = false;
    //标记为已爆炸
    public boolean isBoom = false;


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