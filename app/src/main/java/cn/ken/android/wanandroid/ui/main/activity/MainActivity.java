package cn.ken.android.wanandroid.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.base.BaseActivity;
import cn.ken.android.wanandroid.ui.guide.fragment.GuideFragment;
import cn.ken.android.wanandroid.ui.hierarchy.fragment.HierarchyFragment;
import cn.ken.android.wanandroid.ui.home.fragment.HomeFragment;
import cn.ken.android.wanandroid.ui.project.fragment.ProjectFragment;
import cn.ken.android.wanandroid.ui.wx.fragment.WeChatFragment;
import cn.ken.android.wanandroid.utils.BottomNavigationViewHelper;
import cn.ken.android.wanandroid.utils.ToastUtils;
import cn.ken.android.wanandroid.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private BottomNavigationView mBottomNavigationView;
    private TextView mUsTv;
    private TextView mToolbarTitle;

    private List<Fragment> fragments;
    private HomeFragment mHomeFragment;
    private HierarchyFragment mHierarchyFragment;
    private WeChatFragment mWeChatFragment;
    private GuideFragment mGuideFragment;
    private ProjectFragment mProjectFragment;
    private Bundle bundle;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        init();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        if (outState != null) {
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            if (mHomeFragment != null) {
//                transaction.hide(mHomeFragment);
//            }
//            if (mHierarchyFragment != null) {
//                transaction.hide(mHierarchyFragment);
//            }
//            if (mWeChatFragment != null) {
//                transaction.hide(mWeChatFragment);
//            }
//            if (mGuideFragment != null) {
//                transaction.hide(mGuideFragment);
//            }
//            if (mProjectFragment != null) {
//                transaction.hide(mProjectFragment);
//            }
//            transaction.commit();
//        }
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        if (savedInstanceState != null) {
//            //隐藏碎片 避免重叠
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            if (mHomeFragment != null) {
//                transaction.hide(mHomeFragment);
//            }
//            if (mHierarchyFragment != null) {
//                transaction.hide(mHierarchyFragment);
//            }
//            if (mWeChatFragment != null) {
//                transaction.hide(mWeChatFragment);
//            }
//            if (mGuideFragment != null) {
//                transaction.hide(mGuideFragment);
//            }
//            if (mProjectFragment != null) {
//                transaction.hide(mProjectFragment);
//            }
//            transaction.commit();
//            setTabSelection(0);
//        }
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bundle != null) {
            setTabSelection(0);
        }
    }

    private void init() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mUsTv = (TextView) findViewById(R.id.nav_header_user_tv);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mToolbarTitle = (TextView) findViewById(R.id.common_toolbar_title_tv);
//        initFragment();
        initToolbar();
        initDrawerToggle();
        initNavigationView();
        initBottomNavigationView();
        initDrawerLayout();
        setTabSelection(0);
    }

//    private void initFragment() {
//        fragments = new ArrayList<>();
//        mHomeFragment = HomeFragment.newInstance();
//        mHierarchyFragment = HierarchyFragment.newInstance();
//        mWeChatFragment = WeChatFragment.newInstance();
//        mGuideFragment = GuideFragment.newInstance();
//        mProjectFragment = ProjectFragment.newInstance();
//        fragments.add(mHomeFragment);
//        fragments.add(mHierarchyFragment);
//        fragments.add(mWeChatFragment);
//        fragments.add(mGuideFragment);
//        fragments.add(mProjectFragment);
//    }

    private void initToolbar() {
        // 替换ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        mToolbarTitle.setText(getResources().getString(R.string.app_name));
    }

    private void initDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    private void initNavigationView() {
        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_user_tv);
//        if (mPresenter.getLoginStatus()) {
//            showLoginView();
//        } else {
//            showLogoutView();
//        }
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main_home:
                        mToolbarTitle.setText(getResources().getString(R.string.app_name));
                        setTabSelection(0);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        mToolbarTitle.setText(getResources().getString(R.string.bottom_nav_item_knowledge));
                        setTabSelection(1);
                        break;
                    case R.id.tab_wx_article:
                        mToolbarTitle.setText(getResources().getString(R.string.bottom_nav_item_wx));
                        setTabSelection(2);
                        break;
                    case R.id.tab_navigation:
                        mToolbarTitle.setText(getResources().getString(R.string.bottom_nav_item_guide));
                        setTabSelection(3);
                        break;
                    case R.id.tab_project:
                        mToolbarTitle.setText(getResources().getString(R.string.bottom_nav_item_project));
                        setTabSelection(4);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void setTabSelection(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏碎片
        hideFragment(transaction);
        switch (index) {
            case 0:
                //判断碎片是否为空 以免重复建立 影响性能
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    transaction.add(R.id.relative_frame_layout, mHomeFragment);
                    transaction.show(mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                //判断碎片是否为空 以免重复建立 影响性能
                if (mHierarchyFragment == null) {
                    mHierarchyFragment = HierarchyFragment.newInstance();
                    transaction.add(R.id.relative_frame_layout, mHierarchyFragment);
                    transaction.show(mHierarchyFragment);
                } else {
                    transaction.show(mHierarchyFragment);
                }
                break;
            case 2:
                //判断碎片是否为空 以免重复建立 影响性能
                if (mWeChatFragment == null) {
                    mWeChatFragment = WeChatFragment.newInstance();
                    transaction.add(R.id.relative_frame_layout, mWeChatFragment);
                    transaction.show(mWeChatFragment);
                } else {
                    transaction.show(mWeChatFragment);
                }
                break;
            case 3:
                //判断碎片是否为空 以免重复建立 影响性能
                if (mGuideFragment == null) {
                    mGuideFragment = GuideFragment.newInstance();
                    transaction.add(R.id.relative_frame_layout, mGuideFragment);
                    transaction.show(mGuideFragment);
                } else {
                    transaction.show(mGuideFragment);
                }
                break;
            case 4:
                //判断碎片是否为空 以免重复建立 影响性能
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.newInstance();
                    transaction.add(R.id.relative_frame_layout, mProjectFragment);
                    transaction.show(mProjectFragment);
                } else {
                    transaction.show(mProjectFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        //隐藏碎片 避免重叠
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mHierarchyFragment != null) {
            transaction.hide(mHierarchyFragment);
        }
        if (mWeChatFragment != null) {
            transaction.hide(mWeChatFragment);
        }
        if (mGuideFragment != null) {
            transaction.hide(mGuideFragment);
        }
        if (mProjectFragment != null) {
            transaction.hide(mProjectFragment);
        }
    }

    private void initDrawerLayout() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_collection:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(0);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_item_task:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(1);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_item_moonn:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(2);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_item_settings:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(3);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_item_about:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(4);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_item_exit:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(5);
                mDrawerLayout.closeDrawers();
                break;
        }
        return true;
    }

    private void setNavigationViewChecked(int position) {
        mNavigationView.getMenu().getItem(position).setChecked(true);
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            if (i != position) {
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                ToastUtils.showToast(this, "搜索");
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

}
