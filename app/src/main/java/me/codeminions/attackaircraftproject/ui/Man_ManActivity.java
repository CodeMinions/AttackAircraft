package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.L;
import me.codeminions.attackaircraftproject.tool.Location;
import me.codeminions.attackaircraftproject.tool.MapTools;
import me.codeminions.attackaircraftproject.tool.SerMap;
import me.codeminions.attackaircraftproject.tool.UtilTools;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/8 0:01
 * 描述：TODO
 */

public class Man_ManActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {

    public boolean isInit = false;
    public boolean isOInit = false;

    int count;
    //记录击中数
    int my_boom_Count;
    //记录被击中数
    int be_Boom_Count;


    List<ArrayList<Location>> plane_list;
    HashMap<Location, ArrayList<Location>> my_plane_list;

    TextView wait;

    LinearLayout linear;
    LinearLayout linear_s;

    TextView text_id;
    TextView textBroad;
    StringBuilder inform;

    //用于数据传递的中间载体，用完记得clear()
    ArrayList<Location> plane_l;

    Block[][] matrix;
    Block[][] matrix_s;


    ArrayList<Block> Top_Set;

    String enemyId;

    public static MyHandler handler;

    Set<Location> key;

    public static void actionStart(Context context, SerMap map, String id){
        Intent intent = new Intent(context, Man_ManActivity.class);
        intent.putExtra("map", map);
        intent.putExtra("id", id);

        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_machine);

        init();

        MapTools.setMyPlane(key, plane_l, matrix_s, my_plane_list);

        //异步暴力线程，监听对方地图是否传到
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (BmobData != null) {
                        handler.sendEmptyMessage(1);
                        L.i("敌方地图加载完毕...");
                        break;
                    }
                }
            }
        }).start();
    }


    private void init(){

        //传入我的地图，想在linear_s中显示出来
        my_plane_list = ((SerMap) getIntent().getSerializableExtra("map")).map;
        enemyId = getIntent().getStringExtra("id");
        plane_list = new ArrayList<>();
        Top_Set = new ArrayList<>();

        handler = new MyHandler();

        //显示点击信息
        count = 0;
        my_boom_Count = 0;
        be_Boom_Count = 0;
        inform = new StringBuilder();
        matrix = new Block[10][10];
        matrix_s = new Block[10][10];
        isInit = true;
        //标识开始游戏
        isStartGame = true;

        text_id = (TextView) findViewById(R.id.link_id);
        text_id.setVisibility(View.VISIBLE);
        text_id.append(" " + enemyId);

        textBroad = (TextView) findViewById(R.id.textBroad);
        textBroad.setTextSize(13);
        textBroad.setTextColor(getResources().getColor(R.color.select));
        textBroad.setTypeface(Typeface.SERIF);

        linear = (LinearLayout) findViewById(R.id.map);
        linear_s = (LinearLayout) findViewById(R.id.small_map);

        initView(linear);
        initView(linear_s);

        /**
         * 初始化等待提示，且不能点击
         */
        wait = (TextView) findViewById(R.id.kaiguan);
        wait.setText("等待对方进入...");
        wait.setVisibility(View.VISIBLE);
        setButton(false);

        //将自己的地图显示到屏幕,key为所有我的机头的集合
        key = my_plane_list.keySet();
    }

    //加载原始地图
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


    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 1:
                    parseJson(BmobData);
                    setEnemyPlane();
                    isOInit = true;

                    wait.setVisibility(View.GONE);
                    setButton(true);
                    break;

                case 4:
                    Gson gson = new Gson();
                    Location l = gson.fromJson(BmobData, Location.class);
                    L.e("攻击了" + l.getLocation());
                    enemyAttach(l);

                    wait.setVisibility(View.GONE);
                    setButton(true);
                    break;
                case 5:
                    L.e("更新UI。。。");
                    break;
            }
        }
    }

    private void setButton(Boolean key){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){

                if (!matrix[i][j].isBoom) {
                    matrix[i][j].setEnabled(key);
                }
            }
        }
    }


    private void setEnemyPlane() {
        for(ArrayList<Location> p_l: plane_list){
//            l为我的每个机头对应的机身坐标
            matrix[p_l.get(0).x][p_l.get(0).y].isTop = true;

            Top_Set.add(matrix[p_l.get(0).x][p_l.get(0).y]);
            for(Location l :p_l){
                matrix[l.x][l.y].isPlane = true;
                // FIXME: 2018/10/9 不显示
//                matrix[l.x][l.y].setBackgroundResource(R.drawable.select_block);
            }
        }
    }


    /**
     * 解析后端传来的json数据
     * 可以考虑把这一步放到数据接收端
     */
    public void parseJson(String data){
        Gson gson = new Gson();

        List<ArrayList<Location>> list = gson.fromJson(data, new TypeToken<List<ArrayList<Location>>>() {
        }.getType());

        plane_list.addAll(list);
    }


    @Override
    public void onClick(View v) {
        Log.d("sa", "fuck");
        int x = ((Block) v).getLine();
        int y = ((Block) v).getList();

        //更新记录板
        if(count%10 == 0){
            inform = new StringBuilder();
        }
        count++;

        UtilTools.A a = UtilTools.beClick(v, inform, matrix);
        a.inform = inform;
        a.m = matrix;

        if(Top_Set.get(0).isBoom && Top_Set.get(1).isBoom && Top_Set.get(2).isBoom){
            setDialog("You Win...", String.valueOf(count) + "步击败了对手！");
        }
        textBroad.setText(inform);


        //给对方发送数据，我点击的坐标
        String json = new Gson().toJson(new Location(x, y));
        UtilTools.sendMessage(enemyId, "loc" + DeviceLinkActivity.simple_Id + "#" + json);

        wait.setVisibility(View.VISIBLE);
        wait.setText("请等候对方攻击...");
        setButton(false);
    }


    public void enemyAttach(Location l){

        matrix_s[l.x][l.y].isSelect = true;

        //添加不重复点击机制？？
        if(matrix_s[l.x][l.y].isPlane){
            matrix_s[l.x][l.y].setBackgroundResource(R.drawable.block_boom);
        }else{
            matrix_s[l.x][l.y].setBackgroundResource(R.drawable.block_false);
        }
        L.w("攻击...");
        if(matrix_s[l.x][l.y].isTop){
            be_Boom_Count++;
            if(be_Boom_Count == 3){
                setDialog("You Fail...", "你已被解决...");

            }
        }
    }

    public void setDialog(String inform, String mag){
        AlertDialog.Builder dialog = UtilTools.setDialog(this, inform, mag);
        dialog.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.removeActivity(Man_ManActivity.this);
                finish();
            }
        });
        dialog.show();
    }


    @Override
    public boolean onLongClick(View v) {
        if (((Block) v).isFalse) {
            v.setBackgroundResource(R.drawable.block_bg);
            ((Block) v).isFalse = false;
            return true;
        } else {
            v.setBackgroundResource(R.drawable.block_false);
            ((Block) v).isFalse = true;
            return true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        plane_l.clear();
        plane_list.clear();
        isStartGame = false;
        BmobData = null;
    }
}
