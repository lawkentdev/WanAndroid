package cn.ken.android.wanandroid.ui.project.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.http.project.ProjectControllder;
import cn.ken.android.wanandroid.core.http.project.ProjectPresenter;
import cn.ken.android.wanandroid.ui.project.adapter.ProjectFragmentPagerAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;
import cn.ken.android.wanandroid.utils.ToastUtils;


public class ProjectFragment extends Fragment implements ProjectControllder.View {

    TabLayout tabLayout;
    ViewPager pager;
    List<ProjectClassifyData> projectClassifyDataList = new ArrayList<>();
    ProjectFragmentPagerAdapter pagerAdapter;
    ProgressDialog dialog;
    ProjectControllder.Presenter presenter;

    public static ProjectFragment newInstance() {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        pager = (ViewPager) view.findViewById(R.id.frag_project_view_pager);
        pagerAdapter = new ProjectFragmentPagerAdapter(getActivity().getSupportFragmentManager(), projectClassifyDataList);
        pager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.frag_project_tab_layout);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading");
        dialog.show();
        presenter = new ProjectPresenter(this);
        presenter.getProjectClassifyData();
    }


    @Override
    public void setProjectClassifyData(BaseResponse<List<ProjectClassifyData>> listBaseResponse) {
        LogUtil.e("ProjectFragment", listBaseResponse.getData().get(0).getName());
        projectClassifyDataList.addAll(listBaseResponse.getData());
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ProjectControllder.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError() {
        ToastUtils.showToast(getContext(), "未知错误！");
    }
}
