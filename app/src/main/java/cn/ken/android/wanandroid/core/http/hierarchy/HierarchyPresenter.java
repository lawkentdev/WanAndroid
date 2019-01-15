package cn.ken.android.wanandroid.core.http.hierarchy;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class HierarchyPresenter implements HierarchyController.Presenter {
    private HierarchyController.Model taskModel;
    private HierarchyController.View taskView;

    public HierarchyPresenter(HierarchyController.View view) {
        this.taskView = view;
        taskModel = HierarchyModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getKnowledgeHierarchyData() {
        taskModel.getKnowledgeHierarchyData(new BaseCallback<BaseResponse<List<KnowledgeHierarchyData>>>() {
            @Override
            public void onSuccess(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse) {
                taskView.setKnowledgeHierarchyData(listBaseResponse);
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
