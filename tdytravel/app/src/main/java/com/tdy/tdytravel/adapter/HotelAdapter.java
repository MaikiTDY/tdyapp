package com.tdy.tdytravel.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.AbsMoreBaseAdapter;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.HotelBean;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

/*******************************
 * Created by tangdayi on 2018/4/25.
 * 作者:tangdayi
 * 日期:2018年04月25日21时42分
 * 文件:HotelAdapter
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class HotelAdapter extends AbsMoreBaseAdapter<HotelBean> {
    /**
     * 布局文件的ID 必须和类型一一对应  R.layout.item1 0  R.layout.itme2 1
     *
     * @param context
     * @param resid
     */
    public HotelAdapter(Context context, int... resid) {
        super(context, resid);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HotelBean data) {
        VolleyImageLoader imgLoader =VolleyImageLoader.getInstance(MyApplication.getContext());
        if(data!=null){
            NetworkImageView imageView = (NetworkImageView) viewHolder.getView(R.id.view_information_hotel_lv_item_img);
            TextView title = (TextView) viewHolder.getView(R.id.view_information_hotel_lv_item_title);
            TextView score = (TextView) viewHolder.getView(R.id.view_information_hotel_lv_item_score);
            TextView price = (TextView) viewHolder.getView(R.id.view_information_hotel_lv_item_price);
            TextView address = (TextView) viewHolder.getView(R.id.view_information_hotel_lv_item_address);

            imgLoader.loadImage(data.url, imageView);
            title.setText(data.name);
            address.setText(data.address);
            score.setText(String.valueOf(data.score));
            price.setText(data.min_price);
        }
    }
}
