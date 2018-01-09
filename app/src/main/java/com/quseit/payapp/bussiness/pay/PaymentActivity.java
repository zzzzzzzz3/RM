package com.quseit.payapp.bussiness.pay;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.pay.PayInfoBean;
import com.quseit.pay.PrintUtil;
import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.bussiness.membership.MembershipActivity;
import com.quseit.payapp.util.AmountInputUtil;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.widget.IconText;
import com.quseit.payapp.widget.NumberKeyboard;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;


import butterknife.BindView;
import butterknife.OnClick;


/**
 * 文 件 名: PaymentActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/7 12:48
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PaymentActivity extends BaseActivity implements PayContract.PayView {

    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    @BindView(R.id.payment_number_tv)
    TextView mPaymentTv;
    @BindView(R.id.remark_tv)
    TextView remarkTv;
    @BindView(R.id.sure_iv)
    ImageView mSureIV;
    private ScanUtil mScanUtil;
    private final String defaultNum = "0.00";
    private RMProgressDialog mRMProgressDialog;

    private PayContract.PayPresenter mPayPresenter;
    private Member mMember;
    private PrintUtil mPrintUtil;

    @Override
    public int getRootView() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        mNumberKeyboard.setOnKeyClickListener(new NumberKeyboard.OnKeyClickListener() {
            @Override
            public void onNumberClick(int number) {
                mPaymentTv.setText(AmountInputUtil.input(number + "", mPaymentTv.getText().toString()));
            }

            @Override
            public void onDeleteClick() {
                mPaymentTv.setText(AmountInputUtil.deleteNum(mPaymentTv.getText().toString(), true));
            }

            @Override
            public void onEndKeyClick() {
                mPaymentTv.setText(defaultNum);
            }
        });

        mPaymentTv.setText(defaultNum);

        mRMProgressDialog = new RMProgressDialog(this);
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
        mPayPresenter = new PayPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Payment";
    }

    @OnClick(R.id.mumber_icon)
    public void mumber() {
        startActivityForResult(new Intent(this, MemberActivity.class), GlobalBean.MEMBER_REQUEST);
    }

    @OnClick(R.id.remark_fl)
    public void desc() {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(GlobalBean.REMARK, remarkTv.getText().toString());
        startActivityForResult(intent, GlobalBean.REMARK_REQUEST);
    }

    @OnClick(R.id.cash_icon)
    public void cash() {
        final String amount = mPaymentTv.getText().toString();
        if (Double.parseDouble(amount) < 0.1) {
            toast("Minimum amount is RM 0.1");
            return;
        }

        DialogManager.rmDialog(this, "Continue with cash payment?", getString(R.string.cash_font), R.color.payment_bg_color, new RMDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {
                String remark = remarkTv.getText().toString();
                if (remark.equals("")) {
                    remark = "In-store payment";
                }
                mPayPresenter.pay(amount, "", remark, "123456", mMember);
            }
        });
    }

    @OnClick(R.id.scan_icon)
    public void scan() {
        PermissionUtil.requestPermissions(this, Manifest.permission.CAMERA, new PermissionUtil.RequestPermissionCallback() {
            @Override
            public void onGranted() {
                String amount = mPaymentTv.getText().toString();
                if (Double.parseDouble(amount) < 0.1) {
                    toast("Minimum amount is RM 0.1");
                } else {
                    String remark = remarkTv.getText().toString();
                    if (remark.equals("")) {
                        remark = "In-store payment";
                    }
                    doScan(amount, remark);
                }
            }

            @Override
            public void onUnGranted() {
                toast("no permission！");
            }
        });
    }

    private void doScan(final String amount, final String remark) {
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {
                Log.d("Scan", msg);
            }

            @Override
            public void onResult(final String s) {
                pay(s, amount, remark);
            }

            @Override
            public void onCancel() {
                Log.d("Scan", "onCancel");
            }

            @Override
            public void onTimeout() {
                Log.d("Scan", "onTimeout");
            }
        });
    }

    private void pay(String authcode, String amount, String remark) {
        mPayPresenter.pay(amount, authcode, remark, "123456", mMember);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScanUtil.closeScan();
        mPayPresenter.onDestroy();
        if (mPrintUtil != null)
            mPrintUtil.logout();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GlobalBean.REMARK_REQUEST && resultCode == RESULT_OK) {
            String remarkStr = data.getStringExtra(GlobalBean.REMARK);
            remarkTv.setText(remarkStr);
        } else if (requestCode == GlobalBean.MEMBER_REQUEST && resultCode == RESULT_OK) {
            mMember = (Member) data.getSerializableExtra(GlobalBean.MEMBER);
            if (mMember != null) {
                if ((mMember.getType() == Member.ID && mMember.getMemberId() == 0) || (mMember.getType() == Member.PHONENUMBER && mMember.getPhoneNumber().isEmpty())) {
                    mSureIV.setVisibility(View.GONE);
                    mMember = null;
                } else {
                    mSureIV.setVisibility(View.VISIBLE);
                }
            } else {
                mSureIV.setVisibility(View.GONE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        toast(message);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void setUpToken() {
        settingToken();
    }

    @Override
    public void printPayInfo(PayInfoBean payInfo) {
        if (mPrintUtil == null) {
            mPrintUtil = new PrintUtil();
            mPrintUtil.deviceLogin(this);
        }
        mPrintUtil.printPayInfo(this, payInfo);
    }

    @Override
    public void showDialog(String msg, boolean isSuccess) {
        if (isSuccess) {
            DialogManager.successDialog(this, msg, null);
        } else {
            DialogManager.failDialog(this, msg);
        }
    }
}
