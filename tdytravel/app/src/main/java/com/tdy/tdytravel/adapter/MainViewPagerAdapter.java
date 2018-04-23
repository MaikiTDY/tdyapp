package com.tdy.tdytravel.adapter;

import java.util.ArrayList;
import java.util.List;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdy.tdytravel.base.BaseFragment;

/**
 * fragment适配器
 * * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel
 *
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter{
    private List<BaseFragment> fragmentContainer;
    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentContainer = new ArrayList<BaseFragment>();
    }
    /**
     * 设置数据
     * @param fragmentContainer
     */
    public void setData(List<BaseFragment> fragmentContainer){
        this.fragmentContainer = fragmentContainer;
        this.notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int poistion) {
        return fragmentContainer.get(poistion);
    }

    @Override
    public int getCount() {
        return fragmentContainer.size();
    }

}
