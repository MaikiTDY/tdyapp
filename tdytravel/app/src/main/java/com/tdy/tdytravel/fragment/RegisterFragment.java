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
 * 工程:tdytravel *
 *
 * 注册
 */
public class RegisterFragment extends BaseFragment {


    private static RegisterFragment fragment;
    public static RegisterFragment getFragment(){
        if (fragment==null) {
            fragment = new RegisterFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
