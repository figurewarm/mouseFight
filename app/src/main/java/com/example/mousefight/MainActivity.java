package com.example.mousefight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {


    private Button startGame;
    private Button continueGame;
    private Button setting;
    private Button explain;
    private Button exit;


    public final Context TAG = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView(); //初始化控件
        initListener(); //初始化监听器
    }

    private void initListener() {
        BtnListener listener = new BtnListener();
        startGame.setOnClickListener(listener);
        continueGame.setOnClickListener(listener);
        setting.setOnClickListener(listener);
        explain.setOnClickListener(listener);
        exit.setOnClickListener(listener);
    }

    private void initView() {

        startGame = findViewById(R.id.bt_start);
        continueGame = findViewById(R.id.bt_continue);
        setting = findViewById(R.id.bt_setting);
        explain = findViewById(R.id.bt_explain);
        exit = findViewById(R.id.bt_exit);
    }


    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_start:
                case R.id.bt_continue:
                    Intent intent1 = new Intent(TAG, GameActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.bt_setting:
                    Toast.makeText(TAG, "没什么需要设置的，快去游戏吧", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.bt_explain:
                    Intent intent4 = new Intent(TAG, ExplainActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.bt_exit:
                    finish();
                    Intent intent = new Intent(MainActivity.this, MyMusicService.class);
                    stopService(intent);
                    break;
            }
        }
    }
}
