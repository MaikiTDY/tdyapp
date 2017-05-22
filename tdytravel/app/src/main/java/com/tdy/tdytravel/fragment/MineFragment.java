package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;


/**
 * 我的Fragment
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class MineFragment extends BaseFragment {



    /**
     * 单列模式
     */
    private static MineFragment fragment;
    public static MineFragment getFragment(){
        if (fragment==null) {
            fragment = new MineFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);


        return view;
    }

    @Override
    protected void initData() {

    }

}
