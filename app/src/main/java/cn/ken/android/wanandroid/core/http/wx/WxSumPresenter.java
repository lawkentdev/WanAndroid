package cn.ken.android.wanandroid.core.http.wx;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class WxSumPresenter implements WxSumController.Presenter {
    private WxSumController.Model taskModel;
    private WxSumController.View taskView;

    public WxSumPresenter(WxSumController.View view) {
        this.taskView = view;
        taskModel = WxSumModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getWxSumData(int id, int page) {
        taskModel.getWxSumData(id, page, new BaseCallback<BaseResponse<FeedArticleListData>>() {
            @Override
            public void onSuccess(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                taskView.setWxSumData(feedArticleListDataBaseResponse);
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
