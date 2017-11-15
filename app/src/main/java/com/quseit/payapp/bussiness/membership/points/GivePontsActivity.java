package com.quseit.payapp.bussiness.membership.points;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.IconText;
import com.quseit.payapp.widget.NumberKeyboard;
import com.quseit.payapp.widget.RMDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: GivePontsActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 12:19
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class GivePontsActivity extends BaseActivity {

    @BindView(R.id.points_edit)
    TextView pointsEdit;
    @BindView(R.id.mobile_edit)
    TextView mobileEdit;
    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    @BindView(R.id.scan_icon)
    IconText scanIcon;
    private ScanUtil mScanUtil;

    private Animation inAnim, outAnim;
    private RMDialog mSuccessDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_give_points;
    }

    @Override
    public void initView() {

        scanIcon.setText(GlobalBean.QRCODE_ICON);

        setRightText("Done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        mNumberKeyboard.setEndKeyName("Enter");
        mNumberKeyboard.setOnKeyClickListener(new NumberKeyboard.OnKeyClickListener() {
            @Override
            public void onNumberClick(int number) {
                if (pointsEdit.isSelected()) {
                    String string = pointsEdit.getText().toString();
                    pointsEdit.setText(string + number);
                }
                if (mobileEdit.isSelected()) {
                    String string2 = mobileEdit.getText().toString();
                    mobileEdit.setText(string2 + number);
                }
            }

            @Override
            public void onDeleteClick() {
                if (pointsEdit.isSelected()) {
                    String msg = pointsEdit.getText().toString();
                    if (msg.length()>0)
                    pointsEdit.setText(msg.substring(0, msg.length() - 1));
                }
                if (mobileEdit.isSelected()) {
                    String msg = mobileEdit.getText().toString();
                    if (msg.length()>0)
                    mobileEdit.setText(msg.substring(0, msg.length() - 1));
                }
            }

            @Override
            public void onEndKeyClick() {
                if (mobileEdit.isSelected()) {
                    mobileEdit.setSelected(false);
                }
                if (pointsEdit.isSelected()) {
                    pointsEdit.setSelected(false);
                }
                keyboardClose();
            }
        });
    }

    private void keyboardClose() {
        if (outAnim == null) {
            outAnim = AnimationUtils.loadAnimation(GivePontsActivity.this, R.anim.slide_out_bottom);
        }
        if (mNumberKeyboard.isShown()) {
            mNumberKeyboard.setAnimation(outAnim);
            mNumberKeyboard.setVisibility(View.GONE);
        }
    }

    private void keyboardShow() {
        if (inAnim == null) {
            inAnim = AnimationUtils.loadAnimation(GivePontsActivity.this, R.anim.slide_in_bottom);
        }
        if (mNumberKeyboard.isShown()) {
            return;
        }
        mNumberKeyboard.setAnimation(inAnim);
        mNumberKeyboard.setVisibility(View.VISIBLE);
    }

    private void done() {
        showSuccess();
    }

    private void showSuccess(){
        if (mSuccessDialog ==null){
            mSuccessDialog = new RMDialog(this)
                    .setIcon(GlobalBean.POINTS_ICON)
                    .setText("Name has earned:")
                    .setSubText("1000 points")
                    .setIconColor(UIUtil.getInstance().getColor(R.color.green))
                    .setPositionBtn("OK", new RMDialog.OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick() {

                        }
                    });
        }
        if (!mSuccessDialog.isShowing()){
            mSuccessDialog.show();
        }
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);

    }

    @Override
    public String getToolbarTitle() {
        return "Loyaty Points";
    }

    @OnClick(R.id.scan_icon)
    public void scan() {
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {
                mScanUtil.closeScan();
            }

            @Override
            public void onResult(String s) {
                mScanUtil.closeScan();
                mobileEdit.setText(s);
            }

            @Override
            public void onCancel() {
                mScanUtil.closeScan();
            }

            @Override
            public void onTimeout() {
                mScanUtil.closeScan();
            }
        });
    }

    @OnClick(R.id.mobile_edit)
    public void mobile() {
        mobileEdit.setSelected(true);
        pointsEdit.setSelected(false);
        keyboardShow();
    }

    @OnClick(R.id.points_edit)
    public void points() {
        mobileEdit.setSelected(false);
        pointsEdit.setSelected(true);
        keyboardShow();
    }
}
