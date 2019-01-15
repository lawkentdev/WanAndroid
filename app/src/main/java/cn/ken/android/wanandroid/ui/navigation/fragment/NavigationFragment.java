package cn.ken.android.wanandroid.ui.navigation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.core.http.navigation.NavigationController;
import cn.ken.android.wanandroid.core.http.navigation.NavigationPresenter;
import cn.ken.android.wanandroid.ui.navigation.adapter.MyTabAdapter;
import cn.ken.android.wanandroid.ui.navigation.adapter.NavPagerAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends Fragment implements NavigationController.View {

    VerticalTabLayout verticalTabLayout;
    RecyclerView recyclerView;
    MyTabAdapter tabAdapter;
    RefreshLayout refreshLayout;
    NavPagerAdapter navPagerAdapter;
    List<String> tabTitles = new ArrayList<>();
    List<NavigationListData> navigationListDataList = new ArrayList<>();
    NavigationController.Presenter presenter;

    public static NavigationFragment newInstance() {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        verticalTabLayout = (VerticalTabLayout) view.findViewById(R.id.navigation_frag_vertical_tab_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.navigation_frag_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        navPagerAdapter = new NavPagerAdapter(getContext(), navigationListDataList);
        recyclerView.setAdapter(navPagerAdapter);
        verticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                recyclerView.scrollToPosition(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        refreshLayout = (RefreshLayout) view.findViewById(R.id.navigation_frag_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                navigationListDataList.clear();
                tabTitles.clear();
                showLoading();
                presenter.getNavigationListData();
                refreshlayout.finishRefresh();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        presenter = new NavigationPresenter(this);
        presenter.getNavigationListData();
    }

    @Override
    public void setNavigationListData(BaseResponse<List<NavigationListData>> navigationListData) {
        LogUtil.e("NavigationFragment", navigationListData.getData().get(0).getName());
        for (NavigationListData data : navigationListData.getData()) {
            navigationListDataList.add(data);
            tabTitles.add(data.getName());
        }
        verticalTabLayout.setTabAdapter(tabAdapter = new MyTabAdapter(tabTitles));
        navPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(NavigationController.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {

    }
}