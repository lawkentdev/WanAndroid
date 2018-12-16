package cn.ken.android.wanandroid;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import cn.ken.android.wanandroid.fragment.GuideFragment;
import cn.ken.android.wanandroid.fragment.HomeFragment;
import cn.ken.android.wanandroid.fragment.MoreFragment;
import cn.ken.android.wanandroid.fragment.ProjectFragment;
import cn.ken.android.wanandroid.fragment.WeChatFragment;
import cn.ken.android.wanandroid.utils.ToastUtils;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private BottomNavigationBar mBottomNavigationBar;

    private HomeFragment mHomeFragment;
    private MoreFragment mMoreFragment;
    private WeChatFragment mWeChatFragment;
    private GuideFragment mGuideFragment;
    private ProjectFragment mProjectFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // 初始化控件
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_nav_bar);

        // 替换ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_layout_open, R.string.drawer_layout_close);
        mDrawerToggle.syncState();
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setStatusBarBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(this);
        // 设置NavigationView文字颜色
        mNavigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.drawer_navigation_text));
        // 设置NavigationView图标颜色
        mNavigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.drawer_navigation_icont));
        // 监听事件
        mNavigationView.setNavigationItemSelectedListener(this);

        // 底部导航栏
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_seleted, getString(R.string.bottom_item_title_home)).setInactiveIconResource(R.drawable.ic_home).setActiveColorResource(R.color.black__bottom_item).setInActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.drawable.ic_more_selected, getString(R.string.bottom_item_title_more)).setInactiveIconResource(R.drawable.ic_more).setActiveColorResource(R.color.black__bottom_item).setInActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.drawable.ic_wechat_selected, getString(R.string.bottom_item_title_wechat)).setInactiveIconResource(R.drawable.ic_wechat).setActiveColorResource(R.color.black__bottom_item).setInActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.drawable.ic_guide_selected, getString(R.string.bottom_item_title_guide)).setInactiveIconResource(R.drawable.ic_guide).setActiveColorResource(R.color.black__bottom_item).setInActiveColorResource(R.color.black))
                .addItem(new BottomNavigationItem(R.drawable.ic_project_selected, getString(R.string.bottom_item_title_project)).setInactiveIconResource(R.drawable.ic_project).setActiveColorResource(R.color.black__bottom_item).setInActiveColorResource(R.color.black))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    private void setNavigationViewChecked(int position) {
        mNavigationView.getMenu().getItem(position).setChecked(true);
        Log.i("Kevin", "the count of menu item is--->" + mNavigationView.getMenu().size() + "");
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            if (i != position) {
                mNavigationView.getMenu().getItem(i).setChecked(false);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_collection:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(0);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_task:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(1);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_moonn:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(2);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_settings:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(3);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_about:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(4);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.nav_exit:
                ToastUtils.showToast(this, menuItem.getTitle().toString());
                setNavigationViewChecked(5);
                mDrawerLayout.closeDrawers();
                break;
        }
        return true;
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
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = mHomeFragment.newInstance();
        transaction.replace(R.id.frame_content, homeFragment).commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();

        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                }
                beginTransaction.replace(R.id.frame_content, mHomeFragment);
                break;
            case 1:
                if (mMoreFragment == null) {
                    mMoreFragment = MoreFragment.newInstance();
                }
                beginTransaction.replace(R.id.frame_content, mMoreFragment);
                break;
            case 2:
                if (mWeChatFragment == null) {
                    mWeChatFragment = WeChatFragment.newInstance();
                }
                beginTransaction.replace(R.id.frame_content, mWeChatFragment);
                break;
            case 3:
                if (mGuideFragment == null) {
                    mGuideFragment = GuideFragment.newInstance();
                }
                beginTransaction.replace(R.id.frame_content, mGuideFragment);
                break;
            case 4:
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.newInstance();
                }
                beginTransaction.replace(R.id.frame_content, mProjectFragment);
                break;
        }
        beginTransaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
