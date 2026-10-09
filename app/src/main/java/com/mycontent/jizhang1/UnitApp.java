package com.mycontent.jizhang1;

import android.app.Application;

import com.mycontent.jizhang1.db.DBManager;

//表示全局的应用的类
public class UnitApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库对象
        DBManager.initDB(getApplicationContext());
    }
}
