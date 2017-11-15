package com.quseit.payapp.bussiness.setting;

import android.content.Intent;
import android.provider.Settings;
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
        mItemBeans.add(new ItemBean(R.color.wifi_bg_color, GlobalBean.WIFI, GlobalBean.WIFI_ICON));
        mItemBeans.add(new ItemBean(R.color.sim_bg_color, GlobalBean.SIM, GlobalBean.SIM_ICON));
        mItemBeans.add(new ItemBean(R.color.sound_bg_color, GlobalBean.SOUND, GlobalBean.SOUND_ICON));
        mItemBeans.add(new ItemBean(R.color.brightness_bg_color, GlobalBean.BRIGHTNESS, GlobalBean.BRIGHTNESS_ICON));
        mItemBeans.add(new ItemBean(R.color.about_bg_color, GlobalBean.ABOUT, GlobalBean.ABOUT_ICON));
        mItemBeans.add(new ItemBean(R.color.language_bg_color, GlobalBean.LANGUAGE, GlobalBean.LANGUAGE_ICON));
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
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case GlobalBean.SIM:
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                break;
            case GlobalBean.SOUND:
                startActivity(new Intent(Settings.ACTION_SOUND_SETTINGS));
                break;
            case GlobalBean.BRIGHTNESS:
                startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
                break;
            case GlobalBean.ABOUT:
                toast("ABOUT");
                break;
            case GlobalBean.LANGUAGE:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                break;

        }
    }
}
