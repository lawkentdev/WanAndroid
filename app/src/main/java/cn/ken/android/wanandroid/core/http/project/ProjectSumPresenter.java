package cn.ken.android.wanandroid.core.http.project;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class ProjectSumPresenter implements ProjectSumController.Presenter {
    private ProjectSumController.Model taskModel;
    private ProjectSumController.View taskView;

    public ProjectSumPresenter(ProjectSumController.View view) {
        this.taskView = view;
        taskModel = ProjectSumModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getProjectListData(int page, int id) {
        taskModel.getProjectListData(page, id, new BaseCallback<BaseResponse<ProjectListData>>() {
            @Override
            public void onSuccess(BaseResponse<ProjectListData> data) {
                taskView.setProjectListData(data);
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
