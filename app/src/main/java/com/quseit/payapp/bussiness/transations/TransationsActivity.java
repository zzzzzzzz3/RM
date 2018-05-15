package com.quseit.payapp.bussiness.transations;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.TransationsAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.response.TransationBean;
import com.quseit.payapp.bean.response.pay_v3.Transaction;
import com.quseit.payapp.bussiness.orderDetail.OrderDetailActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.RMEditDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.simple.eventbus.Subscriber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

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

public class TransationsActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TransationsContract.TransationsView {

    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.transations_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.refunds_checkbox)
    CheckBox refundCheckbox;
    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.shadow_layout)
    FrameLayout shadowLayout;
    private int year, month, day;
    private TransationsAdapter mTransationsAdapter;
    private DatePickerDialog mDatePickerDialog;
    private RMProgressDialog mRMProgressDialog;
    private TransationsContract.TransationsPresenter mTransationsPresenter;
    private String endDate;
    private List<Transaction> mTransationBeansV3 = new ArrayList<>();

    @Override
    public int getRootView() {
        return R.layout.activity_transations;
    }

    @Override
    public void initView() {
        ViewCompat.setElevation(shadowLayout, UIUtil.dp2Px(this, 10));
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
        month = now.get(Calendar.MONTH) + 1;
        day = now.get(Calendar.DAY_OF_MONTH);
        setDate();
        endDate = parseDate(year, month, day + 1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTransationsAdapter = new TransationsAdapter(this, mTransationBeansV3);
        mRecyclerView.setAdapter(mTransationsAdapter);

        mSmartRefreshLayout.autoRefresh();
        mSmartRefreshLayout.setEnableAutoLoadmore(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mTransationsPresenter.getTransationV3(parseDate(year, month, day), endDate, refundCheckbox.isChecked(), false);
            }
        }).setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mTransationsPresenter.loadMore(false);
            }
        });

        refundCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTransationsPresenter.getTransation(parseDate(year, month, day), parseDate(year, month, day + 1), isChecked, true);
            }
        });

        mRMProgressDialog = new RMProgressDialog(this);
    }

    @Override
    public void initData() {
        mTransationsPresenter = new TransationsPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Transactions";
    }

    private void setDate() {
        String mon = month < 10 ? ("0" + month) : ("" + month);
        String d = day < 10 ? ("0" + day) : ("" + day);
        dateTv.setText(year + "/" + mon + "/" + d);
    }

    private void search(String editStr) {
        // TODO: 2017/11/13
    }

    @OnClick(R.id.date_pick_layout)
    public void pickDate() {
        if (mDatePickerDialog == null) {
            mDatePickerDialog = DatePickerDialog.newInstance(
                    this,
                    year,
                    month - 1,
                    day
            );
            mDatePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
            mDatePickerDialog.setAccentColor(ContextCompat.getColor(this, R.color.purple_color));
        }
        mDatePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear + 1;
        this.day = dayOfMonth;
        setDate();
        mTransationsPresenter.getTransation(parseDate(year, month, day), parseDate(year, month, day + 1), refundCheckbox.isChecked(), true);
    }

    @Subscriber
    public void orderDetail(Transaction bean) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra(GlobalBean.TRANSATION_BEAN, bean);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
        if (mSmartRefreshLayout.isRefreshing()) {
            mSmartRefreshLayout.finishRefresh();
        }
        if (mSmartRefreshLayout.isLoading()) {
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

    }

    @Override
    public void addDataToListV3(List<Transaction> data) {
        if (data == null || data.isEmpty()){
            emptyView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            emptyView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mTransationBeansV3 = data;
            mTransationsAdapter.setData(mTransationBeansV3);
        }
    }

    @Override
    public void loadMore(List<TransationBean> data) {

        mTransationsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success) {
            DialogManager.successDialog(this, msg, null);
        } else {
            DialogManager.failDialog(this, msg);
        }
    }

    @Subscriber
    public void refresh(String msg) {
        if (msg.equals(GlobalBean.REFRESH)) {
            mTransationsPresenter.getTransation(parseDate(year, month, day), parseDate(year, month, day + 1), refundCheckbox.isChecked(), true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTransationsPresenter.onDestroy();
    }

    private String parseDate(int y, int m, int d) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, y);
        calendar.set(Calendar.MONTH, m - 1);
        calendar.set(Calendar.DAY_OF_MONTH, d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return simpleDateFormat.format(date);
    }
}
