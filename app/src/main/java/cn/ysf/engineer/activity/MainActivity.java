package cn.ysf.engineer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.ysf.common.BaseConstant;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.view.TabView;
import cn.ysf.engineer.BuildConfig;
import cn.ysf.engineer.R;
import cn.ysf.engineer.tab.Home1Fragment;
import cn.ysf.engineer.tab.Home2Fragment;
import cn.ysf.engineer.tab.HomeFragment;
import cn.ysf.engineer.tab.MineFragment;

@Route(path = RoutePath.APP_ACTIVITY_MAIN)
public class MainActivity extends BaseActivity implements TabView.OnTabItemStateChangeListener {

    @BindView(R.id.tabViewLl)
    LinearLayout tabViewLl;

    @BindView(R.id.containerFl)
    FrameLayout containerFl;

    @BindView(R.id.homeTabView)
    TabView homeTabView;

    @BindView(R.id.home1TabView)
    TabView home1TabView;

    @BindView(R.id.home2TabView)
    TabView home2TabView;

    @BindView(R.id.mineTabView)
    TabView mineTabView;

    private final List<TabView> tabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        handleNewIntent(getIntent());
        initListener();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initView() {
        homeTabView.setFragmentClass(HomeFragment.class);
        home1TabView.setFragmentClass(Home1Fragment.class);
        home2TabView.setFragmentClass(Home2Fragment.class);
        mineTabView.setFragmentClass(MineFragment.class);

        tabs.add(homeTabView);
        tabs.add(home1TabView);
        tabs.add(home2TabView);
        tabs.add(mineTabView);
    }


    private void initListener() {
        homeTabView.setOnTabItemStateChangeListener(this);
        home1TabView.setOnTabItemStateChangeListener(this);
        home2TabView.setOnTabItemStateChangeListener(this);
        mineTabView.setOnTabItemStateChangeListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleNewIntent(intent);
    }

    @Override
    public boolean shouldChangeTabItemState(TabView tabView) {
        return !tabView.isItemStateSelected();
    }

    @Override
    public void onTabItemStateChanged(TabView tabView) {
        int index = 0;

        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i) == tabView) {
                index = i;
                break;
            }
        }
        setIndexWithoutException(index);
    }

    private void setIndexWithoutException(int index) {
        if (BuildConfig.DEBUG) {
            if (index >= tabs.size() || index < 0) {
                throw new RuntimeException("index >= tabs.size() || index < 0, current index = $index");
            }
        }
        try {
            TabView tabView = tabs.get(index);
            tabView.setItemStateSelected(!tabView.isItemStateSelected());
            setIndex(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setIndex(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < tabs.size(); i++) {
            TabView tabView = tabs.get(i);
            Class<? extends Fragment> fragmentClass = tabView.getFragmentClass();
            String tag = fragmentClass.getName();
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(tag);
            if (i == index) {
                tabView.setItemStateSelected(true);
                // 添加tab
                if (fragmentByTag == null) {
                    try {
                        fragmentTransaction.add(R.id.containerFl, fragmentClass.newInstance(), tag);
                    } catch (IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (fragmentByTag.isDetached()) {
                        fragmentTransaction.attach(fragmentByTag);
                    }
                }
            } else if (tabView.isItemStateSelected()) {
                tabView.setItemStateSelected(false);
                // 移除tab
                if (fragmentByTag != null) {
                    fragmentTransaction.detach(fragmentByTag);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void handleNewIntent(Intent intent) {
        int tabId = intent.getIntExtra(BaseConstant.KEY_INDEX, BaseConstant.INDEX_HOME);
        switchTab(tabId);
    }

    private void switchTab(int index) {
        if (isFinishing()) {
            return;
        }
        if (index == BaseConstant.INDEX_NONE) {
            throw new RuntimeException("sorry,not entry app");
        } else {
            setIndexWithoutException(index);
        }
    }
}