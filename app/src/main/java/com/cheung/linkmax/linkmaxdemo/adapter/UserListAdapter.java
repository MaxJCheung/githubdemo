package com.cheung.linkmax.linkmaxdemo.adapter;

import android.content.Context;

import com.cheung.linkmax.linkmaxdemo.R;
import com.cheung.linkmax.linkmaxdemo.bean.UserBean;
import com.cheung.linkmax.linkmaxdemo.widget.CommonAdapter;
import com.cheung.linkmax.linkmaxdemo.widget.ViewHolder;

import java.util.List;

/**
 * Created by linkmax on 2016/12/5.
 */

public class UserListAdapter extends CommonAdapter<UserBean.ItemsBean> {

    private Context mContext;

    public UserListAdapter(Context context, List<UserBean.ItemsBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        this.mContext = context;
    }

    @Override
    public void convert(ViewHolder helper, UserBean.ItemsBean item) {
        helper.setImageByUrl(mContext, R.id.user_avatar, item.getAvatar_url());
        helper.setText(R.id.user_usn, item.getLogin());
        helper.setText(R.id.language_preference, item.getPreference());
    }
}
