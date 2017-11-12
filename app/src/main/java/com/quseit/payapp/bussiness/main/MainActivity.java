package com.quseit.payapp.bussiness.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.MainFragmentAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.bussiness.pay.PaymentActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.RMAutoDialog;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMEditDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.simple.eventbus.Subscriber;

import java.util.Calendar;

import butterknife.BindView;

/**
 * 文 件 名: MainActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 12:34
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    @BindView(R.id.dots_layout)
    LinearLayout mDotsLayout;
    //ViewPager页面数
    private static final int len = 2;

    @Override
    public int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setUpViewpager();
        setupDotLayout();
    }

    private void setUpViewpager() {
        MainFragmentAdapter mAdapter = new MainFragmentAdapter(getSupportFragmentManager(),len);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < len; i++) {
                    ImageView image = (ImageView) mDotsLayout.getChildAt(i);
                    if (position == i) {
                        image.setSelected(true);
                    } else {
                        image.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDotLayout() {
        for (int i = 0; i < len; i++) {
            ImageView image = newDotImage();
            if (i == 0) {
                image.setSelected(true);
            }
            mDotsLayout.addView(image);
        }
    }

    private ImageView newDotImage() {
        ImageView image = new ImageView(this);
        int size = UIUtil.dp2Px(this,10);
        int margin = UIUtil.dp2Px(this,5);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size, size);
        lp.setMargins(margin,margin, margin, margin);
        image.setLayoutParams(lp);
        image.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dot_selected_selector));
        return image;
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Subscriber
    public void onItemClick(ItemBean itemBean) {
        switch (itemBean.itemName) {
            case GlobalBean.PAYMENT:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            case GlobalBean.VOUCHER:
                comfirmDialog();
                break;
            case GlobalBean.MEMBERSHIP:
                DialogManager.rmEditDialog(this, "SEARCH", "input...", new RMEditDialog.OnPositiveClickListener() {
                    @Override
                    public void onClick(String editStr) {
                        toast(editStr);
                    }
                });
                break;
            case GlobalBean.HISTORY:
                DialogManager.rmAutoDialog(this,"error", RMAutoDialog.TYPE.FAILED);
                break;
            case GlobalBean.APP_STORE:
                final RMProgressDialog dialog = DialogManager.rmProgressDialog(this,"loading...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                },3000);
                break;
            case GlobalBean.SETTING:

                break;
            case GlobalBean.ORDERS:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.setAccentColor(ContextCompat.getColor(this,R.color.colorAccent));
                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
            case GlobalBean.SUPPORT:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            case GlobalBean.NOTIFICATION:
                startActivity(new Intent(this, PaymentActivity.class));
                break;

        }
    }

    public void comfirmDialog(){
        DialogManager.rmDialog(this, "Do you love me?",R.mipmap.place_holder_icon_black, new RMDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {
                toast("yes");
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        toast(year+"-"+monthOfYear+"-"+dayOfMonth);
    }
}
