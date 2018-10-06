package me.codeminions.attackaircraftproject.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.until.L;
import me.codeminions.attackaircraftproject.until.Location;
import me.codeminions.attackaircraftproject.until.SerMap;
import me.codeminions.attackaircraftproject.until.StaticClass;
import me.codeminions.attackaircraftproject.until.Tools;
import me.codeminions.attackaircraftproject.view.Block;


/**
 * 创建时间：2018/10/3 14:02
 * 描述：准备游戏，布置飞机摆放
 */
public class ReadyActivity extends BaseActivity implements View.OnClickListener ,View.OnLongClickListener{

    LinearLayout linear;

    HashMap<Location, ArrayList<Location>> plane_list;

    ArrayList<Location> plane_l;

    //地图矩阵
    private Block[][] a = new Block[10][10];

    public static void actionStart(Context context){
        context.startActivity(new Intent(context, ReadyActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);

        findViewById(R.id.id_start).setOnClickListener(this);
        plane_list = new HashMap<>();

        linear = (LinearLayout) findViewById(R.id.map);

        initView();

    }

    /**
     * 初始化矩阵地图
    **/
    // FIXME: 2018/10/4 由于要通过迭代给每个方块赋值，所以无法将这段代码抽象出来复用。。
    private void initView(){
        for(int i = 0; i < 10; i++){
            LinearLayout line = new LinearLayout(this);
            line.setId(i);
            linear.addView(line);

            for(int j = 0; j < 10; j++){
                Block bu = new Block(this, i, j);
                bu.setBackgroundResource(R.drawable.block_bg);

                bu.setLayoutParams(new LinearLayout.LayoutParams(90, 90));
                bu.setId(R.id.id_block);
                bu.setOnClickListener(this);
                bu.setOnLongClickListener(this);

                line.addView(bu);

                a[i][j] = bu;
            }
        }
    }


    @Override
    public void onClick(View v) {

        /**
         *  为消除非Block按钮对界面影响
         **/
        if(v.getId() == R.id.id_start) {
//            GamingActivity.actionStart(v.getContext(), new SerMap(plane_list));
            Man_MachineActivity.actionStart(v.getContext(), new SerMap(plane_list));

//            GamingActivity.actionStart(v.getContext(), new SerMap(plane_list, a));
//            GamingActivity.actionStart(v.getContext(), new SerMap(plane_list), a);
        }

        int x = ((Block) v).getLine();
        int y = ((Block) v).getList();

        plane_l = Tools.getPlaneBody(new Location(x, y), a, ((Block) v).isDirection);
        if(plane_l.size() != 0)
            plane_list.put(plane_l.get(0), plane_l);

        switch (v.getId()) {
            case R.id.id_block:
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

            Set<Location> loc = plane_list.keySet();

            //解决对象key的比较问题
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

            for(Location i:loc){
                if(i.equals(l)){
                    plane_l = plane_list.remove(i);
                    break;
                }
            }
            return true;
        }

        return false;
    }

}
