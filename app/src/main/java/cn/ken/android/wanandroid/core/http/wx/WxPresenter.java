package cn.ken.android.wanandroid.core.http.wx;


import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;

public class WxPresenter implements WxController.Presenter {
    private WxController.Model taskModel;
    private WxController.View taskView;

    public WxPresenter(WxController.View view) {
        this.taskView = view;
        taskModel = WxModel.newInstance();
        view.setPresenter(this);
    }

    @Override
    public void getWxAuthorData() {
        taskModel.getWxAuthorData(new BaseCallback<BaseResponse<List<WxAuthor>>>() {
            @Override
            public void onSuccess(BaseResponse<List<WxAuthor>> listBaseResponse) {
                taskView.setWxAuthorData(listBaseResponse);
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
