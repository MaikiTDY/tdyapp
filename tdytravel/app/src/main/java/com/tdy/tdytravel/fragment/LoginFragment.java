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
 * 登录
 */
public class LoginFragment extends BaseFragment {


    private static LoginFragment fragment;
    public static LoginFragment getFragment(){
        if (fragment==null) {
            fragment = new LoginFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
