package cn.ken.android.wanandroid.ui.home.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.home.Controller;
import cn.ken.android.wanandroid.core.http.home.HomeDataPresenter;
import cn.ken.android.wanandroid.ui.home.GlideImageLoader;
import cn.ken.android.wanandroid.ui.home.adapter.HomeListAdapter;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import cn.ken.android.wanandroid.utils.ToastUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment implements Controller.View {

    Banner banner;
    List<String> bannerImages = new ArrayList<>();
    List<String> bannerTitles = new ArrayList<>();
    List<String> mHomeArticles;
    RecyclerView mRecyclerView;
    HomeListAdapter mMyAdapter;

    private ProgressDialog dialog;
    private Controller.Presenter presenter;
    public int pageNum = 0;

//    {
//        bannerTitles.add("第一张图片");
//        bannerImages.add("http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png");
//        bannerTitles.add("第二张图片");
//        bannerImages.add("http://www.wanandroid.com/blogimgs/ab17e8f9-6b79-450b-8079-0f2287eb6f0f.png");
//        bannerTitles.add("第三张图片");
//        bannerImages.add("http://www.wanandroid.com/blogimgs/fb0ea461-e00a-482b-814f-4faca5761427.png");
//    }

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
//        initData();
        mHomeArticles = new ArrayList<String>();
        mMyAdapter = new HomeListAdapter(mHomeArticles);
        mRecyclerView.setAdapter(mMyAdapter);
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(mRecyclerView);
//        setFooterView(mRecyclerView);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
//        presenter.attachView(this); // 绑定view引用
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("加载中...");
        dialog.show();
        presenter = new HomeDataPresenter(this);
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

    private void setHeaderView(RecyclerView view) {
//        bannerImages.add("http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg");
//        bannerImages.add("http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg");
//        bannerImages.add("http://www.wanandroid.com/blogimgs/00f83f1d-3c50-439f-b705-54a49fc3d90d.jpg");
//        bannerTitles.add("JSON工具");
//        bannerTitles.add("JSON工具");
//        bannerTitles.add("JSON工具");
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
        mMyAdapter.setHeaderView(header);
    }

//    private void setFooterView(RecyclerView view) {
//        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer, view, false);
//        mMyAdapter.setFooterView(footer);
//    }

    //初始化RecyclerView中每个item的数据
//    private void initData() {
//        mHomeArticles = new ArrayList<String>();
//        for (int i = 0; i < 20; i++) {
//            mHomeArticles.add("item" + i);
//        }
//    }

    @Override
    public void setBannerData(BaseResponse<List<BannerData>> listBaseResponse) {
        for (BannerData bannerData : listBaseResponse.getData()) {
            bannerTitles.add(bannerData.getTitle().toString());
            bannerImages.add(bannerData.getImagePath().toString());
            LogUtil.e("Frag", bannerData.getTitle());
            LogUtil.e("Frag", bannerData.getImagePath());
        }
        banner.setImages(bannerImages);
        banner.setBannerTitles(bannerTitles);
        banner.start();
    }

    @Override
    public void setArticleListData(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
        for (FeedArticleData data : feedArticleListDataBaseResponse.getData().getDatas()) {
            mHomeArticles.add(data.getAuthor());
        }
//        mMyAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(Controller.Presenter presenter) {
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

//    @Override
//    public boolean isActive() {
//        return isAdded();
//    }

}
