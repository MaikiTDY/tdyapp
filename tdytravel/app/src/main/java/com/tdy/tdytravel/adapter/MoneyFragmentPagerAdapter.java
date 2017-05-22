package com.tdy.tdytravel.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.fragment.InfoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 *
 */
public class MoneyFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private List<BaseFragment> fragmentContainer;
    public MoneyFragmentPagerAdapter(FragmentManager fm) {
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
    /**
     * 改变viewpager的标题
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return InfoFragment.data[position];
    }

}
