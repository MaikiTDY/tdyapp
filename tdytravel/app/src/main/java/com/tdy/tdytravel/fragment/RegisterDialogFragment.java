package com.tdy.tdytravel.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.tdy.tdytravel.R;
import com.tdy.tdytravel.uitls.ToastUtil;

/*******************************
 * Created by tangdayi on 2018/6/6.
 * 作者:tangdayi
 * 日期:2018年06月06日21时07分
 * 文件:LoginDialogFragment
 * 工程:tdy_app tdytravel
 * 邮箱:tangdayi520@126.com
 *******************************/
public class RegisterDialogFragment extends DialogFragment{

    private EditText mUsername;
    private EditText mPassword;
    private EditText mRePassword;

    public static RegisterDialogFragment newInstance() {
        Bundle args = new Bundle();
        RegisterDialogFragment fragment = new RegisterDialogFragment();
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
        View view = inflater.inflate(R.layout.dialog_register, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        mUsername = (EditText) view.findViewById(R.id.dialog_register_username);
        mPassword = (EditText) view.findViewById(R.id.dialog_register_password);
        mRePassword = (EditText) view.findViewById(R.id.dialog_register_repassword);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("注册",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String password = mPassword.getText().toString();
                                String repassword = mRePassword.getText().toString();
                                if (password.equals(repassword)) {
                                    RegisterDialogListener listener = MineFragment.getFragment();
                                    listener.onRegisterComplete(mUsername
                                            .getText().toString(), password);
                                } else {
                                    ToastUtil.showToastInfo("两次密码不一致，请检查，谢谢！");
                                }

                            }
                        }).setNegativeButton("取消", null);
        return builder.create();
    }


    /**
     * 登录监听
     */
    public interface RegisterDialogListener{
        // 登录完成
        void onRegisterComplete(String username, String password);
    }


}
