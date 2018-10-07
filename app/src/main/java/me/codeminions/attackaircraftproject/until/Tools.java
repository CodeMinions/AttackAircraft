package me.codeminions.attackaircraftproject.until;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.view.Block;


/**
 * 创建时间：2018/10/4 15:28
 * 描述：用于系统自动生成飞机位置
 */
public class Tools {

    private static int count = 0;
    public static Location RandomLocation(){

        double temp_x = Math.random()*10;
        double temp_y = Math.random()*10;
        int x = (int) temp_x;
        int y = (int) temp_y;

        return new Location(x, y);
    }

    public static int RandomLDirection(){

        double temp = Math.random()*4;
        int x = (int) temp;
        return x + 1;
    }


    public static void main(String []args){
        int  i = 0;
        Location a;
        int b;
        while(i < 30){
            a = RandomLocation();
            b = RandomLDirection();
            System.out.println(a.x + "  " + a.y + "         " + b);

            i++;
        }
    }


    /**
     * 进来一个坐标和地图，返回一个数组，里面是一架飞机的坐标，首个为机头（即进来的的坐标）
     * */
    public static ArrayList<Location> getPlaneBody(Location l, Block[][] a, int dre) {
//    public static MyBundle getPlaneBody(Location l, Block[][] a, int dre) {

        count++;
        L.i(String.valueOf(count));

        int x = l.x;
        int y = l.y;

        ArrayList<Location> plane_l = new ArrayList<>();

//        double temp = Math.random() * 4;
//        int dre = (int) temp;

//        int dre = 3;

        switch (dre) {
            case StaticClass.NULL:
                break;
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
                    Toast.makeText(a[x][y].getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                }
                try {

                    if (!a[x - 3][y].isPlane && !a[x - 3][y + 1].isPlane && !a[x - 3][y - 1].isPlane && !a[x - 1][y + 1].isPlane && !a[x - 1][y + 2].isPlane && !a[x - 1][y - 1].isPlane && !a[x - 1][y - 2].isPlane) {
                        if (a[x][y + 2] == null) ;
                        if (a[x][y - 2] == null) ;
                        a[x - 3][y].setBackgroundResource(R.drawable.direction_t);
                        a[x - 3][y].isDirection = StaticClass.TOP;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(a[x][y].getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                }
                try {

                    if (!a[x][y + 3].isPlane && !a[x + 1][y + 3].isPlane && !a[x - 1][y + 3].isPlane && !a[x + 1][y + 1].isPlane && !a[x + 2][y + 1].isPlane && !a[x - 1][y + 1].isPlane && !a[x - 2][y + 1].isPlane) {
                        if (a[x + 2][y] == null) ;
                        if (a[x - 2][y] == null) ;
                        a[x][y + 3].setBackgroundResource(R.drawable.direction_r);
                        a[x][y + 3].isDirection = StaticClass.RIGHTP;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(a[x][y].getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                }
                try {
                    if (!a[x][y - 3].isPlane && !a[x + 1][y - 3].isPlane && !a[x - 1][y - 3].isPlane && !a[x - 1][y - 1].isPlane && !a[x - 2][y - 1].isPlane && !a[x + 2][y - 1].isPlane && !a[x + 1][y - 1].isPlane) {
                        if (a[x + 2][y] == null) ;
                        if (a[x - 2][y] == null) ;
                        a[x][y - 3].setBackgroundResource(R.drawable.direction_l);
                        a[x][y - 3].isDirection = StaticClass.LEFT;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(a[x][y].getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                }
            }

        }
        return plane_l;
//        return new MyBundle(plane_l, a);
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
                    if (a[x][y + 3].isPlane && a[x][y + 2].isPlane && a[x][y + 1].isPlane && a[x][y].isPlane && a[x + 1][y].isPlane && a[x - 1][y].isPlane && a[x + 1][y + 2].isPlane && a[x + 2][y + 2].isPlane && a[x - 1][y + 2].isPlane && a[x - 2][y + 2].isPlane) {
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
                    if (a[x][y - 3].isPlane && a[x][y - 2].isPlane && a[x][y - 1].isPlane && a[x][y].isPlane && a[x - 1][y].isPlane && a[x + 1][y].isPlane && a[x - 1][y - 2].isPlane && a[x - 2][y - 2].isPlane && a[x + 1][y - 2].isPlane && a[x + 2][y - 2].isPlane) {
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
                    if (a[x - 3][y].isPlane && a[x - 2][y].isPlane && a[x - 1][y].isPlane && a[x][y].isPlane && a[x][y + 1].isPlane && a[x][y - 1].isPlane && a[x - 2][y + 1].isPlane && a[x - 2][y + 2].isPlane && a[x - 2][y - 1].isPlane && a[x - 2][y - 2].isPlane) {
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

    public static AlertDialog.Builder setDialog(Context context, String inform, String msg){
//        LayoutInflater inflater = LayoutInflater.from(Man_MachineActivity.this);
//        View view_custom = inflater.inflate(R.layout.layout_success, null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(inform);
        dialog.setCancelable(false);
        if(msg != null){
            dialog.setMessage(msg);
        }
        return dialog;
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

}
