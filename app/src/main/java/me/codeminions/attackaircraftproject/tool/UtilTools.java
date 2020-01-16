package me.codeminions.attackaircraftproject.tool;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.PushListener;
import me.codeminions.attackaircraftproject.R;
import me.codeminions.attackaircraftproject.view.Block;


/**
 * 创建时间：2018/10/4 15:28
 * 描述：用于系统自动生成飞机位置
 */
public class UtilTools {

    public static AlertDialog.Builder setDialog(Context context, String inform, String msg){

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(inform);
        dialog.setCancelable(false);
        if(msg != null){
            dialog.setMessage(msg);
        }
        return dialog;
    }

    public static class A {
        public StringBuilder inform;
        public Block[][] m;
    }
    public static A beClick(View v, StringBuilder inform, Block[][] matrix){
        int x = ((Block) v).getLine();
        int y = ((Block) v).getList();

        if(!((Block) v).isSelect){

            ((Block) v).isSelect = true;
            ToastUtil.showText(v.getContext(), "(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")");

            inform.append("(" + String.valueOf(((Block) v).getLine()) + "," + String.valueOf(((Block) v).getList()) + ")" );

            if(matrix[x][y].isPlane){
                ((Block) v).setBackgroundResource(R.drawable.ye_p);
                inform.append("true" + '\n');
                if(matrix[x][y].isTop) {
//                    v.setBackgroundResource(R.drawable.block_boom);     //红色
                    v.setBackgroundResource(R.drawable.boom);     //红色
                    matrix[x][y].setEnabled(false);
                    L.i("不能点...");

                    matrix[x][y].isBoom = true;
                }
            }else{
                inform.append("false" +  '\n');
                ((Block) v).setBackgroundResource(R.drawable.no_p);
            }
        }else{
            ((Block) v).setBackgroundResource(R.drawable.block_bg);
            ((Block) v).isSelect = false;
        }

        A a = new A();
        a.inform = inform;
        a.m = matrix;
        return a;
    }


    //该链接方式为短暂连接，即可在没有id（null）的情况下维持一段时间
    public static void sendMessage(String deviceName, String msg){

        //TODO 替换成所需要推送的Android客户端installationId
        BmobPushManager bmobPushManager = new BmobPushManager();
        BmobQuery<BmobInstallation> query = BmobInstallation.getQuery();
//        String installationId = deviceName;
        query.addWhereEqualTo("installationId", deviceName);
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


    //设置字体，便于使用在各个布局中抽取出来
    public static void setFont(Context context, TextView textView, String font){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font + ".ttf");
        textView.setTypeface(typeface);
    }
}
