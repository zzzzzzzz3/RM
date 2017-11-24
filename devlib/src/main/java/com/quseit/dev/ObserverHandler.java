package com.quseit.dev;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 文 件 名: ObserverHandler
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 09:54
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public abstract class ObserverHandler<T> implements Observer<T> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(@NonNull T response) {
        onResponse(response);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        int code = -1;
        if (e instanceof HttpException){
            code = ((HttpException) e).code();
        }
        onFail(code);
    }

    @Override
    public void onComplete() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public abstract void onResponse(@NonNull T response);

    public abstract void onFail(int code);
}
