package com.tdy.tdytravel.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class HomeFragment extends BaseFragment {

    public ListView listView = null;
    public ViewPager viewPager = null;

    
	private static HomeFragment homeFragment;
	public static HomeFragment getFragment(){
		if (homeFragment==null) {
			homeFragment = new HomeFragment();
			return homeFragment;
		}
		return homeFragment;
	} 
	
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		
		
		
		
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
