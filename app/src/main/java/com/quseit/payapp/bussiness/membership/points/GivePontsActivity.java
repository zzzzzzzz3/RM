package com.quseit.payapp.bussiness.membership.points;

import android.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.request.PointsRequestBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMProgressDialog;
import com.quseit.payapp.widget.SelectDialog;

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
    FrameLayout phoneClick;
    @BindView(R.id.click_id)
    FrameLayout idClick;

    private ScanUtil mScanUtil;

    private PointsContract.PointsPresenter mPointsPresenter;
    private RMProgressDialog mRMProgressDialog;
    private SelectDialog mSelectDialog;

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
        initSelectDialog();
        phoneClick.setSelected(true);
        phoneELayout.setExpanded(true);
    }


    private void done() {
        String amount = pointsEdit.getText().toString();
        if (idELayout.isExpanded()) {
            String id = idEdit.getText().toString();
            mPointsPresenter.givePoints(amount.equals("") ? 0 : Integer.parseInt(amount), "", "", PointsRequestBean.ID, id.isEmpty() ? 0 : Long.parseLong(id));
        } else if (phoneELayout.isExpanded()) {
            String phone = phoneEdit.getText().toString();
            String countryCode = selectText.getText().toString();
            mPointsPresenter.givePoints(amount.equals("") ? 0 : Integer.parseInt(amount), phone, countryCode, PointsRequestBean.PHONENUMBER, 0);
        } else {
            toast("input the phone or member id");
        }
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
        mPointsPresenter = new PointsPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Loyalty Points";
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
                idEdit.setText(s);
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
        if (success) {
            DialogManager.rmDialogSubTextComfirm(this, msg, pointsEdit.getText().toString() + " Points", getString(R.string.points_font), R.color.green);
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
        idClick.setSelected(false);
        phoneClick.setSelected(true);
        phoneELayout.expand();
        idELayout.collapse();
    }

    @OnClick(R.id.click_id)
    public void showIDInput() {
        idClick.setSelected(true);
        phoneClick.setSelected(false);
        idELayout.expand();
        phoneELayout.collapse();
    }

    @OnClick(R.id.tv_select)
    public void selectCountryCode() {
        select();
    }

    public void select() {
        mSelectDialog.show();
    }

    private void initSelectDialog() {
        mSelectDialog = new SelectDialog(this);
        mSelectDialog.setOkCallback(new SelectDialog.OkCallback() {
            @Override
            public void onOk(String item) {
                selectText.setText(item);
            }
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("60");
        list.add("80");
        mSelectDialog.setItems(list);
    }

}
