package com.quseit.payapp.bussiness.membership.points;

import android.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMProgressDialog;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

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

public class GivePontsActivity extends BaseActivity implements PointsContract.PointsView {

    @BindView(R.id.points_edit)
    EditText pointsEdit;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.id_edit)
    EditText idEdit;
    @BindView(R.id.eLayout_phone)
    ExpandableLayout phoneELayout;
    @BindView(R.id.eLayout_id)
    ExpandableLayout idELayout;
    @BindView(R.id.tv_select)
    TextView selectText;
    @BindView(R.id.click_phone)
    TextView phoneClick;
    @BindView(R.id.click_id)
    TextView idClick;

    private ScanUtil mScanUtil;


    private Animation inAnim, outAnim;
    private PointsContract.PointsPresenter mPointsPresenter;
    private RMProgressDialog mRMProgressDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_give_points;
    }

    @Override
    public void initView() {
        setRightText("Done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                done();
            }
        });
        mRMProgressDialog = new RMProgressDialog(this);
    }


    private void done() {
        String amount = pointsEdit.getText().toString();
        String phone = "";
        String id = "";
        String countryCode = "60";
        String type = "";
        if (phoneELayout.isExpanded()){
            type = "PHONENUMBER";
            phone = phoneEdit.getText().toString();
        }else {
            id=idEdit.getText().toString();
            type = "ID";
        }

        mPointsPresenter.givePoints(amount.equals("") ? 0 : Integer.parseInt(amount), phone, countryCode, type,id);
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
        mPointsPresenter = new PointsPresenterImpl(this);
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
                phoneEdit.setText(s);
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
        if (success) {
            DialogManager.rmDialogSubTextComfirm(this, msg, phoneEdit.getText().toString() + " Points", getString(R.string.points_font), R.color.green);
        } else {
            DialogManager.failDialog(this, msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPointsPresenter.onDestroy();
    }

    @OnClick(R.id.click_phone)
    public void showPhoneInput() {
        phoneELayout.expand();
        idELayout.collapse();

    }

    @OnClick(R.id.click_id)
    public void showIDInput() {
        idELayout.expand();
        phoneELayout.collapse();
    }

    @OnClick(R.id.tv_select)
    public void selectCountryCode(){
        select();
    }

    public void select(){
        AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        Window window = dialogBuilder.getWindow();
        window.setGravity(Gravity.BOTTOM);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_select, null);
        dialogBuilder.setView(dialogView);


        LoopView loopView = dialogView.findViewById(R.id.loopView);
        final ArrayList<String> list = new ArrayList<>();
        list.add("+60");
        list.add("+80");
        // 滚动监听
        loopView.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                selectText.setText(list.get(index));
            }
        });
        // 设置原始数据
        loopView.setItems(list);
        dialogBuilder.show();

    }

}
