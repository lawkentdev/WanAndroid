package cn.ken.android.wanandroid.ui.home.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.home.HomePresenter;
import cn.ken.android.wanandroid.core.http.home.HomeController;
import cn.ken.android.wanandroid.ui.home.GlideImageLoader;
import cn.ken.android.wanandroid.ui.home.adapter.HomeListAdapter;
import cn.ken.android.wanandroid.ui.main.activity.ReadArticleActivity;
import cn.ken.android.wanandroid.utils.LogUtil;
import cn.ken.android.wanandroid.utils.ToastUtils;

public class HomeFragment extends Fragment implements HomeController.View {

    Banner banner;
    List<BannerData> bannerDatas = new ArrayList<>();
    List<FeedArticleData> mHomeArticles = new ArrayList<>();
    RecyclerView mRecyclerView;
    HomeListAdapter mMyAdapter;
    RefreshLayout refreshLayout;

    private ProgressDialog dialog;
    private HomeController.Presenter presenter;
    public int pageNum = 0;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.home_frag_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        mMyAdapter = new HomeListAdapter(mHomeArticles);
        mRecyclerView.setAdapter(mMyAdapter);
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
//        setFooterView(mRecyclerView);

        initRefreshLayout(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
//        presenter.attachView(this); // 绑定view引用
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("加载中...");
        presenter = new HomePresenter(this);
        presenter.getBannerData();
        presenter.getArticleListData(pageNum);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (banner != null) {
            banner.startAutoPlay();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (banner != null) {
            banner.stopAutoPlay();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        presenter.detachView(); // 断开view引用
    }

    private void initRefreshLayout(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.home_frag_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                bannerDatas.clear();
                mHomeArticles.clear();
                showLoading();
                presenter.getBannerData();
                presenter.getArticleListData(pageNum = 0);
                refreshlayout.finishRefresh();
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                presenter.getArticleListData(pageNum++);
                refreshlayout.getRefreshFooter();
                refreshlayout.finishLoadMore();
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.item_home_frag_header, view, false);
        // 初始化Banner
        banner = (Banner) header.findViewById(R.id.home_frag_item_banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(bannerImages);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Stack);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(bannerTitles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3 * 1000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
//        banner.start();
        // banner监听事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ReadArticleActivity.actionStart(getContext(), bannerDatas.get(position).getTitle(),
                        bannerDatas.get(position).getUrl());
            }
        });
        mMyAdapter.setHeaderView(header);
    }

    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view_footer, view, false);
        mMyAdapter.setFooterView(footer);
    }

    @Override
    public void setBannerData(BaseResponse<List<BannerData>> listBaseResponse) {
        bannerDatas.addAll(listBaseResponse.getData());
        List<String> bannerImages = new ArrayList<>();
        List<String> bannerTitles = new ArrayList<>();
        for (BannerData bannerData : bannerDatas) {
            bannerTitles.add(bannerData.getTitle());
            bannerImages.add(bannerData.getImagePath());
        }
        banner.setImages(bannerImages);
        banner.setBannerTitles(bannerTitles);
        banner.start();
        hideLoading();
    }

    @Override
    public void setArticleListData(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
        for (FeedArticleData data : feedArticleListDataBaseResponse.getData().getDatas()) {
            mHomeArticles.add(data);
//            LogUtil.e("tags", data.getTags().get(0).getName());
        }
        mMyAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void setPresenter(HomeController.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError() {
        ToastUtils.showToast(getContext(), "未知错误...");
    }

}
