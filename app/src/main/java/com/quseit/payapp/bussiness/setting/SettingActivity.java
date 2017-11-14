package com.quseit.payapp.bussiness.setting;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GridAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: SettingActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/14 09:39
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SettingActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;
    private List<ItemBean> mItemBeans = new ArrayList<>();

    @Override
    public int getRootView() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mItemBeans.add(new ItemBean(R.color.wifi_bg_color, GlobalBean.WIFI, R.mipmap.place_holder_icon_white));
        mItemBeans.add(new ItemBean(R.color.sim_bg_color, GlobalBean.SIM, R.mipmap.place_holder_icon_white));
        mItemBeans.add(new ItemBean(R.color.sound_bg_color, GlobalBean.SOUND, R.mipmap.place_holder_icon_white));
        mItemBeans.add(new ItemBean(R.color.brightness_bg_color, GlobalBean.BRIGHTNESS, R.mipmap.place_holder_icon_white));
        mItemBeans.add(new ItemBean(R.color.about_bg_color, GlobalBean.ABOUT, R.mipmap.place_holder_icon_white));
        mItemBeans.add(new ItemBean(R.color.language_bg_color, GlobalBean.LANGUAGE, R.mipmap.place_holder_icon_white));
        mGridAdapter = new GridAdapter(this, mItemBeans);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Setting";
    }

    @Subscriber
    public void onClick(ItemBean itemBean) {
        switch (itemBean.itemName) {

            case GlobalBean.WIFI:
                toast("WIFI");
                break;
            case GlobalBean.SIM:
                toast("SIM");
                break;
            case GlobalBean.SOUND:
                toast("SOUND");
                break;
            case GlobalBean.BRIGHTNESS:
                toast("BRIGHTNESS");
                break;
            case GlobalBean.ABOUT:
                toast("ABOUT");
                break;
            case GlobalBean.LANGUAGE:
                toast("LANGUAGE");
                break;

        }
    }
}
