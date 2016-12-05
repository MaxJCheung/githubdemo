package com.cheung.linkmax.linkmaxdemo.utils;

/**
 * Created by linkmax on 2016/9/18.
 */
public abstract class IRequestCallback {

    public abstract void onGetDataSuccess(String result);

    public abstract void onGetDataError(Exception e);

}
