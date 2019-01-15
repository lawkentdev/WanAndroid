package cn.ken.android.wanandroid.core.http.project;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class ProjectPresenter implements ProjectControllder.Presenter {
    private ProjectControllder.Model taskModel;
    private ProjectControllder.View taskView;

    public ProjectPresenter(ProjectControllder.View view) {
        this.taskView = view;
        taskModel = ProjectModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getProjectClassifyData() {
        taskModel.getProjectClassifyData(new BaseCallback<BaseResponse<List<ProjectClassifyData>>>() {
            @Override
            public void onSuccess(BaseResponse<List<ProjectClassifyData>> data) {
                taskView.setProjectClassifyData(data);
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
