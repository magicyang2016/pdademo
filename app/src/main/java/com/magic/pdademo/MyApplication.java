package com.magic.pdademo;


import android.app.Application;


/**
 * create by zj on 2019/2/22
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AidlUtil.getInstance().connectPrinterService(this);
    }


}
