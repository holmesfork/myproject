package com.example.holmesk.cehua.utils;

import android.app.Application;

import org.xutils.x;

/**
 * 作者：holmes k
 * 时间：2017.04.10 19:25
 */


public class Myapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }


}

