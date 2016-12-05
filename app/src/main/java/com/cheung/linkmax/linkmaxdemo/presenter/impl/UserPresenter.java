package com.cheung.linkmax.linkmaxdemo.presenter.impl;

import com.cheung.linkmax.linkmaxdemo.bean.UserBean;
import com.cheung.linkmax.linkmaxdemo.model.IUserModel;
import com.cheung.linkmax.linkmaxdemo.model.impl.UserModel;
import com.cheung.linkmax.linkmaxdemo.presenter.IUserPresenter;
import com.cheung.linkmax.linkmaxdemo.view.IMainActivity;

/**
 * Created by linkmax on 2016/12/5.
 */

public class UserPresenter implements IUserPresenter {

    private IUserModel userModel;
    private IMainActivity mainActivity;

    public UserPresenter(IMainActivity mainActivity) {
        userModel = new UserModel(this);
        this.mainActivity = mainActivity;
    }


    @Override
    public void doSearch(String keyWord,int page) {
        userModel.doSearch(keyWord,page);
    }

    @Override
    public void onGetUserSuccess(UserBean userBean) {
        mainActivity.onGetUserSuccess(userBean);
    }

    @Override
    public void onGetUserError(Exception e) {
        mainActivity.onGetUserError(e);
    }

}
