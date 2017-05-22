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
public class CommodityFragment extends BaseFragment {

	private static CommodityFragment fragment;
    public static CommodityFragment getFragment(){
    	if (fragment==null) {
			fragment = new CommodityFragment();
			return fragment;
		}
    	return null;
    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money_commodity, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
