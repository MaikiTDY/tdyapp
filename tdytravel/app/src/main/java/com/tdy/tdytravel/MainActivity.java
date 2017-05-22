package com.tdy.tdytravel;

import java.util.LinkedList;



import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.fragment.WelcomeFragment;
import com.tdy.tdytravel.uitls.ToastUtil;

public class MainActivity extends FragmentActivity {

    //定义返回键时间间隔常量
    public static final int LAST_CLICK_GAP = 600;//最后点击时间的间隔
    public static final int EXIT_TIME_GAP = 2000;//退出时间间隔
    public long lastClickTime = 0;//最后点击时间
    private long mExitTime = 0;//退出时间

    private FragmentManager fragmentManager;//fragment管理者
    //初始化fragmentTag的存放容器
    private LinkedList<String> fragmentTagContainer = new LinkedList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        fragmentManager = getSupportFragmentManager();//获取fragment管理者
        WelcomeFragment fragment = new WelcomeFragment();

        //  MainFragment fragment = MainFragment.getMainFragment();
        //获取fragment的Tag
        String fragmentTag = fragment.getFragmentTag();
        //将新创建的fragmentTag放到集合
        fragmentTagContainer.add(fragmentTag);
        //开启事务并提交
        fragmentManager.beginTransaction().add(R.id.main_frame_container, fragment, fragmentTag).addToBackStack(fragmentTag).commit();

    }

    /**
     * 返回键的处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下返回键
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            //如果返回不是当前的fragment，就默认返回
            if(!backCurrentFragment()){
                goBack();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    /**
     * 返回当前的fragment
     * @return
     */
    private boolean backCurrentFragment() {
        BaseFragment currentFragment = getCurrnetFragment();
        if(currentFragment!=null){
            //onBack()默认返回false
            return currentFragment.onBack();
        }
        return true;
    }
    /**
     * 获取当前的fragment
     * @return
     */
    private BaseFragment getCurrnetFragment() {
        if (fragmentTagContainer.size()>0) {
            //返回最后一个fragment
            return (BaseFragment) fragmentManager.findFragmentByTag(fragmentTagContainer.peekLast());
        }else {
            return null;
        }

    }

    /**
     * 默认返回
     */
    private void goBack() {
        //获取返回栈的fragment的数量
        int fragmentCount = fragmentManager.getBackStackEntryCount();
        //只存在一个fragment的时候 ，提醒在按一次退出
        if(fragmentCount==1){
            if(SystemClock.uptimeMillis()-mExitTime>EXIT_TIME_GAP){
                ToastUtil.showToastInUIThread("再按一次退出哦！");
                mExitTime = SystemClock.uptimeMillis();
            }else {
                //关闭Activity
                MainActivity.this.finish();
            }
        }else {
            //如果集合存在多个fragment
            if (fragmentTagContainer.size()>0) {
                //移除fragment
                fragmentTagContainer.pollLast();
            }
            //移除fragment
            fragmentManager.popBackStack();
        }
    }


    /**
     * 启动fragment
     */
    public void startFragment(BaseFragment fragment,Bundle bundle){
        if (fragment == null) {
            throw new IllegalArgumentException("fragment is null");
        }
        if ((lastClickTime+LAST_CLICK_GAP)<SystemClock.uptimeMillis()) {
            //获取fragment的Tag
            String fragmentTag = fragment.getFragmentTag();
            //获取事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //设置fragment的动画
            transaction.setCustomAnimations(0, 0, 0, 0);
            //添加fragment
            transaction.add(R.id.main_frame_container,fragment,fragmentTag);
            if (bundle!=null) {
                //在设置fragment的数据绑定
                fragment.setArguments(bundle);
            }
            //隐藏当前的Fragment或者finish
            BaseFragment currentFragment = getCurrnetFragment();
            if (currentFragment!=null) {
                if (currentFragment.finish()) {
                    fragmentTagContainer.pollLast();
                    fragmentManager.popBackStack();
                }else {
                    //隐藏当前fragment
                    transaction.hide(currentFragment);
                }
            }

            //添加tag
            fragmentTagContainer.add(fragmentTag);
            //添加到返回栈
            transaction.addToBackStack(fragmentTag);
            //提交事务
            transaction.commit();
            //获取最后点击时间
            lastClickTime = SystemClock.uptimeMillis();
        }
    }




}
