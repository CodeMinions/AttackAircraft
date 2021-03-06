package me.codeminions.attackaircraftproject.tool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 创建时间：2018/10/4 12:46
 * 描述：定义一个序列化集合，使得该数据可以被传递到下一activity
 * 为什么不能是内部类
 */

public class SerMap implements Serializable {
    public HashMap<Location, ArrayList<Location>> map;

    public SerMap(HashMap<Location, ArrayList<Location>> map) {
        this.map = map;
    }

    public HashMap<Location, ArrayList<Location>> getMap() {
        return map;
    }

}
