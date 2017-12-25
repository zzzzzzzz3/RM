package com.quseit.payapp.bussiness.membership.points;

import com.quseit.payapp.bean.request.PointsRequestBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


/**
 * 文 件 名: PointsContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface PointsContract {

    interface PointsView extends IView {
        void showDialog(String msg,boolean success);
    }

    interface PointsModel extends IModel {
        Observable<ResponseBody> givePoints(PointsRequestBean bean);
    }

    interface PointsPresenter extends IPresenter {
        void givePoints(int amount,String mobile,String countryCode,String type,String memberId);
    }
}
