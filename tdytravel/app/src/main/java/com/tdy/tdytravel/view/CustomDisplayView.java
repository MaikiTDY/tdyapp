package com.tdy.tdytravel.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.HomePageAdapter;


/***
 *
 * 图片轮播 滚动
 *
 */
public class CustomDisplayView extends BaseView {
	private View view;
	private CustomViewPager disPager;
	private RadioGroup vpRadioGroup;
	private int CountPager;

	public CustomViewPager getDisPager() {
		return disPager;
	}

	public CustomDisplayView(Context context) {
		super(context);
		initView();
	}

    @Override
    public void initView() {
        view = View.inflate(mContext, R.layout.fragment_home_viewpager, this);
        disPager = (CustomViewPager) view.findViewById(R.id.home_display_viewpager);
        vpRadioGroup = (RadioGroup) view.findViewById(R.id.home_vp_radiogroup);
        initListener();
    }

    @Override
    public void initDate() {

    }

	public void initListener() {
		disPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				RadioButton radioButton = (RadioButton) vpRadioGroup
						.getChildAt(position);
				radioButton.setChecked(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		vpRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				disPager.setCurrentItem(checkedId);
			}
		});
	}

	public void setAdapter(PagerAdapter adapter) {
		if(adapter instanceof HomePageAdapter){
			HomePageAdapter homePageAdapter=(HomePageAdapter)adapter;
			CountPager=homePageAdapter.getCount();
		}
		RadioButton radioButton;
		for (int i = 0; i < CountPager; i++) {
			radioButton = new RadioButton(getContext());
			radioButton.setButtonDrawable(R.drawable.home_vp_radiogroup_selector);
			radioButton.setId(i);
			radioButton.setPadding(5, 0, 5, 0);
			vpRadioGroup.addView(radioButton);
		}
		vpRadioGroup.check(0);
		disPager.setAdapter(adapter);
		disPager.setIGetViewHeight(new CustomViewPager.IGetViewHeightListener() {
			@Override
			public void getViewHeight(int height) {
				if (listener != null)
					listener.getHeight(height);
			}
		});
		switchViewPager();
	}

	private void switchViewPager() {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				disPager.setCurrentItem((Integer) (msg.obj));
			}
		};
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg;
				int i = 0;
				boolean toLeft = true;
				while (true) {
					msg = Message.obtain();
					if (toLeft)
						msg.obj = i++;
					else {
						msg.obj = i--;
					}
					if (i == CountPager) {
						i -= 2;
						toLeft = false;
					}
					if (i == -1) {
						i += 2;
						toLeft = true;
					}
					handler.sendMessage(msg);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private onGetViewHeightListener listener;

	public interface onGetViewHeightListener {
		void getHeight(int height);
	}

	public void setOnGetViewHeightListener(onGetViewHeightListener listener) {
		this.listener = listener;
	}
}
