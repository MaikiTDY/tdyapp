package com.tdy.tdytravel.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.AbsBaseAdapter;
import com.tdy.tdytravel.base.AbsMoreBaseAdapter;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.InformationBean;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

/*******************************
 * Created by tangdayi on 2018/4/25.
 * 作者:tangdayi
 * 日期:2018年04月25日19时32分
 * 文件:InformationAdapter
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class InformationAdapter extends AbsMoreBaseAdapter<InformationBean> {


    /**
     * 布局文件的ID 必须和类型一一对应  R.layout.item1 0  R.layout.itme2 1
     *
     * @param context
     * @param resid
     */
    public InformationAdapter(Context context, int... resid) {
        super(context, resid);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, InformationBean data) {
        VolleyImageLoader imgLoader =VolleyImageLoader.getInstance(MyApplication.getContext());
        if(data!=null){
            NetworkImageView imageView = (NetworkImageView) viewHolder.getView(R.id.view_information_lv_item_img);
            TextView title = (TextView) viewHolder.getView(R.id.view_information_lv_item_title);
            TextView content = (TextView) viewHolder.getView(R.id.view_information_lv_item_content);
            imgLoader.loadImage(data.image, imageView);
            title.setText(data.p_name);
            content.setText(data.p_desc);
        }
    }
}
