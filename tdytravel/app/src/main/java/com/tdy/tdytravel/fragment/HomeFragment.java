package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * @author Administrator
 *
 */
public class HomeFragment extends BaseFragment {
    
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
