package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.quseit.payapp.R;
import com.quseit.payapp.bean.GlobalBean;

/**
 * 文 件 名: RMProgressDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/11 19:25
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RMProgressDialog extends Dialog {

    private LottieAnimationView mLottieAnimationView;


    public RMProgressDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_rm_progress);
        initView();
    }

    private void initView() {
        mLottieAnimationView = findViewById(R.id.animation_view);
    }

    public RMProgressDialog setLoading(String jsonFileName) {
        mLottieAnimationView.setAnimation(jsonFileName);
        return this;
    }

    @Override
    public void show() {
        super.show();
        if (!mLottieAnimationView.isAnimating()) {
            mLottieAnimationView.playAnimation();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mLottieAnimationView.isAnimating()) {
            mLottieAnimationView.cancelAnimation();
        }
    }
}
