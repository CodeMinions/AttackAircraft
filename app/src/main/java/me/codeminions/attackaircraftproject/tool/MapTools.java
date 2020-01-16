package me.codeminions.attackaircraftproject.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/20 15:40
 * 描述：TODO
 */
public class MapTools {

    /**
     * 进来一个坐标和地图，返回一个数组，里面是一架飞机的坐标，首个为机头（即进来的的坐标）
     * 现为未改进版，是传入一个坐标，以此为end，向传入方向的反向生成飞机
     **/
    public static ArrayList<Location> getPlaneBody(Location l, Block[][] a, int dre) {

        int x = l.x;
        int y = l.y;

        ArrayList<Location> plane_l = new ArrayList<>();


        switch (dre) {
            case StaticClass.NULL:
                break;

                //机头朝下
            case StaticClass.TOP:
                try {
                    a[x + 3][y].setBackgroundResource(R.drawable.air_top_t);
                    a[x + 2][y].setBackgroundResource(R.drawable.air_body_t);
                    a[x + 1][y].setBackgroundResource(R.drawable.air_body_end_t);
                    a[x][y].setBackgroundResource(R.drawable.air_end_t);
                    a[x][y - 1].setBackgroundResource(R.drawable.air_end2_t);
                    a[x][y + 1].setBackgroundResource(R.drawable.air_end1_t);
                    a[x + 2][y + 1].setBackgroundResource(R.drawable.air_right2_t);
                    a[x + 2][y + 2].setBackgroundResource(R.drawable.air_right1_t);
                    a[x + 2][y - 1].setBackgroundResource(R.drawable.air_left2_t);
                    a[x + 2][y - 2].setBackgroundResource(R.drawable.air_left1_t);

                    a[x + 3][y].isPlane = true;
                    a[x + 2][y].isPlane = true;
                    a[x + 1][y].isPlane = true;
                    a[x][y].isPlane = true;
                    a[x][y - 1].isPlane = true;
                    a[x][y + 1].isPlane = true;
                    a[x + 2][y + 1].isPlane = true;
                    a[x + 2][y + 2].isPlane = true;
                    a[x + 2][y - 1].isPlane = true;
                    a[x + 2][y - 2].isPlane = true;

                    plane_l.add(new Location(x + 3, y));
                    plane_l.add(new Location(x + 2, y));
                    plane_l.add(new Location(x + 1, y));
                    plane_l.add(new Location(x, y));
                    plane_l.add(new Location(x, y - 1));
                    plane_l.add(new Location(x, y + 1));
                    plane_l.add(new Location(x + 2, y + 1));
                    plane_l.add(new Location(x + 2, y + 2));
                    plane_l.add(new Location(x + 2, y - 1));
                    plane_l.add(new Location(x + 2, y - 2));
                }catch (ArrayIndexOutOfBoundsException e){
                    L.i("溢出了...");
                }

                try {
                    if (!a[x + 6][y].isPlane)
                        a[x + 6][y].setBackgroundResource(R.drawable.block_bg);
                    a[x + 6][y].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                try {
                    if (!a[x + 3][y + 3].isPlane)
                        a[x + 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y + 3].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (!a[x + 3][y - 3].isPlane)
                        a[x + 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y - 3].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                cleanMap(a);

                break;
            case StaticClass.LEFT:
                try {
                    a[x][y + 3].setBackgroundResource(R.drawable.air_top_l);
                    a[x][y + 2].setBackgroundResource(R.drawable.air_body_l);
                    a[x][y + 1].setBackgroundResource(R.drawable.air_body_end_l);
                    a[x][y].setBackgroundResource(R.drawable.air_end_l);
                    a[x + 1][y].setBackgroundResource(R.drawable.air_end2_l);
                    a[x - 1][y].setBackgroundResource(R.drawable.air_end1_l);
                    a[x + 1][y + 2].setBackgroundResource(R.drawable.air_right2_l);
                    a[x + 2][y + 2].setBackgroundResource(R.drawable.air_right1_l);
                    a[x - 1][y + 2].setBackgroundResource(R.drawable.air_left2_l);
                    a[x - 2][y + 2].setBackgroundResource(R.drawable.air_left1_l);


                    a[x][y + 3].isPlane = true;
                    a[x][y + 2].isPlane = true;
                    a[x][y + 1].isPlane = true;
                    a[x][y].isPlane = true;
                    a[x + 1][y].isPlane = true;
                    a[x - 1][y].isPlane = true;
                    a[x + 1][y + 2].isPlane = true;
                    a[x + 2][y + 2].isPlane = true;
                    a[x - 1][y + 2].isPlane = true;
                    a[x - 2][y + 2].isPlane = true;

                    plane_l.add(new Location(x, y + 3));
                    plane_l.add(new Location(x, y + 2));
                    plane_l.add(new Location(x, y + 1));
                    plane_l.add(new Location(x, y));
                    plane_l.add(new Location(x - 1, y));
                    plane_l.add(new Location(x + 1, y));
                    plane_l.add(new Location(x + 1, y + 2));
                    plane_l.add(new Location(x + 2, y + 2));
                    plane_l.add(new Location(x - 1, y + 2));
                    plane_l.add(new Location(x - 2, y + 2));
                }catch (ArrayIndexOutOfBoundsException e){
                    L.i("溢出了...");
                }

                try {
                    if (!a[x + 3][y + 3].isPlane)
                        a[x + 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y + 3].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (!a[x - 3][y + 3].isPlane)
                        a[x - 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x - 3][y + 3].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (!a[x][y + 6].isPlane)
                        a[x][y + 6].setBackgroundResource(R.drawable.block_bg);
                    a[x][y + 6].isDirection = StaticClass.NULL;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                cleanMap(a);

                break;
            case StaticClass.RIGHTP:
                try {
                    a[x][y - 3].setBackgroundResource(R.drawable.air_top_r);
                    a[x][y - 2].setBackgroundResource(R.drawable.air_body_r);
                    a[x][y - 1].setBackgroundResource(R.drawable.air_body_end_r);
                    a[x][y].setBackgroundResource(R.drawable.air_end_r);
                    a[x - 1][y].setBackgroundResource(R.drawable.air_end2_r);
                    a[x + 1][y].setBackgroundResource(R.drawable.air_end1_r);
                    a[x - 1][y - 2].setBackgroundResource(R.drawable.air_right2_r);
                    a[x - 2][y - 2].setBackgroundResource(R.drawable.air_right1_r);
                    a[x + 1][y - 2].setBackgroundResource(R.drawable.air_left2_r);
                    a[x + 2][y - 2].setBackgroundResource(R.drawable.air_left1_r);

                    a[x][y - 3].isPlane = true;
                    a[x][y - 2].isPlane = true;
                    a[x][y - 1].isPlane = true;
                    a[x][y].isPlane = true;
                    a[x - 1][y].isPlane = true;
                    a[x + 1][y].isPlane = true;
                    a[x - 1][y - 2].isPlane = true;
                    a[x - 2][y - 2].isPlane = true;
                    a[x + 1][y - 2].isPlane = true;
                    a[x + 2][y - 2].isPlane = true;

                    plane_l.add(new Location(x, y - 3));
                    plane_l.add(new Location(x, y - 2));
                    plane_l.add(new Location(x, y - 1));
                    plane_l.add(new Location(x, y));
                    plane_l.add(new Location(x - 1, y));
                    plane_l.add(new Location(x + 1, y));
                    plane_l.add(new Location(x - 1, y - 2));
                    plane_l.add(new Location(x - 2, y - 2));
                    plane_l.add(new Location(x + 1, y - 2));
                    plane_l.add(new Location(x + 2, y - 2));
                }catch (ArrayIndexOutOfBoundsException e){
                    L.i("溢出了...");
                }

                try {
                    if (!a[x + 3][y - 3].isPlane) {
                        a[x + 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x + 3][y - 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (!a[x - 3][y - 3].isPlane) {
                        a[x - 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y - 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                try {
                    if (!a[x][y - 6].isPlane) {
                        a[x][y - 6].setBackgroundResource(R.drawable.block_bg);
                        a[x][y - 6].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                cleanMap(a);

                break;
            case StaticClass.BOTTOM:
                try {
                    a[x - 3][y].setBackgroundResource(R.drawable.air_top_b);
                    a[x - 2][y].setBackgroundResource(R.drawable.air_body_b);
                    a[x - 1][y].setBackgroundResource(R.drawable.air_body_end_b);
                    a[x][y].setBackgroundResource(R.drawable.air_end_b);
                    a[x][y + 1].setBackgroundResource(R.drawable.air_end2_b);
                    a[x][y - 1].setBackgroundResource(R.drawable.air_end1_b);
                    a[x - 2][y + 1].setBackgroundResource(R.drawable.air_right2_b);
                    a[x - 2][y + 2].setBackgroundResource(R.drawable.air_right1_b);
                    a[x - 2][y - 1].setBackgroundResource(R.drawable.air_left2_b);
                    a[x - 2][y - 2].setBackgroundResource(R.drawable.air_left1_b);

                    a[x - 3][y].isPlane = true;
                    a[x - 2][y].isPlane = true;
                    a[x - 1][y].isPlane = true;
                    a[x][y].isPlane = true;
                    a[x][y + 1].isPlane = true;
                    a[x][y - 1].isPlane = true;
                    a[x - 2][y + 1].isPlane = true;
                    a[x - 2][y + 2].isPlane = true;
                    a[x - 2][y - 1].isPlane = true;
                    a[x - 2][y - 2].isPlane = true;

                    plane_l.add(new Location(x - 3, y));
                    plane_l.add(new Location(x - 2, y));
                    plane_l.add(new Location(x - 1, y));
                    plane_l.add(new Location(x, y));
                    plane_l.add(new Location(x, y - 1));
                    plane_l.add(new Location(x, y + 1));
                    plane_l.add(new Location(x - 2, y + 1));
                    plane_l.add(new Location(x - 2, y + 2));
                    plane_l.add(new Location(x - 2, y - 1));
                    plane_l.add(new Location(x - 2, y - 2));
                }catch (ArrayIndexOutOfBoundsException e){
                    L.i("溢出了...");
                }

                // FIXME: 2018/10/4 这是啥？？
//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                try {
                    if (!a[x - 6][y].isPlane) {
                        a[x - 6][y].setBackgroundResource(R.drawable.block_bg);
                        a[x - 6][y].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (!a[x - 3][y + 3].isPlane) {
                        a[x - 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y + 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                try {
                    if (!a[x - 3][y - 3].isPlane) {
                        a[x - 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y - 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                cleanMap(a);
                L.i("lalalalala");

                break;
        }

        if (!a[x][y].isPlane) {

            L.i("(" + String.valueOf(x) + "," +
                    String.valueOf(y) + ")");

            if (a[x][y].isTop) {
                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isTop = false;
                try {
                    //方向取消时飞机也不能被替换
                    if (!a[x + 3][y].isPlane) {
                        a[x + 3][y].setBackgroundResource(R.drawable.block_bg);
                        a[x + 3][y].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (!a[x - 3][y].isPlane) {
                        a[x - 3][y].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (!a[x][y + 3].isPlane) {
                        a[x][y + 3].setBackgroundResource(R.drawable.block_bg);
                        a[x][y + 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (!a[x][y - 3].isPlane) {
                        a[x][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x][y - 3].isDirection = StaticClass.NULL;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }

            } else {
                a[x][y].setBackgroundResource(R.drawable.select_block);
                a[x][y].isTop = true;

                //数组溢出
                try {

                    //已是飞机则不能被方向替换
                    if (!a[x + 3][y].isPlane && !a[x + 1][y - 2].isPlane && !a[x + 1][y + 2].isPlane && !a[x + 3][y + 1].isPlane && !a[x + 3][y - 1].isPlane && !a[x + 1][y + 1].isPlane && !a[x + 1][y - 1].isPlane) {
                        if (a[x][y + 2] == null) ;
                        if (a[x][y - 2] == null) ;
                        a[x + 3][y].setBackgroundResource(R.drawable.direction_b);
                        a[x + 3][y].isDirection = StaticClass.BOTTOM;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToastUtil.showText( a[x][y].getContext(), "无效位置");
                }
                try {

                    if (!a[x - 3][y].isPlane && !a[x - 3][y + 1].isPlane && !a[x - 3][y - 1].isPlane && !a[x - 1][y + 1].isPlane && !a[x - 1][y + 2].isPlane && !a[x - 1][y - 1].isPlane && !a[x - 1][y - 2].isPlane) {
                        if (a[x][y + 2] == null) ;
                        if (a[x][y - 2] == null) ;
                        a[x - 3][y].setBackgroundResource(R.drawable.direction_t);
                        a[x - 3][y].isDirection = StaticClass.TOP;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToastUtil.showText( a[x][y].getContext(), "无效位置");
                }
                try {

                    if (!a[x][y + 3].isPlane && !a[x + 1][y + 3].isPlane && !a[x - 1][y + 3].isPlane && !a[x + 1][y + 1].isPlane && !a[x + 2][y + 1].isPlane && !a[x - 1][y + 1].isPlane && !a[x - 2][y + 1].isPlane) {
                        if (a[x + 2][y] == null) ;
                        if (a[x - 2][y] == null) ;
                        a[x][y + 3].setBackgroundResource(R.drawable.direction_r);
                        a[x][y + 3].isDirection = StaticClass.RIGHTP;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToastUtil.showText( a[x][y].getContext(), "无效位置");
                }
                try {
                    if (!a[x][y - 3].isPlane && !a[x + 1][y - 3].isPlane && !a[x - 1][y - 3].isPlane && !a[x - 1][y - 1].isPlane && !a[x - 2][y - 1].isPlane && !a[x + 2][y - 1].isPlane && !a[x + 1][y - 1].isPlane) {
                        if (a[x + 2][y] == null) ;
                        if (a[x - 2][y] == null) ;
                        a[x][y - 3].setBackgroundResource(R.drawable.direction_l);
                        a[x][y - 3].isDirection = StaticClass.LEFT;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToastUtil.showText( a[x][y].getContext(), "无效位置");
                }
            }

        }
        return plane_l;
    }


    //生成随机坐标
    public static Location RandomLocation(){

        double temp_x = Math.random()*10;
        double temp_y = Math.random()*10;
        int x = (int) temp_x;
        int y = (int) temp_y;

        return new Location(x, y);
    }

    //生成随机方向
    public static int RandomLDirection(){

        double temp = Math.random()*4;
        int x = (int) temp;
        return x + 1;
    }


    public static ArrayList<Location> getRandomPlane(Location l, Block[][] a, int dre) {

        int x = l.x;
        int y = l.y;

        ArrayList<Location> plane_l = new ArrayList<>();

        switch (dre) {
            case StaticClass.NULL:
                break;
            case StaticClass.TOP:
                try {
                    if (!a[x + 3][y].isPlane && !a[x + 2][y].isPlane && !a[x + 1][y].isPlane && !a[x][y].isPlane && !a[x][y - 1].isPlane && !a[x][y + 1].isPlane && !a[x + 2][y + 1].isPlane && !a[x + 2][y + 2].isPlane && !a[x + 2][y - 1].isPlane && !a[x + 2][y - 2].isPlane) {

                        plane_l.add(new Location(x + 3, y));
                        plane_l.add(new Location(x + 2, y));
                        plane_l.add(new Location(x + 1, y));
                        plane_l.add(new Location(x, y));
                        plane_l.add(new Location(x, y - 1));
                        plane_l.add(new Location(x, y + 1));
                        plane_l.add(new Location(x + 2, y + 1));
                        plane_l.add(new Location(x + 2, y + 2));
                        plane_l.add(new Location(x + 2, y - 1));
                        plane_l.add(new Location(x + 2, y - 2));
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    L.i("溢出了...");
                }

                cleanMap(a);

                break;
            case StaticClass.LEFT:
                try {
                    if (!a[x][y + 3].isPlane && !a[x][y + 2].isPlane && !a[x][y + 1].isPlane && !a[x][y].isPlane && !a[x + 1][y].isPlane && !a[x - 1][y].isPlane && !a[x + 1][y + 2].isPlane && !a[x + 2][y + 2].isPlane && !a[x - 1][y + 2].isPlane && !a[x - 2][y + 2].isPlane) {
                        plane_l.add(new Location(x, y + 3));
                        plane_l.add(new Location(x, y + 2));
                        plane_l.add(new Location(x, y + 1));
                        plane_l.add(new Location(x, y));
                        plane_l.add(new Location(x - 1, y));
                        plane_l.add(new Location(x + 1, y));
                        plane_l.add(new Location(x + 1, y + 2));
                        plane_l.add(new Location(x + 2, y + 2));
                        plane_l.add(new Location(x - 1, y + 2));
                        plane_l.add(new Location(x - 2, y + 2));
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    L.i("溢出了...");
                }

                cleanMap(a);

                break;
            case StaticClass.RIGHTP:
                try {
                    if (!a[x][y - 3].isPlane && !a[x][y - 2].isPlane && !a[x][y - 1].isPlane && !a[x][y].isPlane && !a[x - 1][y].isPlane && !a[x + 1][y].isPlane && !a[x - 1][y - 2].isPlane && !a[x - 2][y - 2].isPlane && !a[x + 1][y - 2].isPlane && !a[x + 2][y - 2].isPlane) {
                        plane_l.add(new Location(x, y - 3));
                        plane_l.add(new Location(x, y - 2));
                        plane_l.add(new Location(x, y - 1));
                        plane_l.add(new Location(x, y));
                        plane_l.add(new Location(x - 1, y));
                        plane_l.add(new Location(x + 1, y));
                        plane_l.add(new Location(x - 1, y - 2));
                        plane_l.add(new Location(x - 2, y - 2));
                        plane_l.add(new Location(x + 1, y - 2));
                        plane_l.add(new Location(x + 2, y - 2));
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    L.i("溢出了...");
                }

                cleanMap(a);

                break;
            case StaticClass.BOTTOM:
                try {
                    if (!a[x - 3][y].isPlane && !a[x - 2][y].isPlane && !a[x - 1][y].isPlane && !a[x][y].isPlane && !a[x][y + 1].isPlane && !a[x][y - 1].isPlane && !a[x - 2][y + 1].isPlane && !a[x - 2][y + 2].isPlane && !a[x - 2][y - 1].isPlane && !a[x - 2][y - 2].isPlane) {
                        plane_l.add(new Location(x - 3, y));
                        plane_l.add(new Location(x - 2, y));
                        plane_l.add(new Location(x - 1, y));
                        plane_l.add(new Location(x, y));
                        plane_l.add(new Location(x, y - 1));
                        plane_l.add(new Location(x, y + 1));
                        plane_l.add(new Location(x - 2, y + 1));
                        plane_l.add(new Location(x - 2, y + 2));
                        plane_l.add(new Location(x - 2, y - 1));
                        plane_l.add(new Location(x - 2, y - 2));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    L.i("溢出了...");
                }

                cleanMap(a);

                break;
        }
        return plane_l;
    }

    //清理地图
    public static void cleanMap(Block[][] a){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++) {
                a[i][j].isDirection = StaticClass.NULL;
                if (!a[i][j].isPlane) {

                    a[i][j].isTop = false;
                    a[i][j].setBackgroundResource(R.drawable.block_bg);
                }
            }
        }
    }

    public static void setMyPlane(Set<Location> key, ArrayList<Location> plane_l, Block[][] matrix_s, HashMap<Location, ArrayList<Location>> my_plane_list){
        for(Location l: key){
//            plane_l为我的每个机头对应的机身坐标
            plane_l = my_plane_list.get(l);

            matrix_s[l.x][l.y].setBackgroundResource(R.drawable.select_block);
            matrix_s[l.x][l.y].isPlane = true;
            matrix_s[l.x][l.y].isTop = true;

            for (Location t : plane_l) {
                int x = t.x;
                int y = t.y;

                matrix_s[x][y].setBackgroundResource(R.drawable.select_block);
                matrix_s[x][y].isPlane = true;
            }

            if(plane_l.get(1).x == l.x-1){
                MapTools.getPlaneBody(new Location(plane_l.get(0).x-3, plane_l.get(0).y), matrix_s, StaticClass.TOP);
            }else if(plane_l.get(1).x == l.x+1){
                MapTools.getPlaneBody(new Location(plane_l.get(0).x+3, plane_l.get(0).y), matrix_s, StaticClass.BOTTOM);
            }else if(plane_l.get(1).y == l.y-1){
                MapTools.getPlaneBody(new Location(plane_l.get(0).x, plane_l.get(0).y-3), matrix_s, StaticClass.LEFT);
            }else if (plane_l.get(1).y == l.y+1){
                MapTools.getPlaneBody(new Location(plane_l.get(0).x, plane_l.get(0).y+3), matrix_s, StaticClass.RIGHTP);
            }

        }
        plane_l.clear();
    }
}
