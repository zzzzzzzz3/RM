package com.quseit.payapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.quseit.payapp.ui.main.MainFragment;

/**
 * Created by quseitu on 2017/11/7.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MainFragment.newInstance(true);
            case 1:
                return MainFragment.newInstance(false);
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
