package com.tdy.tdytravel.fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.HomeListViewAdapter;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.HomeBean;
import com.tdy.tdytravel.bean.ScenicBean;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.HttpUtil;
import com.tdy.tdytravel.uitls.JSONUtil;
import com.tdy.tdytravel.uitls.ThreadPoolUtil;
import com.tdy.tdytravel.view.HomeImageView;

import java.util.List;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    public ListView listView = null;
    public ViewPager viewPager = null;
    private HomeImageView homeImageView;
    private List<HomeBean.DetailsBean> detailsBeans;
    private HomeListViewAdapter listViewAdapter;
    private List<ScenicBean> scenicBeans;
	private static HomeFragment homeFragment;
	public static HomeFragment getFragment(){
		if (homeFragment==null) {
			homeFragment = new HomeFragment();
			return homeFragment;
		}
		return homeFragment;
	} 
	
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) view.findViewById(R.id.home_lv);
        listView.setOnItemClickListener(this);
		return view;
	}

	@Override
	protected void initData() {
        // 判断当前网络
        if(HttpUtil.isNetWorkAvailable(mContext)){
            String json = HttpUtil.sendGetByURLConn(Constants.home_head_vp_image_url);
            String scenicList = HttpUtil.readAssetsTxt(MyApplication.getContext(), "scenicJson.txt");
            final List<HomeBean> homeBeans = JSONUtil.getDetailsBeanJSONData(json);
            scenicBeans = JSONUtil.getScenicBeans(scenicList);
            ThreadPoolUtil.runTaskInUIThread(new Runnable() {
                @Override
                public void run() {
                    if(homeBeans!=null && homeBeans.size()>0) {
                        listViewAdapter = new HomeListViewAdapter(mContext, R.layout.view_home_lv_item);
                        detailsBeans = homeBeans.get(0).details;
                        homeImageView = new HomeImageView(MyApplication.getContext(), detailsBeans);
                        listView.addHeaderView(homeImageView);
                        listViewAdapter.setDatas(scenicBeans);
                        listView.setAdapter(listViewAdapter);
                    }
                }
            });

        }
		
	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
        if(scenicBeans!=null && scenicBeans.size()>0){
            ScenicBean scenicBean = scenicBeans.get(position-1);
            if (scenicBean!=null){
                MyApplication.putString(Constants.ScenicBeanAPI.image,scenicBean.image);
                MyApplication.putString(Constants.ScenicBeanAPI.p_time,scenicBean.p_time);
                MyApplication.putString(Constants.ScenicBeanAPI.p_id,scenicBean.p_id);
                MyApplication.putString(Constants.ScenicBeanAPI.p_name,scenicBean.p_name);
                MyApplication.putString(Constants.ScenicBeanAPI.p_desc,scenicBean.p_desc);
                startFragment(new DetailsScenicFragment());
            }
        }

    }
}
