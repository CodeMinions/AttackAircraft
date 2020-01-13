package me.codeminions.attackaircraftproject.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.tool.L;
import me.codeminions.attackaircraftproject.tool.Location;
import me.codeminions.attackaircraftproject.tool.MyPushMessageReceiver;
import me.codeminions.attackaircraftproject.tool.SerMap;
import me.codeminions.attackaircraftproject.tool.ToastUtil;
import me.codeminions.attackaircraftproject.tool.Tools;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/8 0:01
 * 描述：TODO
 */

public class Man_ManActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {
    boolean isFirst = true;
    public static boolean isInit = false;

    int count;
    //记录击中数
    int my_boom_Count;
    //记录被击中数
    int be_Boom_Count;


    List<ArrayList<Location>> plane_list;
    HashMap<Location, ArrayList<Location>> my_plane_list;

    TextView kaiguan;

    LinearLayout linear;
    LinearLayout linear_s;

    TextView textBroad;
    StringBuilder inform;

    //用于数据传递的中间载体，用完记得clear()
    ArrayList<Location> plane_l;

    Block[][] matrix;
    Block[][] matrix_s;


    ArrayList<Block> Top_Set;

    String enemyId;

    //保存Bmob的值用于判断Bmob更新
    String old_BmobData;


    public static MyHandler handler;


// FIXME: 2018/10/8 设置更新标记
    public static String be_Boom_loc;

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


        //传入我的地图，想在linear_s中显示出来
        my_plane_list = ((SerMap) getIntent().getSerializableExtra("map")).map;
        enemyId = getIntent().getStringExtra("device");
        plane_list = new ArrayList<>();
        Top_Set = new ArrayList<>();

//        L.i("刚进来的data  " + BmobData);
//        old_BmobData = BmobData;

//        sendMessage(packMessage(my_plane_list));

        handler = new MyHandler();
        init();
        isInit = true;
        //linear_s后初始化，以上操作执行后，matrix数组中保存的是linear_s中的对象

        //将自己的地图显示到屏幕,key为所有我的机头的集合
        Set<Location> key = my_plane_list.keySet();

        setMyPlane(key);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (BmobData != null) {
                        handler.sendEmptyMessage(1);
//                        old_BmobData = BmobData;

                        L.i("地图加载完毕...");
//                        t.start();
                        break;
                    }
                }

            }
        }).start();

    }


    //死循环被kill
//    Thread t = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            L.w("线程启动...");
//            L.i("newdata " + BmobData);
//            L.i("olddata " + old_BmobData);
//            while (true) {
////                 if (!isFirst) {
//                    if (!BmobData.equals(old_BmobData)) {
//                        L.w("Bmob已更新...");
//                        handler.sendEmptyMessage(4);
//                        break;
////                    }
//                }
//            }
//            L.w("线程关闭...");
//        }
//    });


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if(!isFirst) {
//                     while (true){
//
//                        //Bmob更新
//                        if (!BmobData.equals(old_BmobData)) {

//                            //发送null表示收到
//                            if (BmobData == "ok") {
//                                //在这里取消所有按钮点击

//                                handler.sendEmptyMessage(2);
//                            } else {
//                                handler.sendEmptyMessage(3);
//
//                            }

//                        }
//                    }
//                }
//            }
//        }).start();



    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case 1:
                    parseJson(BmobData);
                    setEnemyPlane();

                    break;

                case 4:
                    Gson gson = new Gson();
                    L.i("newdata " + BmobData);
                    L.i("olddata " + old_BmobData);
                    Location l = gson.fromJson(BmobData, Location.class);
                    L.e("攻击了" + l.getLocation());
                    enemyAttach(l);

                    kaiguan.setVisibility(View.GONE);
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
                if(!matrix[i][j].isBoom) {
                    matrix[i][j].setEnabled(key);
                }
            }
        }
    }


