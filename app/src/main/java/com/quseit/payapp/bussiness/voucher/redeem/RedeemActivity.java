package com.quseit.payapp.bussiness.voucher.redeem;

import android.Manifest;
import android.app.Dialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.IconText;
import com.quseit.payapp.widget.NumberKeyboard;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: RedeemActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 10:38
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RedeemActivity extends BaseActivity implements RedeemContract.RedeemView{

    @BindView(R.id.voucher_code__tv)
    EditText voucherCodeTv;
    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    private ScanUtil mScanUtil;
    private RMProgressDialog mRMProgressDialog;
private RedeemContract.RedeemPresenter mRedeemPresenter;
    @Override
    public int getRootView() {
        return R.layout.activity_redeem;
    }

    @Override
    public void initView() {
        mNumberKeyboard.setEndKeyName("Enter");
        mNumberKeyboard.setOnKeyClickListener(new NumberKeyboard.OnKeyClickListener() {
            @Override
            public void onNumberClick(int number) {
                String msg = voucherCodeTv.getText().toString();
                if (msg.length() <= 15)
                    voucherCodeTv.setText(msg + number);
            }

            @Override
            public void onDeleteClick() {
                String msg = voucherCodeTv.getText().toString();
                if (!msg.equals(""))
                    voucherCodeTv.setText(msg.substring(0, msg.length() - 1));
            }

            @Override
            public void onEndKeyClick() {
                submit();
            }
        });
        mRMProgressDialog = new RMProgressDialog(this);
    }

    private void submit() {
        String code = voucherCodeTv.getText().toString();
        if (code.equals("")){
            toast("code must be not empty");
            return;
        }
        mRedeemPresenter.redeem(code);
        voucherCodeTv.setText("");
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
        mRedeemPresenter = new RedeemPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Redeem Voucher";
    }

    @OnClick(R.id.scan_icon)
    public void scan() {
        PermissionUtil.requestPermissions(this, Manifest.permission.CAMERA, new PermissionUtil.RequestPermissionCallback() {
            @Override
            public void onGranted() {
                doScan();
            }

            @Override
            public void onUnGranted() {
                toast("请授予打开相机的权限！");
            }
        });
    }

    private void doScan() {
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {
                Log.d("Scan", msg);
                DialogManager.failDialog(RedeemActivity.this,"Voucher redemption fail");
            }

            @Override
            public void onResult(final String s) {
                Log.d("Scan", s);
                voucherCodeTv.setText(s);
                submit();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScanUtil.closeScan();
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
    public void showDialog(String msg, boolean success) {
        if (success){
            DialogManager.successDialog(this, msg, null);
        }else {
            DialogManager.failDialog(this,msg);
        }
    }
}
