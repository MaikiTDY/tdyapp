package com.tdy.tdytravel.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;
import com.tdy.tdytravel.db.DBHelpUtils;
import com.tdy.tdytravel.db.UserDao;
import com.tdy.tdytravel.uitls.Constants;
import com.tdy.tdytravel.uitls.ToastUtil;

/**
 * Created by tangdayi on 2017/5/20.
 * 作者:tangdayi
 * 日期:2017年05月20日10时02分
 * 工程:tdytravel *
 *
 * 注册
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener{

    private EditText editUsername;
    private EditText editPassword, comfirmPassword;

    private ImageView imgBackBtn;
    private Button submitBtn,cancelBtn;
    private static RegisterFragment fragment;
    public static RegisterFragment getFragment(){
        if (fragment==null) {
            fragment = new RegisterFragment();
            return fragment;
        }
        return fragment;
    }

    @Override
	public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editUsername = (EditText) view.findViewById(R.id.register_username_et);
        editPassword = (EditText) view.findViewById(R.id.register_new_pwd_et);
        comfirmPassword = (EditText) view.findViewById(R.id.et_register_confirm_pwd);
        imgBackBtn = (ImageView) view.findViewById(R.id.register_fragment_goback_img);
        submitBtn = (Button) view.findViewById(R.id.register_submit_btn);
        cancelBtn = (Button) view.findViewById(R.id.register_cancel_btn);
        initListener();
		return view;
	}

    private void initListener() {
        imgBackBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
	protected void initData() {
		
	}

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String cPassword = comfirmPassword.getText().toString();
        switch (id){
            case R.id.register_fragment_goback_img:
                startFragment(LoginFragment.getFragment());
                break;
            case R.id.register_submit_btn:
                if(username!=null&&password!=null&&cPassword!=null){
                    if(!password.equals(cPassword)){
                        ToastUtil.showToastInfo("两次密码输入不一样，请检查，谢谢！");
                        return;
                    }
                    if(UserDao.addUser(MyApplication.getContext(), username, password)){
                        ToastUtil.showToastInfo("恭喜你，注册成功！");
                        ToastUtil.showToastInfo("非常抱歉，现在跳转功能开发中，请耐心等待！");
                        //startFragment(LoginFragment.getFragment());
                        //MainFragment.getMainFragment().switchFragment(new LoginFragment());
                    }
                }
                break;
            case R.id.register_cancel_btn:
                //startFragment(LoginFragment.getFragment());
                break;
            default:
                break;
        }
    }
}
