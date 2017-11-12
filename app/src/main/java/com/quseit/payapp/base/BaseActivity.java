package com.quseit.payapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.quseit.payapp.R;

import org.simple.eventbus.EventBus;

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

    /**
     * 设置标题
     */
    private void initToolbar() {
        mTitleText = (TextView) findViewById(R.id.toolbar_title);
        if (mTitleText != null) {
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
}
