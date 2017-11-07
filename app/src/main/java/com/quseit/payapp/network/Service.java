package com.quseit.payapp.network;

import com.quseit.payapp.PayApplication;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 2017/3/20.
 */

public class Service {
    private ServiceRequest request;

    public Service() {
        request = PayApplication.getRetrofit().create(ServiceRequest.class);
    }


//    /**         登陆
//     * @param email  邮箱
//     * @param pwd 密码
//     * @param subscribe
//     */
//        public void login(String email, String pwd, XSubscribe<LoginBean> subscribe){
//            toSubscribe(request.login(email,pwd),subscribe);
//        }



    /**
     * retrofit 线程管理
     */
    private static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
