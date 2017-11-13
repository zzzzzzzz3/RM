package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.quseit.payapp.R;

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
    private TextView mMsgTv;
    private LottieAnimationView mStatusView;
    private FrameLayout ensureLayout;
    private Button ensureBtn;
    private ImageView statusIcon;

    public enum TYPE {
        SUCCESS, FAILED
    }

    public RMProgressDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_rm_progress);
        initView();
    }

    private void initView() {
        mMsgTv = findViewById(R.id.dialog_text);
        mLottieAnimationView = findViewById(R.id.animation_view);
        mStatusView = findViewById(R.id.status_view);
        ensureLayout = findViewById(R.id.ensure_layout);
        ensureBtn = findViewById(R.id.btn_ok);
        statusIcon = findViewById(R.id.status_icon);
        ensureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public RMProgressDialog setMsg(String msg) {
        if (msg != null && !msg.equals("")) {
            mMsgTv.setVisibility(View.VISIBLE);
            mMsgTv.setText(msg);
        }
        return this;
    }

    public RMProgressDialog setLoading(String jsonFileName) {
        mLottieAnimationView.setAnimation(jsonFileName);
        return this;
    }

    public RMProgressDialog setStatus(RMProgressDialog.TYPE type,String msg,boolean showButton) {
        if (mLottieAnimationView.isAnimating()) {
            mLottieAnimationView.cancelAnimation();
            mLottieAnimationView.setVisibility(View.GONE);
        }
        if (msg!=null&&!msg.equals("")){
            mMsgTv.setVisibility(View.VISIBLE);
            mMsgTv.setText(msg);
        }
        //mStatusView.setVisibility(View.VISIBLE);
        if (type == TYPE.FAILED) {
//            try{
//                mStatusView.setAnimation("false.json");
//                mStatusView.playAnimation();
//            }catch (Exception e){
//                e.printStackTrace();
                statusIcon.setVisibility(View.VISIBLE);
                mStatusView.setImageResource(R.mipmap.failed_icon);
//            }
        }else {
//            try{
//                mStatusView.setAnimation("success_icon.json");
//                mStatusView.playAnimation();
//            }catch (Exception e){
//                e.printStackTrace();
                statusIcon.setVisibility(View.VISIBLE);
                mStatusView.setImageResource(R.mipmap.success_icon);
//            }
        }
        if (showButton){
            ensureLayout.setVisibility(View.VISIBLE);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 1500);
        }
        return this;
    }

    @Override
    public void show() {
        super.show();
        mStatusView.setVisibility(View.GONE);
        statusIcon.setVisibility(View.GONE);
        mLottieAnimationView.setVisibility(View.VISIBLE);
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
        if (mStatusView.isAnimating()){
            mStatusView.cancelAnimation();
        }
        mStatusView.setVisibility(View.GONE);
        statusIcon.setVisibility(View.GONE);
        ensureLayout.setVisibility(View.GONE);
    }
}
