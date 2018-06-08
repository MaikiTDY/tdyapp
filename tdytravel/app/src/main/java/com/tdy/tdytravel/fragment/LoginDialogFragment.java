package com.tdy.tdytravel.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tdy.tdytravel.MainActivity;
import com.tdy.tdytravel.R;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.base.MyApplication;

/*******************************
 * Created by tangdayi on 2018/6/6.
 * 作者:tangdayi
 * 日期:2018年06月06日21时07分
 * 文件:LoginDialogFragment
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class LoginDialogFragment extends DialogFragment{

    EditText mUsername;
    EditText mPassword;

    public static LoginDialogFragment newInstance() {

        Bundle args = new Bundle();
        LoginDialogFragment fragment = new LoginDialogFragment();
        args.putString("title", "标题");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);
        mUsername = (EditText) view.findViewById(R.id.id_txt_username);
        mPassword = (EditText) view.findViewById(R.id.id_txt_password);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("登录",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                LoginDialogListener listener = MineFragment.getFragment();
                                listener.onLoginComplete(mUsername
                                        .getText().toString(), mPassword
                                        .getText().toString());
                            }
                        }).setNegativeButton("取消", null);
        //builder.setTitle("用户登录");
        return builder.create();
    }


    /**
     * 登录监听
     */
    public interface LoginDialogListener{
        // 登录完成
        void onLoginComplete(String username, String password);
    }


}
