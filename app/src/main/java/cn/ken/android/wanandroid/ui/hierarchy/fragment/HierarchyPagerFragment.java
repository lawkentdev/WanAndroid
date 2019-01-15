package cn.ken.android.wanandroid.ui.hierarchy.fragment;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.hierarchy.HierarchyChildrenController;
import cn.ken.android.wanandroid.core.http.hierarchy.HierarchyChildrenPresenter;
import cn.ken.android.wanandroid.ui.hierarchy.adapter.HierarchyChildrenListAdapter;
import cn.ken.android.wanandroid.ui.project.adapter.ProjectListAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;

@SuppressLint("ValidFragment")
public class HierarchyPagerFragment extends Fragment implements HierarchyChildrenController.View {

    KnowledgeHierarchyData knowledgeHierarchyData;
    RefreshLayout refreshLayout;
    RecyclerView recyclerView;
    HierarchyChildrenListAdapter adapter;
    List<FeedArticleData> feedArticleDataList = new ArrayList<>();
    HierarchyChildrenController.Presenter presenter;
    int page = 0;

    @SuppressLint("ValidFragment")
    public HierarchyPagerFragment(KnowledgeHierarchyData knowledgeHierarchyData) {
        this.knowledgeHierarchyData = knowledgeHierarchyData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hierarchy_pager, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.hierarchy_frag_pager_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new HierarchyChildrenListAdapter(feedArticleDataList);
        recyclerView.setAdapter(adapter);

        initRefreshLayout(view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new HierarchyChildrenPresenter(this);
        presenter.getKnowledgeHierarchyDetailData(page, knowledgeHierarchyData.getId());
    }

    private void initRefreshLayout(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.hierarchy_frag_pager_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getKnowledgeHierarchyDetailData(page = 0, knowledgeHierarchyData.getId());
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                presenter.getKnowledgeHierarchyDetailData(page++, knowledgeHierarchyData.getId());
                refreshlayout.getRefreshFooter();
                refreshlayout.finishLoadMore();
            }
        });
    }

    @Override
    public void setKnowledgeHierarchyDetailData(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
        LogUtil.e("HierarchyPagerFragment", feedArticleListDataBaseResponse.getData().getDatas().get(0).getTitle());
        feedArticleDataList.addAll(feedArticleListDataBaseResponse.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HierarchyChildrenController.Presenter presenter) {
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
