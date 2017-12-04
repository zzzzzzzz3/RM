package com.quseit.payapp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.manager.ImageAssetManager;
import com.quseit.payapp.R;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingActivity;
import com.quseit.payapp.util.DataStore2;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMDialog;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.lang.reflect.Method;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 12:34
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseActivity extends AppCompatActivity implements IActivity {

    TextView mTitleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
        initData();
    }

//    public void onWindowFocusChanged(boolean hasFocus) {
//        disableStatusBar();
//        super.onWindowFocusChanged(hasFocus);
//    }
//
//    public void disableStatusBar(){
//        try {
//            @SuppressLint("WrongConstant") Object service = getSystemService("statusbar");
//            Class<?> claz = Class.forName("android.app.StatusBarManager");
//            Method expand = claz.getMethod("disable",int.class);
//            expand.invoke(service,0x00000001);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 设置toolbar右边的图标
     */
    public void setRightIcon(int res, View.OnClickListener listener) {
        ImageView imageView = findViewById(R.id.toolbar_right_icon);
        if (imageView != null) {
            imageView.setImageResource(res);
            findViewById(R.id.toolbar_right_layout).setOnClickListener(listener);
        }
    }

    /**
     * 设置toolbar右边的文字
     */
    public void setRightText(String text, View.OnClickListener listener) {
        TextView textView = findViewById(R.id.toolbar_right_tv);
        if (textView != null) {
            textView.setText(text);
            findViewById(R.id.toolbar_right_layout).setOnClickListener(listener);
        }
    }

    /**
     * 设置标题
     */
    private void initToolbar() {
        mTitleText = findViewById(R.id.toolbar_title);
        if (mTitleText != null) {
            if (getToolbarTitle() != null)
                mTitleText.setText(getToolbarTitle());
        }
    }

    /**
     * 返回按钮
     */
    public void back(View view) {
        finish();
    }

    /**
     * 消息提示
     */
    public void toast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 关闭软键盘
     */
    public void closeKeyBoard() {
        View view = getWindow().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        closeKeyBoard();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        closeKeyBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscriber
    public void exit(String exit){
        if (exit.equals(GlobalBean.EXIT_APP)){
            finish();
        }
    }

    public void settingToken(){
        final Intent intent = new Intent(this, DeviceSettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        DialogManager.rmDialog(this, "your device token is invaliable", GlobalBean.CANCEL_ICON, R.color.red, new RMDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {
                DataStore2.getInstance().clear();
                startActivity(intent);
                finish();
            }
        });
    }
}
