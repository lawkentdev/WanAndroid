package cn.ken.android.wanandroid.ui.wx.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
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

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.core.http.wx.WxSumController;
import cn.ken.android.wanandroid.core.http.wx.WxSumPresenter;
import cn.ken.android.wanandroid.ui.wx.adapter.WXListAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;

@SuppressLint("ValidFragment")
public class WxPageFragment extends Fragment implements WxSumController.View {

    int sampleLayoutRes;
    RecyclerView recyclerView;
    RefreshLayout refreshLayout;
    WXListAdapter adapter;
    WxAuthor author;
    List<FeedArticleData> feedArticleList = new ArrayList<>();
    WxSumController.Presenter presenter;
    int page = 1;

//    public WxPageFragment newInstance(WxAuthor author) {
//        WxPageFragment fragment = new WxPageFragment();
//        this.wxAuthor = author;
//        return fragment;
//    }

    @SuppressLint("ValidFragment")
    public WxPageFragment(WxAuthor author) {
        this.author = author;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx_pager, container, false);

//        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
//        sampleStub.setLayoutResource(sampleLayoutRes);
//        sampleStub.inflate();

        recyclerView = (RecyclerView) view.findViewById(R.id.frag_wx_pager_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new WXListAdapter(feedArticleList);
        recyclerView.setAdapter(adapter);

        initRefreshLayout(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new WxSumPresenter(this);
        presenter.getWxSumData(author.getId(), page);
    }

    private void initRefreshLayout(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.wx_frag_pager_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getWxSumData(author.getId(), page = 1);
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                presenter.getWxSumData(author.getId(), page++);
                refreshlayout.getRefreshFooter();
                refreshlayout.finishLoadMore();
            }
        });
    }

//        private void setFooterView(RecyclerView view) {
//        View footer = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_view_footer, view, false);
//        adapter.setFooterView(footer);
//    }

    @Override
    public void setWxSumData(BaseResponse<FeedArticleListData> listData) {
        LogUtil.e("WxPager", listData.getData().getDatas().get(0).getTitle());
        feedArticleList.addAll(listData.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(WxSumController.Presenter presenter) {
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
