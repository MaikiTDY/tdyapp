package com.tdy.tdytravel.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.tdy.tdytravel.bean.HomeBean;
import com.tdy.tdytravel.bean.ImageBean;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

import java.util.List;

/*******************************
 * Created by tangdayi on 2018/4/18.
 * 作者:tangdayi
 * 日期:2018年04月18日23时13分
 * 文件:ImageViewPageAdapter
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/

public class ImageViewPageAdapter  extends StaticPagerAdapter {

    private List<HomeBean.DetailsBean> list;

    public List<HomeBean.DetailsBean> getList() {
        return list;
    }

    public void setList(List<HomeBean.DetailsBean> list) {
        this.list = list;
    }

    public ImageViewPageAdapter(List<HomeBean.DetailsBean> list){
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        VolleyImageLoader imageLoader = VolleyImageLoader.getInstance(container.getContext());
        NetworkImageView view = new NetworkImageView(container.getContext());
        view.setScaleType(NetworkImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        HomeBean.DetailsBean bean = list.get(position);
        imageLoader.loadImage(bean.pic, view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
