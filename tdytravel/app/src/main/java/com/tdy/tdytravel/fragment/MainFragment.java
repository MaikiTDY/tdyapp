package com.tdy.tdytravel.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.anim;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tdy.tdytravel.adapter.MainViewPagerAdapter;
import com.tdy.tdytravel.base.BaseFragment;
import com.tdy.tdytravel.view.MyViewPager;

import com.tdy.tdytravel.R;

/**
 * 主的Fragment
 * @author Administrator
 *
 */
public class MainFragment extends BaseFragment implements OnCheckedChangeListener{
    private static MainFragment mainFragment =null;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private List<BaseFragment> fragmentContainer = new ArrayList<BaseFragment>();

    private RadioGroup navRg;
    private RadioButton rbHome,rbMoney,rbNews,rbMine;

    private MyViewPager viewPager;
    private MainViewPagerAdapter adapter;

    public static MainFragment getMainFragment(){
        if(mainFragment == null){
            mainFragment = new MainFragment();
            return mainFragment;
        }
        return mainFragment;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);
        init(view);
        adapter = new MainViewPagerAdapter(getFragmentManager());
        return view;
    }
    /**
     * 初始化控件
     * @param view
     */
    private void init(View view) {
        viewPager = (MyViewPager) view.findViewById(R.id.container_main_fragment_vp);
        navRg = (RadioGroup) view.findViewById(R.id.rg);
        rbHome =(RadioButton) view.findViewById(R.id.rb_home);
        rbMoney =(RadioButton) view.findViewById(R.id.rb_money);
        rbNews =(RadioButton) view.findViewById(R.id.rb_news);
        rbMine =(RadioButton) view.findViewById(R.id.rb_mine);

        rbHome.setChecked(true);
    }

    /**
     * 初始化fragment的状态
     */
    @Override
    public void initFragmentState() {
        super.initFragmentState();
        //预加载3个页面
        viewPager.setOffscreenPageLimit(3);
        //初始化集合对象
        fragmentContainer = new ArrayList<BaseFragment>();
        fragmentContainer.add(HomeFragment.getFragment());
        fragmentContainer.add(MoneyFragment.getFragment());
        fragmentContainer.add(NewsFragment.getFragment());
        fragmentContainer.add(MineFragment.getFragment());
        //设置监听
        navRg.setOnCheckedChangeListener(this);
        //设置数据源
        adapter.setData(fragmentContainer);
        viewPager.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }
    /**
     * 导航栏切换fragment
     * @param fragment
     */
    public void switchFragment(BaseFragment fragment){
        if (fragment != null) {
//			fm = getActivity().getSupportFragmentManager();//获取fragment管理者
            ft = fm.beginTransaction();
            //设置fragment切换动画
            ft.setCustomAnimations(anim.fade_in, anim.fade_out);
            //	ft.replace(arg0, arg1, arg2)
            //fragment是否添加成功
//			if (!fragment.isAdded()) {
//				ft.hide(currentFragment).add(R.id.main_frame_container, fragment);
//			}else {
//				ft.hide(currentFragment).show(fragment);
//			}
//			currentFragment = fragment;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                rbHome.setChecked(true);
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.rb_money:
                viewPager.setCurrentItem(1,false);
                rbMoney.setChecked(true);
                break;
            case R.id.rb_news:
                viewPager.setCurrentItem(2,false);
                rbNews.setChecked(true);
                break;
            case R.id.rb_mine:
                viewPager.setCurrentItem(3,false);
                rbMine.setChecked(true);
                break;
            default:
                break;
        }
    }

}
