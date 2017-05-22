package com.tdy.tdytravel.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.fragment.MoneyFragment;

import java.util.ArrayList;
import java.util.List;

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
        return MoneyFragment.data[position];
    }

}
