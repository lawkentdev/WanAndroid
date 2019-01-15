package cn.ken.android.wanandroid.core.http.home;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class HomePresenter implements HomeController.Presenter {
    private HomeController.Model taskModel;
    private HomeController.View taskView;

    public HomePresenter(HomeController.View view) {
        this.taskView = view;
        taskModel = HomeModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getBannerData() {
        taskModel.getBannerData(new BaseCallback<BaseResponse<List<BannerData>>>() {

            @Override
            public void onSuccess(BaseResponse<List<BannerData>> data) {
//                if (isActive()) {
                taskView.setBannerData(data);
//                }
            }

            @Override
            public void onError(String error) {
//                if (isActive()) {
                taskView.showError();
                taskView.hideLoading();
//                }
            }

            @Override
            public void onFinish() {
//                if (isActive()) {
                taskView.hideLoading();
//                }
            }
        });
    }

    @Override
    public void getArticleListData(int pageNum) {
        taskModel.getArticleListData(pageNum, new BaseCallback<BaseResponse<FeedArticleListData>>() {
            @Override
            public void onSuccess(BaseResponse<FeedArticleListData> data) {
                taskView.setArticleListData(data);
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

//    @Override
//    public void attachView(Controller.View view) {
//        this.taskView = view;
//    }
//
//    @Override
//    public void detachView() {
//        this.taskView = null;
//    }
//
//    @Override
//    public boolean isActive() {
//        return this.taskView != null;
//    }

}
