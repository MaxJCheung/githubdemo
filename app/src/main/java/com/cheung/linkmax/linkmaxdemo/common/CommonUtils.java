package com.cheung.linkmax.linkmaxdemo.common;

import com.cheung.linkmax.linkmaxdemo.bean.ReposBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linkmax on 2016/12/5.
 */

public class CommonUtils {

    //获取用户喜好语言
    public static String getPreference(List<ReposBean> reposList) {
        Map<String, Integer> countMap = new HashMap<>();
        for (ReposBean reposBean : reposList) {
            if (null == countMap.get(reposBean.getLanguage())) {
                countMap.put(reposBean.getLanguage(), 1);
            } else {
                countMap.put(reposBean.getLanguage(), countMap.get(reposBean.getLanguage()) + 1);
            }

        }
        Map<String, Integer> newMap = sortMap(countMap);
        if ( newMap.entrySet().iterator().hasNext()) {
            return newMap.entrySet().iterator().next().getKey();
        }
        return "";
    }

    //map按value排序
    private static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg1.getValue() - arg0.getValue();
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }


}
