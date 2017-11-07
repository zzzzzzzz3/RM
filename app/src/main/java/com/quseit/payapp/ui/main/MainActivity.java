package com.quseit.payapp.ui.main;
import android.databinding.ViewDataBinding;
import com.quseit.payapp.R;
import com.quseit.payapp.adapter.MainFragmentAdapter;
import com.quseit.payapp.bean.ToolBarContent;
import com.quseit.payapp.databinding.ActivityMainBinding;
import com.quseit.payapp.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    ActivityMainBinding mBind;

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initView(ViewDataBinding binging) {
        ToolBarContent mBarContent =new ToolBarContent("MAIN");
        mBind= (ActivityMainBinding) binging;
        mBind.setToolbar(mBarContent);
        MainFragmentAdapter mAdapter=new MainFragmentAdapter(getSupportFragmentManager());
        mBind.vpMain.setAdapter(mAdapter);
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

}
