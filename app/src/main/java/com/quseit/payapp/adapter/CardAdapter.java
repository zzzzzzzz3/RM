package com.quseit.payapp.adapter;

import android.view.View;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 4;

    float getBaseElevation();

    View getCardViewAt(int position);

    int getCount();
}