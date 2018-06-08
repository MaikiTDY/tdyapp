package com.tdy.tdytravel.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.db.UserDao;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.ToastUtil;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 *
 * 登录
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener{

    private EditText editUsername;
    private EditText editPassword;
    private Button loginBtn;
    private ImageView imgBackBtn;
    private Button registerBtn;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private static LoginFragment fragment;
    public static LoginFragment getFragment(){
        if (fragment==null) {
            fragment = new LoginFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        editUsername = (EditText) view.findViewById(R.id.login_fragment_username);
        editPassword = (EditText) view.findViewById(R.id.login_fragment_password);
        loginBtn = (Button) view.findViewById(R.id.login_fragment_logBtn);
        imgBackBtn = (ImageView) view.findViewById(R.id.login_fragment_goback_img);
        registerBtn = (Button) view.findViewById(R.id.login_fragment_register);
        initListener();
        return view;
	}

	@Override
	protected void initData() {
		fm = getActivity().getSupportFragmentManager();
        ft = fm.beginTransaction();
	}


    public void initListener(){
        loginBtn.setOnClickListener(this);
        imgBackBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        switch (id){
            case R.id.login_fragment_logBtn:
                if(checkLogin(username,password)){
                    MyApplication.putString(Constants.UserBeanAPI.username, username);
                    MyApplication.putInt(Constants.UserBeanAPI.imageUrl, R.mipmap.prety1);
                    ToastUtil.showToastInfo("登录成功！非常抱歉，登录跳转还没实现，?!?");

                    //startFragment(MainFragment.getMainFragment());
                    //switchFragment(MainFragment.getMainFragment());
                    //FragmentManager f = g.getFragmentManager();
                    // startFragment(MineFragment.getFragment());
                    //MainFragment.getMainFragment().switchFragment(new MineFragment());
                }
                break;
            case R.id.login_fragment_goback_img:
                ft.replace(R.id.container_main_fragment_vp,new MineFragment());
                ft.commit();
                break;
            case R.id.login_fragment_register:
                startFragment(RegisterFragment.getFragment());
                break;
            default:
                break;
        }
    }

    /**
     * 导航栏切换fragment
     * @param fragment
     */
    public void switchFragment(BaseFragment fragment){
        String fragmentTag = fragment.getFragmentTag();
        if(fragmentTag!=null){
            fm.popBackStack();
        }
        //设置fragment切换动画
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.add(R.id.main_frame_container,fragment, fragment.getFragmentTag());         //用 replace方法替换是可以的
        ft.commit();
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
}
