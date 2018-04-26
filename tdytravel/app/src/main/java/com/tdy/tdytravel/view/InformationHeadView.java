package com.tdy.tdytravel.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.InformationBean;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

import java.util.List;

/*******************************
 * Created by tangdayi on 2018/4/24.
 * 作者:tangdayi
 * 日期:2018年04月24日22时54分
 * 文件:InformationHeadView
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class InformationHeadView extends BaseView {

    private NetworkImageView imageView;
    private TextView title;

    private List<InformationBean> beans;

    public List<InformationBean> getBeans() {
        return beans;
    }

    public void setBeans(List<InformationBean> beans) {
        this.beans = beans;
    }

    public InformationHeadView(Context context, List<InformationBean> beans) {
        super(context);
        this.beans = beans;
        initView();
        initDate();
        initListener();
    }

    @Override
    public void initView() {
        LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_information_head,this,true);
        imageView = (NetworkImageView) this.findViewById(R.id.information_new_head_img);
        title = (TextView) this.findViewById(R.id.information_new_head_title);
    }

    @Override
    public void initDate() {
        VolleyImageLoader imageLoader = VolleyImageLoader.getInstance(MyApplication.getContext());
        if (beans!=null && beans.size()>0) {
            int size = beans.size();
            imageLoader.loadImage(beans.get(size - 1).image, imageView);
            title.setText(beans.get(size - 1).p_name);
        }
    }

    @Override
    public void initListener() {

    }
}
