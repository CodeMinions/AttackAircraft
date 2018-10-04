package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.until.Location;
import me.codeminions.attackaircraftproject.until.SerMap;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/4 12:00
 * 描述：TODO
 */
public class GamingActivity extends BaseActivity {

    SerMap plane_list;

    LinearLayout linear;

    ArrayList<Location> plane_l;

    //地图矩阵
    private Block[][] matrix = new Block[10][10];

    public static void actionStart(Context context, SerMap map){
        Intent intent = new Intent(context, GamingActivity.class);
        intent.putExtra("map", map);

        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming);

        plane_list = (SerMap) getIntent().getSerializableExtra("map");
        Set<Location> key = plane_list.map.keySet();

        linear = (LinearLayout) findViewById(R.id.map);


        for(int i = 0; i < 10; i++){
            LinearLayout line = new LinearLayout(this);
            line.setId(i);
            linear.addView(line);

            for(int j = 0; j < 10; j++){
                Block bu = new Block(this, i, j);
                bu.setBackgroundResource(R.drawable.block_bg);

                bu.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
                bu.setId(R.id.id_block);

                line.addView(bu);

                matrix[i][j] = bu;
            }
        }

        for(Location l: key){
            plane_l = plane_list.map.get(l);

            matrix[l.x][l.y].setBackgroundResource(R.drawable.select_block);
            matrix[l.x][l.y].isPlane = true;
            matrix[l.x][l.y].isTop = true;

            for(Location t: plane_l) {

                int x = t.x;
                int y = t.y;

                matrix[x][y].setBackgroundResource(R.drawable.select_block);
                matrix[x][y].isPlane = true;
            }
        }

    }
}
