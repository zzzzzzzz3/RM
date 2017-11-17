package com.quseit.payapp.bussiness.voucher.redeem;

import android.Manifest;
import android.util.Log;
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

public class RedeemActivity extends BaseActivity {

    @BindView(R.id.voucher_code__tv)
    TextView voucherCodeTv;
    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    @BindView(R.id.scan_icon)
    IconText scanIcon;
    private ScanUtil mScanUtil;
    private RMProgressDialog mRMProgressDialog;

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

        scanIcon.setText(GlobalBean.QRCODE_ICON);
    }

    private void submit() {
        toast("voucher code:" + voucherCodeTv.getText().toString());
        voucherCodeTv.setText("");
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
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
                DialogManager.rmDialog(RedeemActivity.this, "Voucher redemption fail",GlobalBean.CANCEL_ICON, UIUtil.getInstance().getColor(R.color.red));
            }

            @Override
            public void onResult(final String s) {
                Log.d("Scan", s);
                voucherCodeTv.setText(s);
                DialogManager.rmDialog(RedeemActivity.this, "Voucher redemption complete",GlobalBean.SUCCESSFUL_ICON, UIUtil.getInstance().getColor(R.color.green));
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
}