//    @SuppressLint("HandlerLeak")
//    public Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.what){
//                case 1:
//                    parseJson(BmobData);
//                    setEnemyPlane();
//
//                    break;
//
//                case 4:
//                    Gson gson = new Gson();
//                    L.i("newdata " + BmobData);
//                    L.i("olddata " + old_BmobData);
//                    Location l = gson.fromJson(BmobData, Location.class);
//                    enemyAttach(l);
//                    old_BmobData = BmobData;
//                    t.start();
//                    break;

//                case 2:
//                    kaiguan.setVisibility(View.VISIBLE);
//                    break;
//                case 3:
//                    Gson gson = new Gson();
//                    kaiguan.setVisibility(View.GONE);
//                    Location l = gson.fromJson(BmobData, Location.class);
//                    enemyAttach(l);
//                    old_BmobData = BmobData;
//                    sendPMessage("ok");
//                    break;
//            }
//        }
//    };

    private void sendPMessage(final String msg){

        BmobPushManager bmobPushManager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        String installationId = enemyId;
        query.addWhereEqualTo("installationId", installationId);
        bmobPushManager.setQuery(query);
        bmobPushManager.pushMessage(msg, new PushListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    L.e("推送成功！" + msg);
                    L.i("isFirst" + String.valueOf(isFirst));
                } else {
                    L.e("异常：" + e.getMessage());
                }
            }
        });
    }



    private String packMessage(HashMap<Location, ArrayList<Location>> my_plane_list){
        Set<Location> key = my_plane_list.keySet();
        List<ArrayList<Location>> l = new ArrayList<>();

        for(Location k: key){
            plane_l = my_plane_list.get(k);
            l.add(plane_l);
        }
        String data =  new Gson().toJson(l);
        return data;
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



    private void setMyPlane(Set<Location> key){
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
        }
        plane_l.clear();
    }

    private void init(){
        //显示点击信息
        count = 0;
        my_boom_Count = 0;
        be_Boom_Count = 0;
        inform = new StringBuilder();
        matrix = new Block[10][10];
        matrix_s = new Block[10][10];

        textBroad = (TextView) findViewById(R.id.textBroad);
        kaiguan = (TextView) findViewById(R.id.kaiguan);

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
        //更新记录板
        if(count == 10){
            count = 0;
            inform = new StringBuilder();
        }
        count++;
        textBroad.setTextSize(13);
        textBroad.setTextColor(getResources().getColor(R.color.select));
        textBroad.setTypeface(Typeface.SERIF);

        int x = ((Block) v).getLine();
        int y = ((Block) v).getList();
        if(!((Block) v).isSelect){
            ((Block) v).setBackgroundResource(R.drawable.select_block);
            ((Block) v).isSelect = true;
            ToastUtil.showText(v.getContext(), "(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")");

            inform.append("(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")" );

            if(matrix[x][y].isPlane){
                if(matrix[((Block) v).getLine()][((Block) v).getList()].isTop) {
                    v.setBackgroundResource(R.drawable.block_boom);
                    v.setEnabled(false);
                    L.i("不能点...");

                    matrix[((Block) v).getLine()][((Block) v).getList()].isBoom = true;
                    if(Top_Set.get(0).isBoom && Top_Set.get(1).isBoom && Top_Set.get(2).isBoom){
                        setDialog("You Win...", String.valueOf(count) + "步击败了对手！");
                    }

                }
                inform.append("true" + '\n');
            }else{
                inform.append("false" +  '\n');
            }
        }else{
            ((Block) v).setBackgroundResource(R.drawable.block_bg);
            ((Block) v).isSelect = false;
        }
        textBroad.setText(inform);


        //给对方发送数据，我点击的坐标
        String json = new Gson().toJson(new Location(x, y));
        sendPMessage("loc" + DeviceLinkActivity.simple_Id + "#" + json);

        kaiguan.setVisibility(View.VISIBLE);
        setButton(false);

        isFirst = false;
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
        AlertDialog.Builder dialog = Tools.setDialog(this, inform, mag);
        dialog.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.removeActivity(Man_ManActivity.this);
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

}
