package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;
import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.application.ActivityCollector;
import me.codeminions.attackaircraftproject.until.L;
import me.codeminions.attackaircraftproject.until.Location;
import me.codeminions.attackaircraftproject.until.SerMap;
import me.codeminions.attackaircraftproject.until.Tools;
import me.codeminions.attackaircraftproject.view.Block;

/**
 * 创建时间：2018/10/8 0:01
 * 描述：TODO
 */

public class Man_ManActivity extends BaseActivity implements View.OnClickListener, View.OnLongClickListener {
        boolean isFirst = true;

        int count;
        //记录击中数
        int my_boom_Count;
        //记录被击中数
        int be_Boom_Count;


        List<ArrayList<Location>> plane_list;
        HashMap<Location, ArrayList<Location>> my_plane_list;

        LinearLayout linear;
        LinearLayout linear_s;

        TextView textBroad;
        StringBuilder inform;

        //用于数据传递的中间载体，用完记得clear()
        ArrayList<Location> plane_l;

        Block[][] matrix;
        Block[][] matrix_s;

        BmobIMUserInfo UserId;

        private BmobIMConversation mBmobIMConversation;
        public static String BmobData;

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
            UserId.setUserId(getIntent().getStringExtra("id"));
            plane_list = new ArrayList<>();

            sendMessage(packMessage(my_plane_list));

            init();

            //linear_s后初始化，以上操作执行后，matrix数组中保存的是linear_s中的对象

            //将自己的地图显示到屏幕,key为所有我的机头的集合
            Set<Location> key = my_plane_list.keySet();

            setMyPlane(key);
            setEnemyPlane();
        }

        private String packMessage(HashMap<Location, ArrayList<Location>> my_plane_list){
            Set<Location> key = my_plane_list.keySet();
            List<ArrayList<Location>> l = new ArrayList<>();

            for(Location k: key){
                plane_l = my_plane_list.get(k);
                l.add(plane_l);
            }
            return new Gson().toJson(l);
        }

        private void sendMessage(final String json){

            BmobIM.getInstance().startPrivateConversation(UserId, new ConversationListener() {
                @Override
                public void done(BmobIMConversation c, BmobException e) {
                    if (e == null) {
                        mBmobIMConversation = BmobIMConversation.obtain(BmobIMClient.getInstance(), c);
                        BmobIMTextMessage msg = new BmobIMTextMessage();
                        msg.setContent(json);
                        mBmobIMConversation.sendMessage(msg, new MessageSendListener() {
                            @Override
                            public void done(BmobIMMessage bmobIMMessage, BmobException e) {
                                //标记已发送
                                Toast.makeText(Man_ManActivity.this, "本地信息已发送...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }

        private void setEnemyPlane() {
            for(ArrayList<Location> p_l: plane_list){
//            l为我的每个机头对应的机身坐标
                matrix_s[p_l.get(0).x][p_l.get(0).y].isTop = true;
                for(Location l :p_l){
                    matrix_s[l.x][l.y].isPlane = true;
                    //不显示
//                    matrix_s[l.x][l.y].setBackgroundResource(R.drawable.select_block);
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
        List<ArrayList<Location>> list = gson.fromJson(data, new TypeToken<List<Location>>(){}.getType());

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
                Toast.makeText(v.getContext(), "(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")", Toast.LENGTH_SHORT).show();

                inform.append("(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")" );

                if(matrix[x][y].isPlane){
                    if(matrix[((Block) v).getLine()][((Block) v).getList()].isTop) {
                        v.setBackgroundResource(R.drawable.block_boom);
                        my_boom_Count++;
                        if(my_boom_Count == 3){
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
            sendMessage(json);

            Gson gson = new Gson();
            Location l = gson.fromJson(be_Boom_loc, Location.class);
            enemyAttach(l);
        }


        public void enemyAttach(Location l){

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


}
