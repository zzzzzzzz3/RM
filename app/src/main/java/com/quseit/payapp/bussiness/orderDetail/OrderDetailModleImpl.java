package com.quseit.payapp.bussiness.orderDetail;

import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.response.UserBean;
import com.quseit.payapp.db.GreenDaoHelper;
import com.quseit.payapp.db.UserBeanDao;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * 文 件 名: OrderDetailModleImpl
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 00:12
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class OrderDetailModleImpl extends BaseModel implements OrderDetailContract.OrderDetailModle {
    @Override
    public Observable<List<UserBean>> getUsers() {
        return Observable.create(new ObservableOnSubscribe<List<UserBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserBean>> e) throws Exception {
                UserBeanDao dao = GreenDaoHelper.getInstance().getDaoSession().getUserBeanDao();
                e.onNext(dao.queryBuilder().list());
                e.onComplete();
            }
        });
    }
}
