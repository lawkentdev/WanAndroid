package cn.ken.android.wanandroid.ui.wx.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.ui.wx.fragment.WxPageFragment;

public class WxFragmentPagerAdapter extends FragmentPagerAdapter {
    List<WxAuthor> wxAuthorList;

    public WxFragmentPagerAdapter(FragmentManager fm, List<WxAuthor> wxAuthorList) {
        super(fm);
        this.wxAuthorList = wxAuthorList;
    }

    @Override
    public Fragment getItem(int i) {
        return new WxPageFragment(wxAuthorList.get(i));
    }

    @Override
    public int getCount() {
        return wxAuthorList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return wxAuthorList.get(position).getName();
    }
}
