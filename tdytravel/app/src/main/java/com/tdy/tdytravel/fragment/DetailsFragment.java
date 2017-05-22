package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class DetailsFragment extends BaseFragment implements OnItemClickListener{
    private GridView gridView;
    
    private static DetailsFragment fragment;
    public static DetailsFragment getFragment(){
    	if (fragment==null) {
			fragment = new DetailsFragment();
			return fragment;
		}
    	return null;
    }
    
    
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money_inconme, container,false);
		gridView = (GridView) view.findViewById(R.id.money_income_gv);
		
		
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
