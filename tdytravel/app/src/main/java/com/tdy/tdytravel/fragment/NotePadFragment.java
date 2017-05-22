package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * @author Administrator
 *
 */
public class NotePadFragment extends BaseFragment implements OnItemClickListener{

	 private static NotePadFragment fragment;
	    public static NotePadFragment getFragment(){
	    	if (fragment==null) {
				fragment = new NotePadFragment();
				return fragment;
			}
	    	return null;
	    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money_note_pad, container, false);
		
		
		
		return view;
	}

	@Override
	protected void initData() {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

}
