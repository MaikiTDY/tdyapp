package com.tdy.tdytravel.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.MoneyFragmentPagerAdapter;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.view.PagerSlidingTabStrip;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 *
 * 消息
 */

public class InfoFragment extends BaseFragment implements OnPageChangeListener{
	public static final String[] data={"阅新闻","赏景点","吃美食","住酒店"};
	private PagerSlidingTabStrip psts;
	private ViewPager viewPager;
	private List<BaseFragment> fragmentContainer = new ArrayList<>();
	private MoneyFragmentPagerAdapter adapter;
	

	private static InfoFragment fragment;
	public static InfoFragment getFragment(){
		if (fragment==null) {
			fragment = new InfoFragment();
		    return fragment;
		}
		return fragment;
	} 
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_information, container,false);
		init(view);
		adapter = new MoneyFragmentPagerAdapter(getChildFragmentManager());
		return view;
	}
	
	@Override
	public void initFragmentState() {
		super.initFragmentState();
		fragmentContainer.add(InformationNewsFragment.getFragment());
		fragmentContainer.add(InformationScenicFragment.getFragment());
		fragmentContainer.add(InformationFoodFragment.getFragment());
		fragmentContainer.add(InformationHotelFragment.getFragment());
		
		viewPager.setOffscreenPageLimit(4);
		viewPager.setAdapter(adapter);
		adapter.setData(fragmentContainer);
		psts.setViewPager(viewPager);
		psts.setOnPageChangeListener(this);
		
	}
	
    /**
     */
	private void init(View view) {
		psts = (PagerSlidingTabStrip) view.findViewById(R.id.money_pagersliding);
		viewPager = (ViewPager) view.findViewById(R.id.money_fragmnt_pager);
		
	}
	@Override
	protected void initData() {
		
	}
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		
	}
	@Override
	public void onPageSelected(int position) {
		viewPager.setCurrentItem(position,false);
	}
	@Override
	public void onPageScrollStateChanged(int state) {
		
	}

}
