package com.quseit.payapp.bussiness.transations;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.TransationsAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.response.TransationBean;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingActivity;
import com.quseit.payapp.bussiness.orderDetail.OrderDetailActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMEditDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: TransationsActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 12:30
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationsActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener ,TransationsContract.TransationsView{

    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.transations_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.refunds_checkbox)
    CheckBox refundCheckbox;
    @BindView(R.id.empty_view)
    ImageView emptyView;
    private int year, month, day;
    private TransationsAdapter mTransationsAdapter;
    private List<TransationBean> mTransationBeans = new ArrayList<>();
    private DatePickerDialog mDatePickerDialog;
    private RMProgressDialog mRMProgressDialog;
    private TransationsContract.TransationsPresenter mTransationsPresenter;

    @Override
    public int getRootView() {
        return R.layout.activity_transations;
    }

    @Override
    public void initView() {
        setRightIcon(R.mipmap.search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogManager.rmEditDialog(TransationsActivity.this, "Search by order number", "Order No", new RMEditDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(String editStr) {
                        search(editStr);
                    }
                });
            }
        });
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        day = now.get(Calendar.DAY_OF_MONTH);
        dateTv.setText(year + "/" + (month + 1) + "/" + day);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTransationsAdapter = new TransationsAdapter(this, mTransationBeans);
        mRecyclerView.setAdapter(mTransationsAdapter);

        mSmartRefreshLayout.autoRefresh();
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mTransationsPresenter.getTransation();
            }
        }).setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mTransationsPresenter.loadMore();
            }
        });

        refundCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // TODO: 2017/11/13
                }
            }
        });

        mRMProgressDialog = new RMProgressDialog(this);
    }

    private void search(String editStr) {
        // TODO: 2017/11/13
    }

    @Override
    public void initData() {
        mTransationsPresenter = new TransationsPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Transations";
    }

    @OnClick(R.id.date_pick_layout)
    public void pickDate() {
        if (mDatePickerDialog == null) {
            mDatePickerDialog = DatePickerDialog.newInstance(
                    this,
                    year,
                    month,
                    day
            );
            mDatePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
            mDatePickerDialog.setAccentColor(ContextCompat.getColor(this, R.color.blue));
        }
        mDatePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        dateTv.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
        // TODO: 2017/11/13
    }

    @Subscriber
    public void orderDetail(TransationBean bean) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra(GlobalBean.TRANSATION_BEAN,bean);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
        if (mSmartRefreshLayout.isRefreshing()){
            mSmartRefreshLayout.finishRefresh();
        }
        if (mSmartRefreshLayout.isLoading()){
            mSmartRefreshLayout.finishLoadmore();
        }
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
    public void addDataToList(List<TransationBean> data) {
        if (data.size()>0){
            emptyView.setVisibility(View.GONE);
            mTransationBeans = data;
            mTransationsAdapter.setData(mTransationBeans);
        }else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadMore(List<TransationBean> data) {
        mTransationBeans.addAll(data);
        mTransationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success){
            DialogManager.successDialog(this,msg,null);
        }else {
            DialogManager.failDialog(this,msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTransationsPresenter.onDestroy();
    }
}
