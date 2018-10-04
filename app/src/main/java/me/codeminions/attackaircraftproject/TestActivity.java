package me.codeminions.attackaircraftproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import me.codeminions.attackaircraftproject.until.L;
import me.codeminions.attackaircraftproject.until.Location;
import me.codeminions.attackaircraftproject.until.StaticClass;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/3 14:02
 * 描述：TODO
 */



public class TestActivity extends AppCompatActivity implements View.OnClickListener ,View.OnLongClickListener{

    private int h, w;

    LinearLayout linear;

    HashMap<Location, ArrayList<Location>> plane_list;

    ArrayList<Location> plane_l;

    //地图矩阵
    private Block [][] a = new Block[10][10];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        plane_list = new HashMap<>();

        linear = (LinearLayout) findViewById(R.id.map);


        for(int i = 0; i < 10; i++){
            LinearLayout line = new LinearLayout(this);
            line.setId(i);
            linear.addView(line);

            for(int j = 0; j < 10; j++){
                LinearLayout list = new LinearLayout(this);
                line.addView(list);
                Block bu = new Block(this, i, j);
                bu.setBackgroundResource(R.drawable.block_bg);

                bu.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
                bu.setId(R.id.id_block);
                bu.setOnClickListener(this);
                bu.setOnLongClickListener(this);

                list.addView(bu);

                a[i][j] = bu;
            }
        }
    }

    @Override
    public void onClick(View v) {

        int x = ((Block) v).getLine();
        int y = ((Block) v).getList();

        switch (((Block) v).isDirection){
            case StaticClass.NULL:
                break;
            case StaticClass.TOP:
                a[x+3][y].setBackgroundResource(R.drawable.air_top_t);
                a[x+2][y].setBackgroundResource(R.drawable.air_body_t);
                a[x+1][y].setBackgroundResource(R.drawable.air_body_end_t);
                a[x][y].setBackgroundResource(R.drawable.air_end_t);
                a[x][y-1].setBackgroundResource(R.drawable.air_end2_t);
                a[x][y+1].setBackgroundResource(R.drawable.air_end1_t);
                a[x+2][y+1].setBackgroundResource(R.drawable.air_right2_t);
                a[x+2][y+2].setBackgroundResource(R.drawable.air_right1_t);
                a[x+2][y-1].setBackgroundResource(R.drawable.air_left2_t);
                a[x+2][y-2].setBackgroundResource(R.drawable.air_left1_t);

                a[x+3][y].isPlane = true;
                a[x+2][y].isPlane = true;
                a[x+1][y].isPlane = true;
                a[x][y].isPlane = true;
                a[x][y-1].isPlane = true;
                a[x][y+1].isPlane = true;
                a[x+2][y+1].isPlane = true;
                a[x+2][y+2].isPlane = true;
                a[x+2][y-1].isPlane = true;
                a[x+2][y-2].isPlane = true;

                plane_l = new ArrayList<>();
                plane_l.add(new Location(x+2, y));
                plane_l.add(new Location(x+1, y));
                plane_l.add(new Location(x, y));
                plane_l.add(new Location(x, y-1));
                plane_l.add(new Location(x, y+1));
                plane_l.add(new Location(x+2, y+1));
                plane_l.add(new Location(x+2, y+2));
                plane_l.add(new Location(x+2, y-1));
                plane_l.add(new Location(x+2, y-2));
                plane_list.put(new Location(x+3, y), plane_l);

                try {
                    a[x + 6][y].setBackgroundResource(R.drawable.block_bg);
                    a[x + 6][y].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                try {
                    a[x + 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y + 3].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}
                try {
                    a[x + 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y - 3].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}

                break;
            case StaticClass.LEFT:
                a[x][y+3].setBackgroundResource(R.drawable.air_top_l);
                a[x][y+2].setBackgroundResource(R.drawable.air_body_l);
                a[x][y+1].setBackgroundResource(R.drawable.air_body_end_l);
                a[x][y].setBackgroundResource(R.drawable.air_end_l);
                a[x+1][y].setBackgroundResource(R.drawable.air_end2_l);
                a[x-1][y].setBackgroundResource(R.drawable.air_end1_l);
                a[x+1][y+2].setBackgroundResource(R.drawable.air_right2_l);
                a[x+2][y+2].setBackgroundResource(R.drawable.air_right1_l);
                a[x-1][y+2].setBackgroundResource(R.drawable.air_left2_l);
                a[x-2][y+2].setBackgroundResource(R.drawable.air_left1_l);


                a[x][y+3].isPlane = true;
                a[x][y+2].isPlane = true;
                a[x][y+1].isPlane = true;
                a[x][y].isPlane = true;
                a[x+1][y].isPlane = true;
                a[x-1][y].isPlane = true;
                a[x+1][y+2].isPlane = true;
                a[x+2][y+2].isPlane = true;
                a[x-1][y+2].isPlane = true;
                a[x-2][y+2].isPlane = true;

                plane_l = new ArrayList<>();
                plane_l.add(new Location(x, y+2));
                plane_l.add(new Location(x, y+1));
                plane_l.add(new Location(x, y));
                plane_l.add(new Location(x-1, y));
                plane_l.add(new Location(x+1, y));
                plane_l.add(new Location(x+1, y+2));
                plane_l.add(new Location(x+2, y+2));
                plane_l.add(new Location(x-1, y+2));
                plane_l.add(new Location(x-2, y+2));
                plane_list.put(new Location(x, y+3), plane_l);

                try {
                    a[x + 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x + 3][y + 3].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}

                try {
                    a[x - 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                    a[x - 3][y + 3].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}

                try {
                    a[x][y + 6].setBackgroundResource(R.drawable.block_bg);
                    a[x][y + 6].isDirection = StaticClass.NULL;
                }catch (ArrayIndexOutOfBoundsException e){}

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;
                break;
            case StaticClass.RIGHTP:
                a[x][y-3].setBackgroundResource(R.drawable.air_top_r);
                a[x][y-2].setBackgroundResource(R.drawable.air_body_r);
                a[x][y-1].setBackgroundResource(R.drawable.air_body_end_r);
                a[x][y].setBackgroundResource(R.drawable.air_end_r);
                a[x-1][y].setBackgroundResource(R.drawable.air_end2_r);
                a[x+1][y].setBackgroundResource(R.drawable.air_end1_r);
                a[x-1][y-2].setBackgroundResource(R.drawable.air_right2_r);
                a[x-2][y-2].setBackgroundResource(R.drawable.air_right1_r);
                a[x+1][y-2].setBackgroundResource(R.drawable.air_left2_r);
                a[x+2][y-2].setBackgroundResource(R.drawable.air_left1_r);

                a[x][y-3].isPlane = true;
                a[x][y-2].isPlane = true;
                a[x][y-1].isPlane = true;
                a[x][y].isPlane = true;
                a[x-1][y].isPlane = true;
                a[x+1][y].isPlane = true;
                a[x-1][y-2].isPlane = true;
                a[x-2][y-2].isPlane = true;
                a[x+1][y-2].isPlane = true;
                a[x+2][y-2].isPlane = true;

                plane_l = new ArrayList<>();
                plane_l.add(new Location(x, y-2));
                plane_l.add(new Location(x, y-1));
                plane_l.add(new Location(x, y));
                plane_l.add(new Location(x-1, y));
                plane_l.add(new Location(x+1, y));
                plane_l.add(new Location(x-1, y-2));
                plane_l.add(new Location(x-2, y-2));
                plane_l.add(new Location(x+1, y-2));
                plane_l.add(new Location(x+2, y-2));
                plane_list.put(new Location(x, y-3), plane_l);

                try {
                    if(!a[x + 3][y - 3].isPlane) {
                        a[x + 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x + 3][y - 3].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if(!a[x - 3][y - 3].isPlane) {
                        a[x - 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y - 3].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;

                try {
                    if(!a[x][y - 6].isPlane) {
                        a[x][y - 6].setBackgroundResource(R.drawable.block_bg);
                        a[x][y - 6].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

                break;
            case StaticClass.BOTTOM:
                a[x-3][y].setBackgroundResource(R.drawable.air_top_b);
                a[x-2][y].setBackgroundResource(R.drawable.air_body_b);
                a[x-1][y].setBackgroundResource(R.drawable.air_body_end_b);
                a[x][y].setBackgroundResource(R.drawable.air_end_b);
                a[x][y+1].setBackgroundResource(R.drawable.air_end2_b);
                a[x][y-1].setBackgroundResource(R.drawable.air_end1_b);
                a[x-2][y+1].setBackgroundResource(R.drawable.air_right2_b);
                a[x-2][y+2].setBackgroundResource(R.drawable.air_right1_b);
                a[x-2][y-1].setBackgroundResource(R.drawable.air_left2_b);
                a[x-2][y-2].setBackgroundResource(R.drawable.air_left1_b);

                a[x-3][y].isPlane = true;
                a[x-2][y].isPlane = true;
                a[x-1][y].isPlane = true;
                a[x][y].isPlane = true;
                a[x][y+1].isPlane = true;
                a[x][y-1].isPlane = true;
                a[x-2][y+1].isPlane = true;
                a[x-2][y+2].isPlane = true;
                a[x-2][y-1].isPlane = true;
                a[x-2][y-2].isPlane = true;

                plane_l = new ArrayList<>();
                plane_l.add(new Location(x-2, y));
                plane_l.add(new Location(x-1, y));
                plane_l.add(new Location(x, y));
                plane_l.add(new Location(x, y-1));
                plane_l.add(new Location(x, y+1));
                plane_l.add(new Location(x-2, y+1));
                plane_l.add(new Location(x-2, y+2));
                plane_l.add(new Location(x-2, y-1));
                plane_l.add(new Location(x-2, y-2));
                plane_list.put(new Location(x-3, y), plane_l);

//                a[x][y].setBackgroundResource(R.drawable.block_bg);
                a[x][y].isDirection = StaticClass.NULL;
                try {
                    if(!a[x - 6][y].isPlane) {
                        a[x - 6][y].setBackgroundResource(R.drawable.block_bg);
                        a[x - 6][y].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if(!a[x - 3][y + 3].isPlane) {
                        a[x - 3][y + 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y + 3].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if(!a[x - 3][y - 3].isPlane) {
                        a[x - 3][y - 3].setBackgroundResource(R.drawable.block_bg);
                        a[x - 3][y - 3].isDirection = StaticClass.NULL;
                    }
                }catch (ArrayIndexOutOfBoundsException e){}

                break;
        }

        if(!a[x][y].isPlane)
            switch(v.getId()){
                case R.id.id_block:

                    L.i("(" + String.valueOf(((Block) v).getLine()) + "," +
                        String.valueOf(((Block) v).getList()) + ")");

                    if(((Block) v).isTop){
                        v.setBackgroundResource(R.drawable.block_bg);
                        ((Block) v).isTop = false;
                        try {
    //方向取消时飞机也不能被替换
                            if(!a[x + 3][y].isPlane) {
                                a[x + 3][y].setBackgroundResource(R.drawable.block_bg);
                                a[x + 3][y].isDirection = StaticClass.NULL;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){}
                        try {
                            if(!a[x-3][y].isPlane) {
                                a[x - 3][y].setBackgroundResource(R.drawable.block_bg);
                                a[x - 3][y].isDirection = StaticClass.NULL;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){}
                        try {
                            if(!a[x][y+3].isPlane) {
                                a[x][y + 3].setBackgroundResource(R.drawable.block_bg);
                                a[x][y + 3].isDirection = StaticClass.NULL;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){}
                        try {
                            if(!a[x][y - 3].isPlane) {
                                a[x][y - 3].setBackgroundResource(R.drawable.block_bg);
                                a[x][y - 3].isDirection = StaticClass.NULL;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){}

                    } else{
                        v.setBackgroundResource(R.drawable.select_block );
                        ((Block) v).isTop = true;

                        //数组溢出
                        try{

    //已是飞机则不能被方向替换
                            if(!a[x + 3][y].isPlane && !a[x+1][y-2].isPlane && !a[x+1][y+2].isPlane && !a[x+3][y+1].isPlane && !a[x+3][y-1].isPlane && !a[x+1][y+1].isPlane && !a[x+1][y-1].isPlane) {
                                if (a[x][y + 2] == null) ;
                                if (a[x][y - 2] == null) ;
                                a[x + 3][y].setBackgroundResource(R.drawable.direction_b);
                                a[x + 3][y].isDirection = StaticClass.BOTTOM;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(v.getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                        }
                        try{

                            if(!a[x - 3][y].isPlane && !a[x-3][y+1].isPlane && !a[x-3][y-1].isPlane && !a[x-2][y+1].isPlane && !a[x-2][y+2].isPlane && !a[x-2][y-1].isPlane && !a[x-2][y-2].isPlane) {
                                if (a[x][y + 2] == null) ;
                                if (a[x][y - 2] == null) ;
                                a[x - 3][y].setBackgroundResource(R.drawable.direction_t);
                                a[x - 3][y].isDirection = StaticClass.TOP;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(v.getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                        }
                        try{

                            if(!a[x][y + 3].isPlane && !a[x+1][y+3].isPlane && !a[x-1][y+3].isPlane && !a[x+1][y+1].isPlane && !a[x+2][y+1].isPlane && !a[x-1][y+1].isPlane && !a[x-2][y+1].isPlane) {
                                if (a[x + 2][y] == null) ;
                                if (a[x - 2][y] == null) ;
                                a[x][y + 3].setBackgroundResource(R.drawable.direction_r);
                                a[x][y + 3].isDirection = StaticClass.RIGHTP;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(v.getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                        }
                        try{
                            if(!a[x][y - 3].isPlane && !a[x+1][y-3].isPlane && !a[x-1][y-3].isPlane && !a[x-1][y-1].isPlane && !a[x-2][y-1].isPlane && !a[x+2][y-1].isPlane && !a[x+1][y-1].isPlane) {
                                if (a[x + 2][y] == null) ;
                                if (a[x - 2][y] == null) ;
                                a[x][y - 3].setBackgroundResource(R.drawable.direction_l);
                                a[x][y - 3].isDirection = StaticClass.LEFT;
                            }
                        }catch (ArrayIndexOutOfBoundsException e){
                            Toast.makeText(v.getContext(), "无效位置", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }
    }

    @Override
    public boolean onLongClick(View v) {
        if(((Block) v).isTop){
            int x = ((Block) v).getLine();
            int y = ((Block) v).getList();

            ((Block) v).isPlane = false;
            ((Block) v).isTop = false;
            ((Block) v).setBackgroundResource(R.drawable.block_bg);

//            plane_l.clear();
            Location l = new Location(x, y);
//            plane_l = plane_list.get(new Location(x, y));
            Set<Location> loc = plane_list.keySet();
            for(Location i:loc){
                if(i.equals(l)){
                    plane_l = plane_list.get(i);

                    break;
                }
            }

            for(Location i:plane_l){
                a[i.getX()][i.getY()].setBackgroundResource(R.drawable.block_bg);
                a[i.getX()][i.getY()].isPlane = false;
            }
            return true;
        }

//        return true;
//        Toast.makeText(v.getContext(), "长按", Toast.LENGTH_SHORT).show();
        return false;
    }
}
