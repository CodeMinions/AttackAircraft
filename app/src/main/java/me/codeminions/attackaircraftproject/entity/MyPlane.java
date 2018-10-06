package me.codeminions.attackaircraftproject.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import me.codeminions.attackaircraftproject.until.Location;

/**
 * 创建时间：2018/10/5 2:25
 * 描述：我方的飞机信息
 */
public class MyPlane {
    HashMap<Location, ArrayList<Location>> my_map;

    public MyPlane(HashMap<Location, ArrayList<Location>> my_map) {
        this.my_map = my_map;
    }
}
