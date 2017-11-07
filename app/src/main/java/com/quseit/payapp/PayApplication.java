package com.quseit.payapp;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.quseit.payapp.network.ServerService;
import com.quseit.payapp.network.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by quseitu on 2017/11/7.
 */

public class PayApplication extends Application{
    public static Context mContext;
    public static Retrofit mRetrofit;
    public static OkHttpClient mOkHttpClient;
    public static HttpLoggingInterceptor mInterceptor;
    public static Service mService;
    public static ServerService mServerService;
    public static Gson mGson;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder().addInterceptor(mInterceptor).build();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.SERVERADDRESS)
                .build();
        mService=new Service();
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
    public static Context getContext(){
        return mContext;
    }

    public static Retrofit getRetrofit(){
        return mRetrofit;
    }

    public static Service getService(){
        return mService;
    }

    public static Gson getGson(){
        return mGson;
    }
}
