package com.tdy.tdytravel.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.adapter.HomePageAdapter;
import com.tdy.tdytravel.adapter.ImageViewPageAdapter;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.bean.HomeBean;
import com.tdy.tdytravel.bean.ImageBean;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.HttpUtil;
import com.tdy.tdytravel.uitls.VolleyImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*******************************
 * Created by tangdayi on 2018/4/18.
 * 作者:tangdayi
 * 日期:2018年04月18日22时44分
 * 文件:HomeImageView
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class HomeImageView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private NavImgLayout navDot;

    private ViewPagerAdapter adapter;
    private int currentIndex = 0;
    private ImageView leftImg;
    private ImageView rightImg;
    private  List<NetworkImageView> imgs;

    private ScheduledExecutorService scheduledExecutorService;
    private List<HomeBean.DetailsBean> data;

    public HomeImageView(Context context, List<HomeBean.DetailsBean> data) {
        super(context);
        this.data = data;
        initView();
        initDate();
        initListener();
        initEvent();
    }

    public List<HomeBean.DetailsBean> getData() {
        return data;
    }

    public void setData(List<HomeBean.DetailsBean> data) {
        this.data = data;
    }


    public void initView() {
        //View view = View.inflate(context, R.layout.fragment_home_vp_items,this,true);
        LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.view_hom_head_viewpager,this,true);

        viewPager = (ViewPager) this.findViewById(R.id.home_head_nav_viewpager);
        navDot = (NavImgLayout) this.findViewById(R.id.home_head_nav_dot);
        leftImg = (ImageView) this.findViewById(R.id.home_head_bottomleft_img);
        rightImg = (ImageView) this.findViewById(R.id.home_head_bottomright_img);

    }


    public void initDate() {
        adapter = new ViewPagerAdapter(data);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(data.size());
        navDot.setCount(data.size());

    }


    public void initListener() {
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navDot.selectByIndex(position);
        currentIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void initEvent() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(),0,4, TimeUnit.SECONDS);
    }


    // 切换当前显示的图片
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentIndex,true);// 切换当前显示的图片
            navDot.selectByIndex(currentIndex);
        }


    };

    private class ScrollTask implements Runnable {
        public void run() {
            synchronized (viewPager) {
                currentIndex = (currentIndex + 1) % imgs.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }
    }



    class ViewPagerAdapter extends PagerAdapter {

        public ViewPagerAdapter( List<HomeBean.DetailsBean> data){
            imgs = new ArrayList<>();
            if(data!=null && data.size()>0){
                VolleyImageLoader imageLoader = VolleyImageLoader.getInstance(MyApplication.getContext());
                for(int i = 0 ; i < data.size();i++){
                    NetworkImageView img = new NetworkImageView(MyApplication.getContext());
                    String imgUrl = data.get(i).pic;
                    imageLoader.loadImage(imgUrl,img);
                    img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imgs.add(img);
                }
            }
        }
        @Override
        public int getCount() {
            return imgs.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgs.get(position));
            return imgs.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgs.get(position));
        }
    }
}
