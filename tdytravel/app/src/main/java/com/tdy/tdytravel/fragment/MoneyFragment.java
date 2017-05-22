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
 * @author Administrator
 *
 */
public class MoneyFragment extends BaseFragment implements OnPageChangeListener{
	public static final String[] data={"�������","֧������","��ǩ����","�˻�����","�ֿ����","��Ʒ����"};
	private PagerSlidingTabStrip psts;
	private ViewPager viewPager;
	private List<BaseFragment> fragmentContainer = new ArrayList<BaseFragment>();
	private MoneyFragmentPagerAdapter adapter;
	

	private static MoneyFragment fragment;
	public static MoneyFragment getFragment(){
		if (fragment==null) {
			fragment = new MoneyFragment();
		    return fragment;
		}
		return fragment;
	} 
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_money, container,false);
		init(view);
		adapter = new MoneyFragmentPagerAdapter(getChildFragmentManager());
		return view;
	}
	
	@Override
	public void initFragmentState() {
		super.initFragmentState();
		fragmentContainer.add(IncomeFragment.getFragment());
		fragmentContainer.add(PayFragment.getFragment());
		fragmentContainer.add(NotePadFragment.getFragment());
		fragmentContainer.add(AccountFragment.getFragment());
		fragmentContainer.add(WarehouseFragment.getFragment());
		fragmentContainer.add(CommodityFragment.getFragment());
		
		viewPager.setOffscreenPageLimit(5);
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
