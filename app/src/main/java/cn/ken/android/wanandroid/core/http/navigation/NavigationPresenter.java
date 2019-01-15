package cn.ken.android.wanandroid.core.http.navigation;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class NavigationPresenter implements NavigationController.Presenter {
    private NavigationController.Model taskModel;
    private NavigationController.View taskView;

    public NavigationPresenter(NavigationController.View view) {
        this.taskView = view;
        taskModel = NavigationModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getNavigationListData() {
        taskModel.getNavigationListData(new BaseCallback<BaseResponse<List<NavigationListData>>>() {
            @Override
            public void onSuccess(BaseResponse<List<NavigationListData>> data) {
                taskView.setNavigationListData(data);
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
