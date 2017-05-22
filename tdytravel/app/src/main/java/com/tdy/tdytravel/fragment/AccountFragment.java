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
public class AccountFragment extends BaseFragment {
    
	private static AccountFragment fragment;
    public static AccountFragment getFragment(){
    	if (fragment==null) {
			fragment = new AccountFragment();
			return fragment;
		}
    	return null;
    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money_account, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}

}
