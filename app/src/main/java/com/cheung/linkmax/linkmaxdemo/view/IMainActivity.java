package com.cheung.linkmax.linkmaxdemo.view;

import com.cheung.linkmax.linkmaxdemo.bean.UserBean;

/**
 * Created by linkmax on 2016/12/5.
 */

public interface IMainActivity {
    void onGetUserSuccess(UserBean userBean);

    void onGetUserError(Exception e);
}
