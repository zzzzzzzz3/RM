package com.quseit.payapp.bussiness.pay;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.quseit.pay.ScanUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.widget.SelectDialog;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: MemberActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/1/3 14:45
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MemberActivity extends BaseActivity {

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

    private SelectDialog mSelectDialog;
    private ScanUtil mScanUtil;

    @Override
    public int getRootView() {
        return R.layout.activity_member;
    }

    @Override
    public void initView() {
        setRightText("Save", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMember();
            }
        });
        initSelectDialog();
        phoneClick.setSelected(true);
        phoneELayout.setExpanded(true);
    }

    private void setMember() {
        Intent intent = new Intent();
        if (idELayout.isExpanded()) {
            String id = idEdit.getText().toString();
            Member member = new Member(Member.ID, null, null, Long.parseLong(id));
            intent.putExtra(GlobalBean.MEMBER, member);
        } else if (phoneELayout.isExpanded()) {
            String phone = phoneEdit.getText().toString();
            String countryCode = selectText.getText().toString();
            if (phone.length() > 9) {
                toast("The phoneNumber,omitempty must be at least 9 characters.");
                return;
            }
            Member member = new Member(Member.PHONENUMBER, countryCode, phone, 0);
            intent.putExtra(GlobalBean.MEMBER, member);
        } else {
            Member member = null;
            intent.putExtra(GlobalBean.MEMBER, member);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void initData() {
        mScanUtil = new ScanUtil(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Member";
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

    @OnClick(R.id.scan_icon)
    public void scan() {
        mScanUtil.beginScan(this, new ScanUtil.ScanCallback() {
            @Override
            public void onError(String msg) {

            }

            @Override
            public void onResult(String s) {
                idEdit.setText(s);
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
    protected void onDestroy() {
        super.onDestroy();
        mScanUtil.closeScan();
    }
}
