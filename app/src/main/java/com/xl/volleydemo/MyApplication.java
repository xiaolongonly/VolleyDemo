package com.xl.volleydemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2/17/2016.
 */
public class MyApplication extends Application {
    public static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());//获得全局请求队列
    }
    public static RequestQueue getHttpQueues(){
        return queues;//全局请求队列
    }
}
