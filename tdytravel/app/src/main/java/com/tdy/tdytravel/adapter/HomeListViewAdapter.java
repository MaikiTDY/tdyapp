package com.tdy.tdytravel.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.AbsMoreBaseAdapter;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.HomeBean;
import com.tdy.tdytravel.bean.ScenicBean;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

/*******************************
 * Created by tangdayi on 2018/4/20.
 * 作者:tangdayi
 * 日期:2018年04月20日13时00分
 * 文件:HomeListViewAdapter
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/

public class HomeListViewAdapter extends AbsMoreBaseAdapter<ScenicBean> {


    /**
     * 布局文件的ID 必须和类型一一对应  R.layout.item1 0  R.layout.itme2 1
     *
     * @param context
     * @param resid
     */
    public HomeListViewAdapter(Context context, int... resid) {
        super(context, resid);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, ScenicBean data) {
        VolleyImageLoader imgLoader =VolleyImageLoader.getInstance(MyApplication.getContext());
        if(data!=null){
            NetworkImageView imageView = (NetworkImageView) viewHolder.getView(R.id.view_home_lv_item_img);
            TextView title = (TextView) viewHolder.getView(R.id.view_home_lv_item_title);
            TextView content = (TextView) viewHolder.getView(R.id.view_home_lv_item_content);
            imgLoader.loadImage(data.image, imageView);
            title.setText(data.p_name);
            content.setText(data.p_desc);
        }
    }
}
