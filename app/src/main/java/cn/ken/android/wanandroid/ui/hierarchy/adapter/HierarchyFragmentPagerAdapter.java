package cn.ken.android.wanandroid.ui.hierarchy.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.ui.hierarchy.fragment.HierarchyPagerFragment;

public class HierarchyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<KnowledgeHierarchyData> knowledgeHierarchyDataList;

    public HierarchyFragmentPagerAdapter(FragmentManager fm, List<KnowledgeHierarchyData> knowledgeHierarchyDataList) {
        super(fm);
        this.knowledgeHierarchyDataList = knowledgeHierarchyDataList;
    }

    @Override
    public Fragment getItem(int i) {
        return new HierarchyPagerFragment(knowledgeHierarchyDataList.get(i));
    }

    @Override
    public int getCount() {
        return knowledgeHierarchyDataList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return knowledgeHierarchyDataList.get(position).getName();
    }
}
