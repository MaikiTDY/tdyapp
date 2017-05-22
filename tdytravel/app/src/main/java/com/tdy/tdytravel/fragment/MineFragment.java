package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;


/**
 * 我的Fragment
 * @author Administrator
 *
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
