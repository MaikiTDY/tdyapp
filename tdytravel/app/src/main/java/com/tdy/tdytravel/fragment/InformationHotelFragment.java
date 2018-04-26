package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.HotelAdapter;
import com.tdy.tdytravel.adapter.InformationAdapter;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.HotelBean;
import com.tdy.tdytravel.bean.InformationBean;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.HttpUtil;
import com.tdy.tdytravel.uitls.JSONUtil;
import com.tdy.tdytravel.uitls.ThreadPoolUtil;
import com.tdy.tdytravel.uitls.ToastUtil;
import com.tdy.tdytravel.view.HotelHeadView;
import com.tdy.tdytravel.view.InformationHeadView;

import java.util.List;


/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class InformationHotelFragment extends BaseFragment implements OnItemClickListener{
    
     ListView listView;
	 private static InformationHotelFragment fragment;
	    public static InformationHotelFragment getFragment(){
	    	if (fragment==null) {
				fragment = new InformationHotelFragment();
				return fragment;
			}
	    	return null;
	    }
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_information_hotle, container, false);
        listView = (ListView) view.findViewById(R.id.information_hotel_lv);
        listView.setOnItemClickListener(this);
        return view;
	}

	@Override
	protected void initData() {
        if(HttpUtil.isNetWorkAvailable(mContext)){
            String json = HttpUtil.sendGetByURLConn(Constants.hotle_url);
            final List<HotelBean> data = JSONUtil.getHotelBeans(json);

            ThreadPoolUtil.runTaskInUIThread(new Runnable() {
                @Override
                public void run() {
                    HotelHeadView view = new HotelHeadView(MyApplication.getContext(), data);
                    HotelAdapter adapter = new HotelAdapter(MyApplication.getContext(), R.layout.view_information_hotel_lv_item);
                    if (view != null) {
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
        ToastUtil.showToastInfo("请不要点我，我还没有详情页！😊");
	}

}
