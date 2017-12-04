package com.quseit.dev;

import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

        if (e instanceof HttpException) {
            int code = ((HttpException) e).code();
            String msg = null;
            try {
                msg = ((HttpException) e).response().errorBody().string();
                msg = new Gson().fromJson(msg, HttpResponse.class).getMessage();
                onFail(code, msg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            onFail(-1, "unkonw error");
        }
    }

    @Override
    public void onComplete() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    public abstract void onResponse(@NonNull T response);

    public abstract void onFail(int code, String msg);
}
