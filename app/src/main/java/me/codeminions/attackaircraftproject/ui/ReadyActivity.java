package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;

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
import me.codeminions.attackaircraftproject.tool.L;
import me.codeminions.attackaircraftproject.tool.Location;
import me.codeminions.attackaircraftproject.tool.SerMap;
import me.codeminions.attackaircraftproject.tool.StaticClass;
import me.codeminions.attackaircraftproject.tool.Tools;
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

    private int GAME_MODE;

    String deviceName;

    // FIXME: 2018/10/7 可以判断一下从哪一个界面跳过来的。
    public static void actionStart(Context context, @Nullable String info){
        Intent intent = new Intent(context, ReadyActivity.class);
        if(info != null) {
            intent.putExtra("device", info);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready);
        if(getIntent().getStringExtra("device") != null){
            deviceName = getIntent().getStringExtra("device");
            GAME_MODE = StaticClass.MAN;
        }else {
            GAME_MODE = StaticClass.MACHINE;
        }


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
            if(plane_list.size() != 3){
                AlertDialog.Builder dialog = Tools.setDialog(this, "请选择飞机（至少3架）...", null);
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                dialog.show();
//                Toast.makeText(v.getContext(), "sanjia", Toast.LENGTH_SHORT).show();
            } else{
                if(GAME_MODE == StaticClass.MAN){
                    sendMessage("map" + packMessage(plane_list));
                    Man_ManActivity.actionStart(v.getContext(), new SerMap(plane_list), deviceName);
                }else{
                    Man_MachineActivity.actionStart(v.getContext(), new SerMap(plane_list));
                }
                finish();
            }
        }else {

            int x = ((Block) v).getLine();
            int y = ((Block) v).getList();

            plane_l = Tools.getPlaneBody(new Location(x, y), a, ((Block) v).isDirection);
            if (plane_l.size() != 0)
                plane_list.put(plane_l.get(0), plane_l);

            switch (v.getId()) {
                case R.id.id_block:
                    break;
            }
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


    private void sendMessage(String msg){

        //TODO 替换成所需要推送的Android客户端installationId
        BmobPushManager bmobPushManager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
        String installationId = deviceName;
        query.addWhereEqualTo("installationId", installationId);
        bmobPushManager.setQuery(query);
        bmobPushManager.pushMessage(msg, new PushListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    L.e("推送成功！");
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


}
