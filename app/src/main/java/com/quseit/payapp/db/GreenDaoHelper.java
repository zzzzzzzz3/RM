package com.quseit.payapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 文 件 名: GreenDaoHelper
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/29 20:05
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class GreenDaoHelper {

    private static GreenDaoHelper instance = null;
    private DaoSession mDaoSession;

    private GreenDaoHelper() {

    }

    public static GreenDaoHelper getInstance() {
        if (instance == null) {
            synchronized (GreenDaoHelper.class) {
                instance = new GreenDaoHelper();
            }
        }

        return instance;
    }

    public void init(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "tt.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
