package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tdy.tdytravel.base.BaseFragment;


/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class InformationScenicFragment extends BaseFragment implements OnItemClickListener{
    
	
	 private static InformationScenicFragment fragment;
	    public static InformationScenicFragment getFragment(){
	    	if (fragment==null) {
				fragment = new InformationScenicFragment();
				return fragment;
			}
	    	return null;
	    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {

		return null;
	}

	@Override
	protected void initData() {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

}
