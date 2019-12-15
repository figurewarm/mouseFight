package com.example.mousefight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class login2 extends Activity {

    private Boolean judge = false;
    private Intent intent;
    final String GET = "http://10.0.2.2:8080/judge?names=";
    final String POST = "http://10.0.2.2:8080/add/?names=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button reisterButton = findViewById(R.id.register);
        intent = new Intent(login2.this, MainActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameEditText.getText().toString();
                Log.e("logssss", name);
                judge(GET + name);
                if (judge)
                    Toast.makeText(login2.this, "帐号或密码错误！请注册", Toast.LENGTH_SHORT).show();

            }
        });
        reisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String para = name + ":" + password;
                if (judge) {
                    register(POST + para);
                    Toast.makeText(login2.this, "注册成功", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    /**
     * 注册功能。异步发送post请求
     * */
    private void register(String Info) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Info)
                .post(RequestBody.create(null, ""))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("logssss", "onFailure");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("logssss", "onResponse: " + response.body().string());
            }

        });
    }

    /**
     * 登录功能。同步发送get请求判断是否是用户
     * */
    private String judge(String Info) {
        final String[] res = new String[1];
        res[0] = "fail";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(Info)
                .get()
                .build();

        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
//                    String version = response.body().string();
//                    myHandler.sendMessage(UI_OPRATION);
                    Log.d("logssss", res[0] = response.body().string());
                    if (res[0].equals("ok"))
                        startActivity(intent);
                    else {
                        judge = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        final Call call = okHttpClient[0].newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                startActivity(intent);
//                Log.v("logssss", res[0] = response.body().string());
//            }
//
//        });
        Log.e("logssss", res[0]);
        return res[0];
    }
}
