package cn.ken.android.wanandroid.core.http.hierarchy;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class HierarchyChildrenPresenter implements HierarchyChildrenController.Presenter {
    private HierarchyChildrenController.Model taskModel;
    private HierarchyChildrenController.View taskView;

    public HierarchyChildrenPresenter(HierarchyChildrenController.View view) {
        this.taskView = view;
        taskModel = HierarchyChildrenModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getKnowledgeHierarchyDetailData(int page, int cid) {
        taskModel.getKnowledgeHierarchyDetailData(page, cid, new BaseCallback<BaseResponse<FeedArticleListData>>() {
            @Override
            public void onSuccess(BaseResponse<FeedArticleListData> data) {
                taskView.setKnowledgeHierarchyDetailData(data);
            }

            @Override
            public void onError(String error) {
                taskView.showError();
                taskView.hideLoading();
            }

            @Override
            public void onFinish() {
                taskView.hideLoading();
            }
        });
    }
}
