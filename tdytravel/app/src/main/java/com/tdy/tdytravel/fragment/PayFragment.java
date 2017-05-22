package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tdy.tdytravel.base.BaseFragment;


/**
 * @author Administrator
 *
 */
public class PayFragment extends BaseFragment implements OnItemClickListener{
    
	
	 private static PayFragment fragment;
	    public static PayFragment getFragment(){
	    	if (fragment==null) {
				fragment = new PayFragment();
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
