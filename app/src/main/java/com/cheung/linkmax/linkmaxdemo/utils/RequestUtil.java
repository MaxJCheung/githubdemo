package com.cheung.linkmax.linkmaxdemo.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by linkmax on 2016/9/18.
 * 网络请求工具类
 */
public class RequestUtil {

    public static void Request(String url, String tag, final IRequestCallback volleyListenerInterface) {
        OkGo.getInstance().cancelTag(tag);
        OkGo.get(url)
                .tag(tag)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, okhttp3.Response response) {
                        volleyListenerInterface.onGetDataSuccess(s);
                    }

                    @Override
                    public void onError(Call call, okhttp3.Response response, Exception e) {
                        super.onError(call, response, e);
                        volleyListenerInterface.onGetDataError(e);
                    }
                });
    }

}
