package com.wangly.retrofit.net;

/**
 * Created by wangly on 2016/11/8.
 */

public interface ResultCallBack {

    void  onFailure(String error);

    <T> void onComplete(T t);
}
