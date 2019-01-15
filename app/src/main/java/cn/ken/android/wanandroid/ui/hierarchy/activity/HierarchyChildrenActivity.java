package cn.ken.android.wanandroid.ui.hierarchy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.base.BaseActivity;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.ui.hierarchy.adapter.HierarchyFragmentPagerAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;

public class HierarchyChildrenActivity extends BaseActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;
    List<KnowledgeHierarchyData> knowledgeHierarchyDataList = new ArrayList<>();
    HierarchyFragmentPagerAdapter pagerAdapter;
    KnowledgeHierarchyData hierarchyData;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hierarchy_children);
        hierarchyData = (KnowledgeHierarchyData) getIntent().getSerializableExtra("KnowledgeHierarchyData");
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_hierarchy_children);
        pager = (ViewPager) findViewById(R.id.frag_hierarchy_view_pager);
        for (KnowledgeHierarchyData childrenDatas : hierarchyData.getChildren()) {
            knowledgeHierarchyDataList.add(childrenDatas);
        }
        LogUtil.e("haofan", hierarchyData.getName());
        pagerAdapter = new HierarchyFragmentPagerAdapter(getSupportFragmentManager(), knowledgeHierarchyDataList);
        pager.setAdapter(pagerAdapter);
        pager.setNestedScrollingEnabled(false);
        tabLayout = (TabLayout) findViewById(R.id.frag_hierarchy_tab_layout);
        tabLayout.setupWithViewPager(pager);
        initToolbar();
    }

    private void initToolbar() {
        // 替换ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(hierarchyData.getName());
    }

    public static void actionStart(Context context, KnowledgeHierarchyData data) {
        Intent intent = new Intent(context, HierarchyChildrenActivity.class);
        intent.putExtra("KnowledgeHierarchyData", data);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_hierarchy_share:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_hierarchy, menu);
        return true;
    }

}
