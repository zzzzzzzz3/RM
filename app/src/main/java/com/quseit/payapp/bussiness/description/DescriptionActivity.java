package com.quseit.payapp.bussiness.description;

import android.widget.EditText;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;

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

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Description";
    }

    @OnClick(R.id.save_btn)
    public void saveDesc() {
        toast("save");
    }
}
