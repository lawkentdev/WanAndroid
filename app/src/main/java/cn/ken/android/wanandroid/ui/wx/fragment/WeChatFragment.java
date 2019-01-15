package cn.ken.android.wanandroid.ui.wx.fragment;

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
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.core.http.wx.WxController;
import cn.ken.android.wanandroid.core.http.wx.WxPresenter;
import cn.ken.android.wanandroid.ui.wx.adapter.WxFragmentPagerAdapter;
import cn.ken.android.wanandroid.utils.LogUtil;
import cn.ken.android.wanandroid.utils.ToastUtils;

public class WeChatFragment extends Fragment implements WxController.View {

    TabLayout tabLayout;
    ViewPager pager;
    List<WxAuthor> wxAuthors = new ArrayList<>();
    WxFragmentPagerAdapter pagerAdapter;
    ProgressDialog dialog;
    WxController.Presenter presenter;

    public static WeChatFragment newInstance() {
        WeChatFragment fragment = new WeChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx, container, false);

        pager = (ViewPager) view.findViewById(R.id.frag_wx_view_pager);
        pagerAdapter = new WxFragmentPagerAdapter(getActivity().getSupportFragmentManager(), wxAuthors);
        pager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.frag_wx_tab_layout);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("加载中...");
        dialog.show();
        presenter = new WxPresenter(this);
        presenter.getWxAuthorData();
    }

    @Override
    public void setWxAuthorData(BaseResponse<List<WxAuthor>> listBaseResponse) {
        LogUtil.e("wx", listBaseResponse.getData().get(0).getName());
        wxAuthors.addAll(listBaseResponse.getData());
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(WxController.Presenter presenter) {
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
