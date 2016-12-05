package com.cheung.linkmax.linkmaxdemo.presenter;

import com.cheung.linkmax.linkmaxdemo.bean.UserBean;

/**
 * Created by linkmax on 2016/12/5.
 */

public interface IUserPresenter {
    void doSearch(String keyWord,int page);

    void onGetUserSuccess(UserBean userBean);

    void onGetUserError(Exception e);
}
