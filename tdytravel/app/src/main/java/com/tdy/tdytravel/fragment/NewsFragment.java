package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * Fragment
 * @author Administrator
 *
 */
public class NewsFragment extends BaseFragment {

	private static NewsFragment fragment;
	public static NewsFragment getFragment(){
		if (fragment==null) {
			fragment = new NewsFragment();
		    return fragment;
		}
		return fragment;
	} 
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_news, container, false);
		
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
