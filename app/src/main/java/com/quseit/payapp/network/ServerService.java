package com.quseit.payapp.network;

import com.quseit.payapp.PayApplication;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sky on 2017/4/20.
 */

public class ServerService {
    private ServiceRequest request;
//    public Retrofit portRetrofit;
    public ServerService(String url) {
        request= new Retrofit.Builder()
                .client(PayApplication.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build()
                .create(ServiceRequest.class);
        //Log.e("server",url);
//        request = XApp.portRetrofit.create(ServiceRequest.class);
    }

//    /**
//     *    开启服务
//     * @param token
//     * @param email
//     * @param subscribe
//     */
//    public void openVpnService(String token, String email, XSubscribe<ProxyBean> subscribe){
//        toSubscribe(request.openVpnService(token,email),subscribe);
//    }


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
