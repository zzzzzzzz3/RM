package com.quseit.payapp.bussiness.pay;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bussiness.description.DescriptionActivity;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.widget.NumberKeyboard;

import butterknife.BindView;
import butterknife.OnClick;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by quseitu on 2017/11/7.
 */

public class PaymentActivity extends BaseActivity {

    @BindView(R.id.keyboard_number)
    NumberKeyboard mNumberKeyboard;
    @BindView(R.id.payment_number_tv)
    TextView mPaymentTv;
    private MaterialDialog mDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        mNumberKeyboard.setOnKeyClickListener(new NumberKeyboard.OnKeyClickListener() {
            @Override
            public void onNumberClick(int number) {
                input(number + "");
            }

            @Override
            public void onDeleteClick() {
                deleteNum();
            }

            @Override
            public void onClearClick() {
                mPaymentTv.setText("0.00");
            }
        });
    }

    /**
     * 删除单个数字
     */
    private void deleteNum() {
        String str = mPaymentTv.getText().toString();
        if (!includeOther(str.toCharArray())) {
            return;
        }
        if (str.length() <= 4) {
            str = str.substring(0, str.length() - 1);
            str = "0" + str;
            str = moveDotL(str.toCharArray());
        } else {
            str = str.substring(0, str.length() - 1);
            str = moveDotL(str.toCharArray());
        }
        mPaymentTv.setText(str);
    }

    private String moveDotL(char[] list) {
        for (int i = 1, len = list.length; i < len - 1; i++) {
            if (list[i] == '.') {
                char temp = list[i];
                list[i] = list[i - 1];
                list[i - 1] = temp;
                break;
            }
        }
        return new String(list);
    }

    /**
     * ---------------------删除逻辑结束------------------------------
     * */

    /**
     * 输入数字
     */
    private void input(String s) {
        String str = mPaymentTv.getText().toString();
        if (str.length() > 12) {
            return;
        }
        if (s.equals("0")) {
            if (includeOther(str.toCharArray())) {
                str += s;
                str = moveDotR(str.toCharArray());
                if (str.charAt(0)=='0'){
                    str = str.substring(1,str.length());
                }
            }
        } else {
            int lastZeroIndex = findLastZero(str.toCharArray());
            if (lastZeroIndex == -1) {
                str += s;
                str = moveDotR(str.toCharArray());
            } else {
                str = replaceZero(str.toCharArray(), lastZeroIndex, s.charAt(0));
            }
        }
        mPaymentTv.setText(str);
    }

    private boolean includeOther(char[] list) {
        for (int i = 0, len = list.length; i < len; i++) {
            char c = list[i];
            if (c != '0' && c != '.') {
                return true;
            }
        }
        return false;
    }

    private String replaceZero(char[] chars, int index, char insert) {
        if (index == 3) {
            chars[3] = insert;
        }
        if (index == 2) {
            chars[2] = chars[3];
            chars[3] = insert;
        }
        if (index == 0) {
            chars[0] = chars[2];
            chars[2] = chars[3];
            chars[3] = insert;
        }
        return new String(chars);
    }

    private String moveDotR(char[] list) {
        for (int i = 0, len = list.length; i < len - 1; i++) {
            if (list[i] == '.') {
                char temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                break;
            }
        }
        return new String(list);
    }

    private int findLastZero(char[] list) {
        for (int i = list.length - 1; i >= 0; i--) {
            char c = list[i];
            if (c == '0') {
                return i;
            }
        }
        return -1;
    }

    /**
     * ---------------------输入逻辑结束------------------------------
     */

    @Override
    public void initData() {

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
        startActivity(new Intent(this, DescriptionActivity.class));
    }

    @OnClick(R.id.cash_icon)
    public void cash() {
        mDialog = new MaterialDialog.Builder(this)
                .content("Continue as Cash payment?")
                .positiveText("OK")
                .negativeText("CANCEL")
                .contentColor(Color.BLACK)
                .titleColor(Color.BLACK)
                .backgroundColor(Color.WHITE)
                .positiveColor(Color.parseColor("#00cc6a"))
                .negativeColor(Color.parseColor("#00cc6a"))
                .show();
        mDialog.getActionButton(DialogAction.POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                toast("ok");
            }
        });
    }

    @OnClick(R.id.scan_icon)
    public void scan() {
        PermissionUtil.requestPermissions(this, Manifest.permission.CAMERA, new PermissionUtil.RequestPermissionCallback() {
            @Override
            public void onGranted() {
                // TODO: 2017/11/9 扫描支付码
                String amount = mPaymentTv.getText().toString();
                if (Double.parseDouble(amount)<1.00){
                    toast("Minimum amount is RM 1.00");
                }else {

                }
            }

            @Override
            public void onUnGranted() {
                toast("请授予打开相机的权限！");
            }
        });
    }
}
