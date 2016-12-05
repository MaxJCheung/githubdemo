package com.cheung.linkmax.linkmaxdemo.application;

import android.app.Application;
import android.content.Context;

import com.cheung.linkmax.linkmaxdemo.common.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;

import java.util.logging.Level;

import okhttp3.internal.framed.Header;

/**
 * Created by linkmax on 2016/12/5.
 */

public class App extends Application {

    private static Context _context;

    @Override
    public void onCreate() {
        super.onCreate();
        _context = this;
        initOkGo();
    }

    public static Context getInstance() {
        return _context;
    }

    private void initOkGo() {
        OkGo.init(this);
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent", "GitHubDemoApp");
        headers.put("Authorization", "token "+ Constant.GITHUB_TOKEN);
        try {
            OkGo.getInstance()
                    .debug("OkGo", Level.INFO, true)
                    .setCacheMode(CacheMode.NO_CACHE)
                    .setRetryCount(3)
                    .setCertificates()
            .addCommonHeaders(headers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
