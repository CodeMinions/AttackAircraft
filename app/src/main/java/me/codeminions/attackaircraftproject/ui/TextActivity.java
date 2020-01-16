package me.codeminions.attackaircraftproject.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.codeminions.attackaircraftproject.R;

public class TextActivity extends AppCompatActivity {

    public static void actionStart(Context context){
        context.startActivity(new Intent(context, TextActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
    }
}
