package com.quseit.payapp.bussiness.devicesetting;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.quseit.pay.PrintUtil;
import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bussiness.main.MainActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: DeviceSettingActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 17:25
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DeviceSettingActivity extends BaseActivity implements DeviceSettingContract.DeviceSettingView {

    @BindView(R.id.device_token_edit)
    EditText mEditText;
    private ScanUtil mScanUtil;

    private RMProgressDialog mRMProgressDialog;
    private DeviceSettingContract.DeviceSettingPresenter mDeviceSettingPresenter;

    @Override
    public int getRootView() {
        return R.layout.activity_device_setting;
    }

    @Override
    public void initView() {
        mRMProgressDialog = new RMProgressDialog(this);
    }

    @Override
    public void initData() {
        mDeviceSettingPresenter = new DeviceSettingPresenterImpl(this);
        mScanUtil = new ScanUtil(this);
    }

    @Override
    public String getToolbarTitle() {
        return "DeviceRegister";
    }

    @OnClick(R.id.btn_ok)
    public void saveDeviceToken() {
        String token = mEditText.getText().toString();
        if (token.equals("")) {
            toast("device token is illegal");
            return;
        }
        mDeviceSettingPresenter.saveToken(token);
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

    }

    @OnClick(R.id.scan_icon)
    public void scan(){
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResult(String s) {
                mEditText.setText(s);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onTimeout() {
                toast("error");
            }
        });
    }

    @Override
    public void back(View view) {
        EventBus.getDefault().post(GlobalBean.EXIT_APP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(GlobalBean.EXIT_APP);
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success) {
            DialogManager.successDialog(this, msg, new RMDialog.OnPositiveClickListener() {
                @Override
                public void onPositiveClick() {
                    startActivity(new Intent(DeviceSettingActivity.this, MainActivity.class));
                    finish();
                }
            });
        } else {
            DialogManager.failDialog(this, msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDeviceSettingPresenter.onDestroy();
        mScanUtil.closeScan();
    }
}
