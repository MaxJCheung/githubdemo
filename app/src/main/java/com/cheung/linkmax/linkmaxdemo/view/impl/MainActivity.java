package com.cheung.linkmax.linkmaxdemo.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cheung.linkmax.linkmaxdemo.R;
import com.cheung.linkmax.linkmaxdemo.adapter.UserListAdapter;
import com.cheung.linkmax.linkmaxdemo.bean.UserBean;
import com.cheung.linkmax.linkmaxdemo.presenter.IUserPresenter;
import com.cheung.linkmax.linkmaxdemo.presenter.impl.UserPresenter;
import com.cheung.linkmax.linkmaxdemo.view.IMainActivity;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    @BindView(R.id.keyword_input)
    EditText keywordInput;
    @BindView(R.id.result_list)
    ListView resultList;
    @BindView(R.id.loading_process_bar)
    ProgressBar loadingProcessBar;
    @BindView(R.id.refresh_layout)
    MaterialRefreshLayout resultRefreshLayout;

    private IUserPresenter userPresenter;
    private List<UserBean.ItemsBean> dataList;
    private UserListAdapter userListAdapter;
    private String keyword = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView();
    }

    private void init() {
        userPresenter = new UserPresenter(this);
        dataList = new ArrayList<>();
        userListAdapter = new UserListAdapter(this, dataList, R.layout.item_user_list_layout);
        resultList.setAdapter(userListAdapter);
    }

    private void initView() {
        keywordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!"".equals(editable.toString())) {
                    loadingProcessBar.setVisibility(View.VISIBLE);
                    page = 1;
                    keyword = editable.toString();
                    userPresenter.doSearch(keyword, page);
                }
            }
        });
        resultRefreshLayout.setLoadMore(true);
        resultRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                page = 1;
                userPresenter.doSearch(keyword, page);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                page++;
                userPresenter.doSearch(keyword, page);
            }
        });
    }

    @Override
    public void onGetUserSuccess(UserBean userBean) {
        resultRefreshLayout.finishRefresh();
        resultRefreshLayout.finishRefreshLoadMore();
        if(page==1){
            dataList.clear();
        }
        dataList.addAll(userBean.getItems());
        userListAdapter.notifyDataSetChanged();
        loadingProcessBar.setVisibility(View.GONE);
    }

    @Override
    public void onGetUserError(Exception e) {
        resultRefreshLayout.finishRefresh();
        resultRefreshLayout.finishRefreshLoadMore();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        loadingProcessBar.setVisibility(View.GONE);
    }


}
