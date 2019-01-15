package cn.ken.android.wanandroid.ui.navigation.adapter;

import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.TabView;

public class MyTabAdapter implements TabAdapter {
    List<String> menus;

    public MyTabAdapter(List<String> menus) {
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public TabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public TabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public TabView.TabTitle getTitle(int position) {
        return new TabView.TabTitle.Builder()
                .setContent(menus.get(position))
                .setTextColor(0xFF36BC9B, 0xFF757575)
                .build();
    }

    @Override
    public int getBackground(int position) {
        return -1;
    }
}
