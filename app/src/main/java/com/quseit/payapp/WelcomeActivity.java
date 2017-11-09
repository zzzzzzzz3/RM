package com.quseit.payapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bussiness.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 文 件 名: WelcomeActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 20:27
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        },1000);
    }
}
