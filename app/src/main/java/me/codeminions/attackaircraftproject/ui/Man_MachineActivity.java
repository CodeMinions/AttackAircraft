package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.L;
import me.codeminions.attackaircraftproject.tool.Location;
import me.codeminions.attackaircraftproject.tool.MapTools;
import me.codeminions.attackaircraftproject.tool.SerMap;
import me.codeminions.attackaircraftproject.tool.SoundPoolUtil;
import me.codeminions.attackaircraftproject.tool.UtilTools;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/4 20:49
 * 描述：接受准备界面传来的数据，开始对战
 */
public class Man_MachineActivity extends ClickActivity implements View.OnClickListener, View.OnLongClickListener {

    int count;
    //记录击中数
    int boom_Count;
    //记录被击中数
    int be_Boom_Count;

    HashMap<Location, ArrayList<Location>> plane_list;
    HashMap<Location, ArrayList<Location>> my_plane_list;

    LinearLayout linear;
    LinearLayout linear_s;

    TextView textBroad;

    StringBuilder inform;

    ArrayList<Location> plane_l;

    Block[][] matrix;
    Block[][] matrix_s;

    ArrayList<Block> Top_Set;

    public static void actionStart(Context context, SerMap map){
        Intent intent = new Intent(context, Man_MachineActivity.class);
        intent.putExtra("map", map);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_machine);


        //传入我的地图，想在linear_s中显示出来
        my_plane_list = ((SerMap) getIntent().getSerializableExtra("map")).map;
        plane_list = new HashMap<>();
        Top_Set = new ArrayList<>();
        init();

        //linear_s后初始化，以上操作执行后，matrix数组中保存的是linear_s中的对象

        //将自己的地图显示到屏幕,key为所有我的机头的集合
        Set<Location> key = my_plane_list.keySet();

        MapTools.setMyPlane(key, plane_l, matrix_s, my_plane_list);
        setRandomPlane();
    }

    private void setRandomPlane() {
        //循环直到生成三架位置不同的飞机
        while(true) {
            //生成一个随机位置与随机方向，由此生成一架飞机的全身坐标
            Location location = MapTools.RandomLocation();
            plane_l = MapTools.getRandomPlane(location, matrix, MapTools.RandomLDirection());
            //如果出来一架飞机，放入plane_list中
            if(plane_l.size() != 0) {
                plane_list.put(plane_l.get(0), plane_l);
                matrix[plane_l.get(0).x][plane_l.get(0).y].isTop = true;

                Top_Set.add(matrix[plane_l.get(0).x][plane_l.get(0).y]);

                for (Location t : plane_l) {

                    matrix[t.x][t.y].isPlane = true;
                    //不显示
//                    matrix[t.x][t.y].setBackgroundResource(R.drawable.select_block);
                }
            }

            if(plane_list.size() == 3)
                break;
        }
    }

    private void init(){
        //显示点击信息
        count = 0;
        boom_Count = 0;
        be_Boom_Count = 0;
        inform = new StringBuilder();
        matrix = new Block[10][10];
        matrix_s = new Block[10][10];

        textBroad = (TextView) findViewById(R.id.textBroad);

        textBroad.setTextSize(13);
        textBroad.setTextColor(getResources().getColor(R.color.select));
        textBroad.setTypeface(Typeface.SERIF);
        textBroad.setText("AAAAAAAAAA");

        linear = (LinearLayout) findViewById(R.id.map);
        linear_s = (LinearLayout) findViewById(R.id.small_map);

        initView(linear);
        initView(linear_s);
    }

    private void initView(LinearLayout linear){
        for(int i = 0; i < 10; i++){
            LinearLayout line = new LinearLayout(this);
            line.setId(i);
            linear.addView(line);

            for(int j = 0; j < 10; j++){
                Block bu = new Block(this, i, j);
                bu.setBackgroundResource(R.drawable.block_bg);
                if(linear.getId() == R.id.small_map) {
                    bu.setLayoutParams(new LinearLayout.LayoutParams(52, 52));
                    matrix_s[i][j] = bu;
//                    matrix_s[i][j].isPlane = true;
                }else if(linear.getId() == R.id.map){
                    bu.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
                    bu.setId(R.id.id_block);
                    bu.setOnClickListener(this);
                    bu.setOnLongClickListener(this);

                    matrix[i][j] = bu;
                }
                line.addView(bu);
            }
        }
    }


    @Override
    public void click() {
        setMusicType(MusicType.FIRST);
    }

    @Override
    public void onClick(View v) {
        SoundPoolUtil.getInstance(Man_MachineActivity.this).play();
        L.i("fuck");
        if(count%10 == 0){
                inform = new StringBuilder();
            }
            count++;

            UtilTools.A a = UtilTools.beClick(v, inform, matrix);
            a.inform = inform;
            a.m = matrix;
            textBroad.setText(inform);

            if(Top_Set.get(0).isBoom && Top_Set.get(1).isBoom && Top_Set.get(2).isBoom){
                setDialog("You Win...", String.valueOf(count) + "步击败了对手！");
            }

            enemyAttach();
    }


    public void enemyAttach(){
        Location l;
        while (true){
            l = MapTools.RandomLocation();
            if(!matrix_s[l.x][l.y].isSelect)
                break;
        }
        matrix_s[l.x][l.y].isSelect = true;

        //添加不重复点击机制？？
        if(matrix_s[l.x][l.y].isPlane){
            matrix_s[l.x][l.y].setBackgroundResource(R.drawable.block_boom);
        }else{
            matrix_s[l.x][l.y].setBackgroundResource(R.drawable.block_false);
        }
        if(matrix_s[l.x][l.y].isTop){
            be_Boom_Count++;
            if(be_Boom_Count == 3){
                setDialog("You Fail...", "跟机器人打都能输？？？");

            }
        }
    }
    public void setDialog(String inform, String mag){
        AlertDialog.Builder dialog = UtilTools.setDialog(this, inform, mag);
        dialog.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.removeActivity(Man_MachineActivity.this);
            }
        });
        dialog.show();
    }


    @Override
    public boolean onLongClick(View v) {
        if(((Block) v).isFalse){
            v.setBackgroundResource(R.drawable.block_bg);
            ((Block) v).isFalse = false;
            return true;
        }else{
            v.setBackgroundResource(R.drawable.block_false);
            ((Block) v).isFalse = true;
            return true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        plane_l.clear();
        plane_list.clear();
    }
}
