package me.codeminions.attackaircraftproject.entity;

import java.util.ArrayList;
import java.util.HashMap;

import me.codeminions.attackaircraftproject.until.Location;

/**
 * 创建时间：2018/10/5 2:32
 * 描述：敌方的飞机信息
 */
public class EnemyPlane {
    HashMap<Location, ArrayList<Location>> enemy_map;

    public EnemyPlane(HashMap<Location, ArrayList<Location>> my_map) {
        this.enemy_map = my_map;
    }
}
