package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.InformationAdapter;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.InformationBean;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.HttpUtil;
import com.tdy.tdytravel.uitls.JSONUtil;
import com.tdy.tdytravel.uitls.ThreadPoolUtil;
import com.tdy.tdytravel.uitls.ToastUtil;
import com.tdy.tdytravel.view.InformationHeadView;

import java.util.List;


/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class InformationFoodFragment extends BaseFragment implements OnItemClickListener{
    
	 private ListView listView;
     private List<InformationBean> data;
	 private static InformationFoodFragment fragment;
	    public static InformationFoodFragment getFragment(){
	    	if (fragment==null) {
				fragment = new InformationFoodFragment();
				return fragment;
			}
	    	return null;
	    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_information_food, container, false);
        listView = (ListView) view.findViewById(R.id.information_foods_lv);
        listView.setOnItemClickListener(this);
        return view;
	}

	@Override
	protected void initData() {
        if(HttpUtil.isNetWorkAvailable(mContext)){
            String json = HttpUtil.readAssetsTxt(MyApplication.getContext(), "informationFoodsJson.txt");
            data = JSONUtil.getInformationBeans(json);
            ThreadPoolUtil.runTaskInUIThread(new Runnable() {
                @Override
                public void run() {
                    InformationHeadView view = new InformationHeadView(MyApplication.getContext(), data);
                    InformationAdapter adapter = new InformationAdapter(MyApplication.getContext(), R.layout.view_information_lv_item);
                    if(view!=null){
                        listView.addHeaderView(view);
                    }
                    adapter.setDatas(data);
                    listView.setAdapter(adapter);
                }
            });
        }
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
        ToastUtil.showToastInfo("我将于跳转，你准备好了吗？");
        if(data!=null && data.size()>0){
            InformationBean informationBean = data.get(position-1);
            if (informationBean!=null){
                MyApplication.putString(Constants.ScenicBeanAPI.image,informationBean.image);
                MyApplication.putString(Constants.ScenicBeanAPI.p_time,informationBean.p_time);
                MyApplication.putString(Constants.ScenicBeanAPI.p_id,informationBean.p_id);
                MyApplication.putString(Constants.ScenicBeanAPI.p_name,informationBean.p_name);
                MyApplication.putString(Constants.ScenicBeanAPI.p_desc,informationBean.p_desc);
                startFragment(new DetailsScenicFragment());
            }
        }
		
	}

}
