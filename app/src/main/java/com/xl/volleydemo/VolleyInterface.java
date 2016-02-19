package com.xl.volleydemo;

import android.content.Context;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;


/**
 * Created by Administrator on 2/17/2016.
 */
public abstract class VolleyInterface {
    public Context mContext;
    public static Listener<String> mListener;
    public static ErrorListener mErrorListener;

    //构造抽象类，传入上下文，绑定事件监听
    public VolleyInterface(Context context, Listener<String> listener, ErrorListener errorListener) {
        this.mContext = context;
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    //在接口中
    public Listener<String> loadingListener() {
        mListener = new Listener<String>() {
            @Override
            public void onResponse(String response) {
                onMySuccess(response);//成功方法回调
                //弹出加载框
            }
        };
        return mListener;
    }
    public ErrorListener errorListener()
    {
        mErrorListener = new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onMyError(error);//失败方法回调
                //提示请求失败
            }
        };
        return  mErrorListener;
    }
    public abstract  void onMySuccess(String result); //声明回调方法
    public abstract  void onMyError(VolleyError error);
}
