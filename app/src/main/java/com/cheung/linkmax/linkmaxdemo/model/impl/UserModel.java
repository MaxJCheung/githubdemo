package com.cheung.linkmax.linkmaxdemo.model.impl;

import com.cheung.linkmax.linkmaxdemo.bean.ReposBean;
import com.cheung.linkmax.linkmaxdemo.bean.UserBean;
import com.cheung.linkmax.linkmaxdemo.common.CommonUtils;
import com.cheung.linkmax.linkmaxdemo.common.Constant;
import com.cheung.linkmax.linkmaxdemo.model.IUserModel;
import com.cheung.linkmax.linkmaxdemo.presenter.impl.UserPresenter;
import com.cheung.linkmax.linkmaxdemo.utils.IRequestCallback;
import com.cheung.linkmax.linkmaxdemo.utils.RequestUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by linkmax on 2016/12/5.
 */

public class UserModel implements IUserModel {

    private UserPresenter userPresenter;
    private Map<String, String> params;

    public UserModel(UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
        params = new HashMap<>();
    }

    @Override
    public void doSearch(String keyWord, int page) {
        RequestUtil.Request(Constant.USER_LIST_URL + keyWord + "&page=" + page, "users", new IRequestCallback() {
            @Override
            public void onGetDataSuccess(String result) {
                UserBean userBean = new Gson().fromJson(result, UserBean.class);
                getUserRepos(userBean);
            }

            @Override
            public void onGetDataError(Exception e) {
                userPresenter.onGetUserError(e);
            }

        });
    }

    private void getUserRepos(final UserBean userBean) {
        for (int i = 0; i < userBean.getItems().size(); i++) {
            final int finalI = i;
            RequestUtil.Request(Constant.USER_REPOS_URL + userBean.getItems().get(i).getLogin() + "/repos", "repos" + userBean.getItems().get(i).getLogin(), new IRequestCallback() {
                @Override
                public void onGetDataSuccess(String result) {
                    List<ReposBean> reposList = new Gson().fromJson(result, new TypeToken<List<ReposBean>>() {
                    }.getType());
                    userBean.getItems().get(finalI).setPreference(CommonUtils.getPreference(reposList));
                    if (finalI == userBean.getItems().size() - 1) {
                        userPresenter.onGetUserSuccess(userBean);
                    }
                }

                @Override
                public void onGetDataError(Exception e) {
                    userPresenter.onGetUserError(e);
                }

            });
        }

    }


}
