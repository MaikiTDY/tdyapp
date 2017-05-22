package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class WarehouseFragment extends BaseFragment {

	private static WarehouseFragment fragment;
    public static WarehouseFragment getFragment(){
    	if (fragment==null) {
			fragment = new WarehouseFragment();
			return fragment;
		}
    	return null;
    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money_warehouse, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
