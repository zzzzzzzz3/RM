package com.quseit.payapp.network;

import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.quseit.payapp.PayApplication;
import com.quseit.payapp.R;
import com.quseit.payapp.bean.SimpleResponse;

import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by sky on 2017/3/20.
 */

public class XSubscribe  <T extends SimpleResponse> extends Subscriber<T>{
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException | e instanceof HttpException) {
            if (e.toString().equals("queryserver faild")){
                Toast.makeText(PayApplication.getContext(),e.toString() , Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(PayApplication.getContext(), R.string.err_net, Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(XApp.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Logger.e("MySubscribe Error" + "++++" + e.toString());
        }
    }

    @Override
    public void onNext(T t) {
//        if ("1".equals(t.getErrno())) {
//
//            onError(new Throwable(t.getMsg()));
//            return;
//        }
    }
}
