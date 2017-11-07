package com.quseit.payapp.ui;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;



/**
 * Created by Administrator on 2016/9/12. 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity
{
    private Toast toast;
    private Dialog dialog;
//    public Tracker tracker;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //去掉actionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ViewDataBinding mBinding= DataBindingUtil.setContentView(this,setViewId());
        initView(mBinding);// 初始化界面
        initEvent();// 初始化点击事件
        loadData();// 请求数据
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    protected abstract void initEvent();

    protected abstract void initView(ViewDataBinding binging);

    protected abstract void loadData();

    protected abstract int setViewId();


    public void startActivity(Class<?> pClass){
        startActivity(new Intent(this,pClass));
    }
    //短时间的Toast
    public void showToast(String msg){

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.show();
    }
    //长时间的Toast
    public void showToast(String msg, int longtimeshow){
        /*if (toast == null) {
            toast = Toast.makeText(XApp.getContext(), msg, Toast.LENGTH_LONG);
        }*/
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(msg);
        toast.show();
    }
    public void showToast(int resId){
        showToast(getString(resId));
    }





}
