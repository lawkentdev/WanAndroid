package cn.ken.android.wanandroid.ui.project.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.ui.project.fragment.ProjectPagerFragment;

public class ProjectFragmentPagerAdapter extends FragmentPagerAdapter {
    List<ProjectClassifyData> projectClassifyDataList;

    public ProjectFragmentPagerAdapter(FragmentManager fm, List<ProjectClassifyData> projectClassifyDataList) {
        super(fm);
        this.projectClassifyDataList = projectClassifyDataList;
    }

    @Override
    public Fragment getItem(int i) {
        return new ProjectPagerFragment(projectClassifyDataList.get(i));
    }

    @Override
    public int getCount() {
        return projectClassifyDataList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return projectClassifyDataList.get(position).getName();
    }
}
