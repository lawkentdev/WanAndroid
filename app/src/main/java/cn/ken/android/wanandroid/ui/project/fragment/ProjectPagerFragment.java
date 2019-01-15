package cn.ken.android.wanandroid.ui.project.fragment;

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
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.bean.project.ProjectListData;
import cn.ken.android.wanandroid.core.http.project.ProjectSumController;
import cn.ken.android.wanandroid.core.http.project.ProjectSumPresenter;
import cn.ken.android.wanandroid.ui.project.adapter.ProjectListAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;

@SuppressLint("ValidFragment")
public class ProjectPagerFragment extends Fragment implements ProjectSumController.View {
    int sampleLayoutRes;
    RecyclerView recyclerView;
    RefreshLayout refreshLayout;
    ProjectListAdapter adapter;
    ProjectClassifyData projectClassifyData;
    List<FeedArticleData> feedArticleList = new ArrayList<>();
    ProjectSumController.Presenter presenter;
    int page = 1;

    @SuppressLint("ValidFragment")
    public ProjectPagerFragment(ProjectClassifyData projectClassifyData) {
        this.projectClassifyData = projectClassifyData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_pager, container, false);

//        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
//        sampleStub.setLayoutResource(sampleLayoutRes);
//        sampleStub.inflate();

        recyclerView = (RecyclerView) view.findViewById(R.id.frag_project_pager_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        adapter = new ProjectListAdapter(feedArticleList);
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
        presenter = new ProjectSumPresenter(this);
        presenter.getProjectListData(0, projectClassifyData.getId());
    }

    private void initRefreshLayout(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.project_frag_pager_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.getProjectListData(page = 0, projectClassifyData.getId());
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                presenter.getProjectListData(page++, projectClassifyData.getId());
                refreshlayout.getRefreshFooter();
                refreshlayout.finishLoadMore();
            }
        });
    }

    @Override
    public void setProjectListData(BaseResponse<ProjectListData> listBaseResponse) {
        LogUtil.e("ProjectPagerFragment", listBaseResponse.getData().getDatas().get(0).getTitle());
        feedArticleList.addAll(listBaseResponse.getData().getDatas());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ProjectSumController.Presenter presenter) {
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
