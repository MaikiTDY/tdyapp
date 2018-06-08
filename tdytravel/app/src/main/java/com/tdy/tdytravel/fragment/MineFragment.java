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
import com.tdy.tdytravel.db.UserDao;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.ToastUtil;


/**
 * 我的Fragment
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel * *
 */
public class MineFragment extends BaseFragment implements LoginDialogFragment.LoginDialogListener, RegisterDialogFragment.RegisterDialogListener {

    private TextView usernameTv;   // 用户名
    private Button login;   // 登录按钮
    private Button register;  // 注册按钮
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
        usernameTv = (TextView) view.findViewById(R.id.mine_login_name);
        login = (Button) view.findViewById(R.id.mine_login_btn);
        register = (Button) view.findViewById(R.id.mine_register_btn);
        headImage = (NetworkImageView) view.findViewById(R.id.mine_login_img);
        initListener();
        return view;
    }

    @Override
    protected void initData() {
        String name = MyApplication.getString(Constants.UserBeanAPI.username);
        if(name!=null && name.length()>0){
            //usernameTv.setText(name);
            headImage.setDefaultImageResId(MyApplication.getInt(Constants.UserBeanAPI.imageUrl));
        }
    }

    private FragmentManager fm;
    private FragmentTransaction ft;

    /**
     * 监听初始化
     */
    public void initListener(){
        // 登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转登录页面
                // MainFragment.getMainFragment().switchFragment(new LoginFragment());
                //startFragment(LoginFragment.getFragment());
                showLoginDialog(v);

            }
        });
        // 注册
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog(v);

            }
        });
    }


    public void showLoginDialog(View view)
    {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "登录");
    }

    public void showRegisterDialog(View view){
        RegisterDialogFragment registerDialogFragment = new RegisterDialogFragment();
        registerDialogFragment.show(getFragmentManager(), "注册");
    }

    /**
     * 登录
     * @param username
     * @param password
     */
    @Override
    public void onLoginComplete(String username, String password) {
        if(checkLogin(username,password)){  // 登录成功
            usernameTv.setText(username);
            ToastUtil.showToastInfo("帐号：" + username + ",  密码 :" + password);
            ToastUtil.showToastInfo("登录成功！");
            login.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            headImage.setDefaultImageResId(MyApplication.getInt(Constants.UserBeanAPI.imageUrl));
            headImage.setVisibility(View.VISIBLE);

        }else {
            ToastUtil.showToastInfo("用户不存在，请注册，谢谢！");
        }
    }


    /***
     * 检查登录
     * @param username
     * @param password
     */
    public boolean checkLogin(String username, String password) {
        // 管理源直接登录
        if ("admin".equals(username) && "admin".equals(password)) {

            return true;
        }
        if (username != null && password != null) {
            boolean flag = UserDao.checkLogin(MyApplication.getContext(), username, password);
            if (flag) {
                return flag;
            } else {
                ToastUtil.showToastInfo("用户名或密码不对，请重新输入，谢谢！");
            }

        } else {
            ToastUtil.showToastInfo("用户名或密码不能为空！");
        }
        return false;
    }

    /***
     * 注册
     * @param username
     * @param password
     */
    @Override
    public void onRegisterComplete(String username, String password) {
        if(UserDao.addUser(MyApplication.getContext(), username, password)){
            ToastUtil.showToastInfo("恭喜你，注册成功！");
            ToastUtil.showToastInfo("注册帐号：" + username + ",  密码 :" + password);
        }else {
            ToastUtil.showToastInfo("很遗憾，注册失败，请检查！");
        }

    }
}
