package com.tdy.tdytravel.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.uitls.Constants;


/**
 * 我的Fragment
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class MineFragment extends BaseFragment {

    private TextView username;   // 用户名
    private Button login;   // 登录按钮
    private NetworkImageView headImage;  // 登录之后的头像


    /**
     * 单列模式
     */
    private static MineFragment fragment;
    public static MineFragment getFragment(){
        if (fragment==null) {
            fragment = new MineFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        username = (TextView) view.findViewById(R.id.mine_login_name);
        login = (Button) view.findViewById(R.id.mine_login_btn);
        headImage = (NetworkImageView) view.findViewById(R.id.mine_login_img);
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        String name = MyApplication.getString(Constants.UserBeanAPI.username);
        if(name!=null && name.length()>0){
            username.setText(name);
            headImage.setDefaultImageResId(MyApplication.getInt(Constants.UserBeanAPI.imageUrl));
        }
    }

    private FragmentManager fm;
    private FragmentTransaction ft;

    /**
     * 监听初始化
     */
    public void initListener(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转登录页面
                // MainFragment.getMainFragment().switchFragment(new LoginFragment());
                startFragment(LoginFragment.getFragment());
            }
        });
    }

}
