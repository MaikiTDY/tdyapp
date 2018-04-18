package com.tdy.tdytravel.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewPager extends ViewPager {

	public CustomViewPager(Context context) {
		super(context);
	}

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int height = 0;
		// �����������child�ĸ߶�
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.measure(widthMeasureSpec,
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int h = child.getMeasuredHeight();
			if (h > height) { // ��������view�ĸ߶ȡ�
				height = h;
				if (listener != null)
					listener.getViewHeight(height);
			}
		}
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
				MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	private IGetViewHeightListener listener;

	public interface IGetViewHeightListener {
		void getViewHeight(int height);
	}

	public void setIGetViewHeight(IGetViewHeightListener viewHeightListener) {
		this.listener = viewHeightListener;
	}

}
