package com.tdy.tdytravel.fragment;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
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
 *  欢迎页
 */
public class WelcomeFragment extends BaseFragment {

	Handler handler = new Handler();
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_welcome, container, false);
		return view;
	}

	@Override
	protected void initData() {
		
	}
	/**
	 * MainFragment
     * 定时启动 进入欢迎页
	 */
	@Override
	public void initFragmentState(){
		super.initFragmentState();
		new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startFragment(MainFragment.getMainFragment(), null);
                    }
                });
			}
		} , 3000);

	}
	
	

}
