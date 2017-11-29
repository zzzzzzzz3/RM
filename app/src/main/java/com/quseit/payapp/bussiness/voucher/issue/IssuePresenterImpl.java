package com.quseit.payapp.bussiness.voucher.issue;

import android.content.Context;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.pay.PrintUtil;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.response.VoucherQrcode;
import com.quseit.payapp.bean.response.VoucherResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


/**
 * 文 件 名: IssuePresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class IssuePresenterImpl extends BasePresenter implements IssueContract.IssuePresenter {

    private IssueContract.IssueView mIssueView;
    private IssueContract.IssueModel mIssueModel;
    private String cursor = "";
    private PrintUtil mPrintUtil;

    public IssuePresenterImpl(IssueContract.IssueView view) {
        mIssueView = view;
        setView(view);
        mIssueModel = new IssueModelImpl();
        mPrintUtil = new PrintUtil();
    }

    @Override
    public void onDestroy() {
        unDispose();
        mPrintUtil.logout();
    }

    @Override
    public void getVouchers() {
            cursor = "";
            loadMore();
    }

    @Override
    public void loadMore() {
        if (!cursor.equals(GlobalBean.NO_DATA)){
            logic(mIssueModel.getVouchers(cursor), false,new ObserverHandler<VoucherResponse>() {
                @Override
                public void onResponse(VoucherResponse response) {
                    if (cursor.equals("")){
                        mIssueView.setDataToList(response.getItems());
                    }else {
                        mIssueView.loadMore(response.getItems());
                    }
                    if (response.getCount()>0) {
                        cursor = response.getCursor();
                    } else {
                        cursor = GlobalBean.NO_DATA;
                    }
                }

                @Override
                public void onFail(int code) {
                    if (code== HttpCode.UNAUTHORIZED){
                        mIssueView.setUpToken();
                    }else {
                        mIssueView.showDialog("net error", false);
                    }
                }
            });
        }else {
            mView.hideLoading();
        }
    }

    @Override
    public void printQRcode(Context context, String voucherId,final String date, final int count) {
        mPrintUtil.deviceLogin(context);
        if (mPrintUtil.isLogined()){
            cursor="";
            Observable.concat(mIssueModel.getVoucherCode(voucherId),mIssueModel.getVouchers(cursor))
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            mIssueView.showLoading();
                            addDispose(disposable);
                        }
                    })
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate(new Action() {
                        @Override
                        public void run() throws Exception {
                            mIssueView.hideLoading();
                        }
                    })
                    .subscribe(new Observer<Object>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Object o) {
                            if (o instanceof VoucherQrcode){
                                VoucherQrcode voucherQrcode = (VoucherQrcode) o;
                                mPrintUtil.print(voucherQrcode.getCode(),date,count);
                            }else if (o instanceof VoucherResponse){
                                VoucherResponse voucherResponse = (VoucherResponse) o;
                                mIssueView.setDataToList(voucherResponse.getItems());
                                cursor = voucherResponse.getCursor();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e instanceof HttpException){
                                int code = ((HttpException) e).code();
                                if (code == HttpCode.UNAUTHORIZED){
                                    mIssueView.setUpToken();
                                }else {
                                    mIssueView.showDialog("net error",false);
                                }
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }
}
