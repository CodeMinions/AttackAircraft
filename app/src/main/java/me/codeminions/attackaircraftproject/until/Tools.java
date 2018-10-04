package me.codeminions.attackaircraftproject.until;

import java.util.ArrayList;
import java.util.HashMap;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.view.Block;

import static java.lang.Math.random;

/**
 * 创建时间：2018/10/4 15:28
 * 描述：用于系统自动生成飞机位置
 */
public class Tools {
    public static Location RandoLocation(){

        double temp_x = Math.random()*10;
        double temp_y = Math.random()*10;
        int x = (int) temp_x*10;
        int y = (int) temp_y*10;

        return new Location(x, y);
    }

    /**
     * 进来一个坐标和地图，返回一个数组，里面是一架飞机的坐标，首个为机头（即进来的的坐标）
     * */
//    public static ArrayList<Location> getPlaneBody(Location l, Block[][] a){
//        int x = l.x;
//        int y = l.y;
//
//        a[x + 3][y].setBackgroundResource(R.drawable.air_top_t);
//        a[x + 2][y].setBackgroundResource(R.drawable.air_body_t);
//        a[x + 1][y].setBackgroundResource(R.drawable.air_body_end_t);
//        a[x][y].setBackgroundResource(R.drawable.air_end_t);
//        a[x][y - 1].setBackgroundResource(R.drawable.air_end2_t);
//        a[x][y + 1].setBackgroundResource(R.drawable.air_end1_t);
//        a[x + 2][y + 1].setBackgroundResource(R.drawable.air_right2_t);
//        a[x + 2][y + 2].setBackgroundResource(R.drawable.air_right1_t);
//        a[x + 2][y - 1].setBackgroundResource(R.drawable.air_left2_t);
//        a[x + 2][y - 2].setBackgroundResource(R.drawable.air_left1_t);
//
//        a[x + 3][y].isPlane = true;
//        a[x + 2][y].isPlane = true;
//        a[x + 1][y].isPlane = true;
//        a[x][y].isPlane = true;
//        a[x][y - 1].isPlane = true;
//        a[x][y + 1].isPlane = true;
//        a[x + 2][y + 1].isPlane = true;
//        a[x + 2][y + 2].isPlane = true;
//        a[x + 2][y - 1].isPlane = true;
//        a[x + 2][y - 2].isPlane = true;
//
//        ArrayList<Location> plane_l = new ArrayList<>();
//        plane_l.add(new Location(x + 2, y));
//        plane_l.add(new Location(x + 1, y));
//        plane_l.add(new Location(x, y));
//        plane_l.add(new Location(x, y - 1));
//        plane_l.add(new Location(x, y + 1));
//        plane_l.add(new Location(x + 2, y + 1));
//        plane_l.add(new Location(x + 2, y + 2));
//        plane_l.add(new Location(x + 2, y - 1));
//        plane_l.add(new Location(x + 2, y - 2));
//
//        try {
//            a[x + 6][y].setBackgroundResource(R.drawable.block_bg);
//            a[x + 6][y].isDirection = StaticClass.NULL;
//        } catch (ArrayIndexOutOfBoundsException e) {
//        }
//
//        a[x][y].isDirection = StaticClass.NULL;
//
//        try {
//            a[x + 3][y + 3].setBackgroundResource(R.drawable.block_bg);
//            a[x + 3][y + 3].isDirection = StaticClass.NULL;
//        } catch (ArrayIndexOutOfBoundsException e) {
//        }
//        try {
//            a[x + 3][y - 3].setBackgroundResource(R.drawable.block_bg);
//            a[x + 3][y - 3].isDirection = StaticClass.NULL;
//        } catch (ArrayIndexOutOfBoundsException e) {
//        }
//    }
}
