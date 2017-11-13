package com.quseit.payapp.bussiness.pay;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.quseit.dev.RetrofitManager;
import com.quseit.pay.ScanUtil;
import com.quseit.payapp.Http.CommonService;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ResponseBean;
import com.quseit.payapp.bussiness.description.DescriptionActivity;
import com.quseit.payapp.util.AmountInputUtil;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.widget.NumberKeyboard;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;


import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 文 件 名: PaymentActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/7 12:48
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    @BindView(R.id.payment_number_tv)
    TextView mPaymentTv;
    @BindView(R.id.remark_tv)
    TextView remarkTv;
    private ScanUtil mScanUtil;
    private final String defaultNum = "0.00";
    private RMProgressDialog mRMProgressDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        mNumberKeyboard.setOnKeyClickListener(new NumberKeyboard.OnKeyClickListener() {
            @Override
            public void onNumberClick(int number) {
                mPaymentTv.setText(AmountInputUtil.input(number+"",mPaymentTv.getText().toString()));
            }

            @Override
            public void onDeleteClick() {
                mPaymentTv.setText(AmountInputUtil.deleteNum(mPaymentTv.getText().toString(),true));
            }

            @Override
            public void onEndKeyClick() {
                mPaymentTv.setText(defaultNum);
            }
        });

        mPaymentTv.setText(defaultNum);
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Payment";
    }

    @OnClick(R.id.mumber_icon)
    public void mumber() {
        toast("mumber");
    }

    @OnClick(R.id.desc_icon)
    public void desc() {
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(GlobalBean.REMARK,remarkTv.getText().toString());
        startActivityForResult(intent,GlobalBean.REMARK_REQUEST);
    }

    @OnClick(R.id.cash_icon)
    public void cash() {
        DialogManager.rmDialog(this, "Continue as cash payment?", R.mipmap.place_holder_icon_black, new RMDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {
                toast("success");
            }
        });
    }

    @OnClick(R.id.scan_icon)
    public void scan() {
        PermissionUtil.requestPermissions(this, Manifest.permission.CAMERA, new PermissionUtil.RequestPermissionCallback() {
            @Override
            public void onGranted() {
                String amount = mPaymentTv.getText().toString();
                if (Double.parseDouble(amount) < 1.00) {
                    toast("Minimum amount is RM 1.00");
                } else {
                    String remark = remarkTv.getText().toString();
                    if (remark.equals("")){
                        remark = "no remark";
                    }
                    doScan(amount,remark);
                }
            }

            @Override
            public void onUnGranted() {
                toast("请授予打开相机的权限！");
            }
        });
    }

    private void doScan(final String amount,final String remark) {
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {
                Log.d("Scan", msg);
                mScanUtil.closeScan();
            }

            @Override
            public void onResult(final String s) {
                Log.d("Scan", s);
                mScanUtil.closeScan();
                DialogManager.rmDialog(PaymentActivity.this, "Continue as QR Code Payment?", R.mipmap.place_holder_icon_black, new RMDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick() {
                        pay(s,amount,remark);
                    }
                });
            }

            @Override
            public void onCancel() {
                Log.d("Scan", "onCancel");
                mScanUtil.closeScan();
            }

            @Override
            public void onTimeout() {
                Log.d("Scan", "onTimeout");
                mScanUtil.closeScan();
            }
        });
    }

    private void pay(String s, String amount,String remark) {
        RetrofitManager.getInstance().createService(CommonService.class).pay(amount, s, remark, "123456")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mRMProgressDialog = DialogManager.rmProgressDialog(PaymentActivity.this,"loading...");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .subscribe(new Observer<ResponseBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBean responseBean) {
                        if (responseBean.success()){
                            mRMProgressDialog.setStatus(RMProgressDialog.TYPE.SUCCESS,responseBean.getMsg(),true);
                            mPaymentTv.setText(defaultNum);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        mRMProgressDialog.setStatus(RMProgressDialog.TYPE.FAILED,"net error",false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mScanUtil.closeScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GlobalBean.REMARK_REQUEST  && resultCode == RESULT_OK){
            String remarkStr = data.getStringExtra(GlobalBean.REMARK);
            remarkTv.setText(remarkStr);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
