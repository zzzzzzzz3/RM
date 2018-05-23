package com.quseit.payapp.adapter;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedImageView;
import com.quseit.payapp.R;
import com.quseit.payapp.bean.response.refund_users.UserBean;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter {

    private List<UserBean> mData;

    public CardPagerAdapter() {
        mData = new ArrayList<>();
    }

    public void addCardItem(UserBean item) {
        mData.add(item);
    }

    public UserBean getUserAt(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter_card, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        View cardView = view.findViewById(R.id.cardView);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void bind(UserBean item, View view) {
        final RoundedImageView imageView = view.findViewById(R.id.user_avatar_img);
        TextView userTv = view.findViewById(R.id.user_name_tv);
        Glide.with(view).asBitmap().load(item.getAvatarUrl()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                imageView.setImageBitmap(resource);
            }
        });
        userTv.setText(item.getFirstName());
    }

}