package com.quseit.payapp.base;


import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: BasePresenter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 15:57
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public abstract class BasePresenter {
    protected CompositeDisposable mCompositeDisposable;
    protected IView mView;

    protected void setView(IView view) {
        mView = view;
    }

    protected void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有disposable放入,集中处理
    }

    protected void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证activity结束时取消所有正在执行的订阅
        }
    }

    /**
     * 封装通用的逻辑
     */
    protected <T> void logic(Observable<T> observable, ObserverHandler<T> observerHandler) {
        logic(observable,true,observerHandler);
    }

    protected <T> void logic(Observable<T> observable, final boolean showLoading, ObserverHandler<T> observerHandler) {
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDispose(disposable);
                        if (mView != null && showLoading) {
                            mView.showLoading();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (mView != null) {
                            mView.hideLoading();
                        }
                    }
                }).subscribe(observerHandler);
    }
}
