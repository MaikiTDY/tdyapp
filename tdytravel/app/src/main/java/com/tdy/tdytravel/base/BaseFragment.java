package com.tdy.tdytravel.base;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdy.tdytravel.MainActivity;
import com.tdy.tdytravel.uitls.ThreadPoolUtil;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 */

public abstract class BaseFragment extends Fragment{
    private View view;
    private int fragmentId;
    //每创建一个fragment，fragmentId加一
    public BaseFragment(){
        fragmentId++;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = initView(inflater,container);
        return view;
    }
    /**
     * view的初始化
     * @param inflater
     * @param container
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);
    /**
     * 获取view
     * @return
     */
    public View getRootView(){
        return view;
    }
    /**
     * 获取Fragment的Tag标记
     * @return
     */
    public String getFragmentTag(){
        return this.getClass().getName()+fragmentId;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentState();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ThreadPoolUtil.runTaskInThread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        });
    }
    /**
     * 数据源初始化
     */
    protected abstract void initData();

    /**
     * 初始化fragment的状态
     */
    public void initFragmentState() {

    }
    /**
     * 获取Bundele
     * @param bundle
     */
    public void onGetBundle(Bundle bundle){

    }
    /**
     * 返回键处理的相关
     * @return
     */
    public boolean onBack(){
        return false;
    }

    public boolean finish(){
        return false;
    }
    /**
     * 启动fragment
     * @param fragment
     * @param bundle
     */
    public void startFragment(BaseFragment fragment,Bundle bundle){
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).startFragment(fragment,bundle);
        }
    }
}
