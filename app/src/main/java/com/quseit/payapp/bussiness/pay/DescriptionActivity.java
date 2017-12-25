package com.quseit.payapp.bussiness.pay;

import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.EditText;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 文 件 名: DescriptionActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 17:44
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DescriptionActivity extends BaseActivity {

    @BindView(R.id.desc_edit)
    EditText descEdit;

    @Override
    public int getRootView() {
        return R.layout.activity_description;
    }

    @Override
    public void initView() {
        ViewCompat.setElevation(descEdit, UIUtil.dp2Px(this,8));
        String remarkStr = getIntent().getStringExtra(GlobalBean.REMARK);
        if (remarkStr != null){
            descEdit.setText(remarkStr);
            descEdit.setSelection(remarkStr.length());
        }
        setRightText("Save", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDesc();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Description";
    }

    private void saveDesc() {
        Intent intent = new Intent();
        intent.putExtra(GlobalBean.REMARK,descEdit.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
