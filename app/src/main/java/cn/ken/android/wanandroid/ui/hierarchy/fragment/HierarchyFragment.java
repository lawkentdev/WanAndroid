package cn.ken.android.wanandroid.ui.hierarchy.fragment;


import android.app.ProgressDialog;
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
import cn.ken.android.wanandroid.core.http.hierarchy.HierarchyController;
import cn.ken.android.wanandroid.core.http.hierarchy.HierarchyPresenter;
import cn.ken.android.wanandroid.core.http.home.HomeController;
import cn.ken.android.wanandroid.ui.hierarchy.adapter.HierarchyListAdapter;
import cn.ken.android.wanandroid.ui.home.adapter.HomeListAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;
import cn.ken.android.wanandroid.utils.ToastUtils;

public class HierarchyFragment extends Fragment implements HierarchyController.View {

    RefreshLayout refreshLayout;
    ProgressDialog dialog;
    HierarchyController.Presenter presenter;
    List<KnowledgeHierarchyData> hierarchyDataList;
    List<String> arr = new ArrayList<>();
    ;
    HierarchyListAdapter adapter;
    RecyclerView recyclerView;

    public static HierarchyFragment newInstance() {
        HierarchyFragment fragment = new HierarchyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.knowledge_frag_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        hierarchyDataList = new ArrayList<>();
        adapter = new HierarchyListAdapter(hierarchyDataList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        initData();
        LogUtil.e("hieoncreate", "oncreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initRefreshLayout(View view) {
        refreshLayout = (RefreshLayout) view.findViewById(R.id.hierarchy_frag_refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                hierarchyDataList.clear();
                presenter.getKnowledgeHierarchyData();
                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.getRefreshFooter();
                refreshlayout.finishLoadMore();
            }
        });
    }

    private void initData() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("加载中...");
        dialog.show();
        presenter = new HierarchyPresenter(this);
        presenter.getKnowledgeHierarchyData();
    }

    @Override
    public void setKnowledgeHierarchyData(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse) {
        hierarchyDataList.addAll(listBaseResponse.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HierarchyController.Presenter presenter) {
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