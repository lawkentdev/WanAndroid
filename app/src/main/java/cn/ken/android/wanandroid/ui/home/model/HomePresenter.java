package cn.ken.android.wanandroid.ui.home.model;

public class HomePresenter {
    private HomeFragView fragView;

    public HomePresenter(HomeFragView fragView) {
        this.fragView = fragView;
    }

    public void getDatas(int param) {
        // 显示正在加载
        fragView.showLoading();
        HomeModel.getHomeArticleDatas(param, new HomeCallBack() {
            @Override
            public void Success(Object datas) {
                if (isAttachView()) {
                    // 显示数据
                    fragView.showDatas((String) datas);
                }
            }

            @Override
            public void onFailure(String failureMsg) {
                if (isAttachView()) {
                    fragView.showFailureMessage(failureMsg);
                }
            }

            @Override
            public void onError(String errorMsg) {
                if (isAttachView()) {
                    fragView.showErrorMessage(errorMsg);
                }
            }

            @Override
            public void onComplete() {
                if (isAttachView()) {
                    fragView.hideLoading();
                }
            }
        });
    }

    /**
     * 绑定View
     *
     * @param fragView
     */
    public void attachView(HomeFragView fragView) {
        this.fragView = fragView;
    }

    /**
     * 断开View
     */
    public void detachView() {
        this.fragView = null;
    }

    /**
     * 是否与View建立连接
     *
     * @return
     */
    public boolean isAttachView() {
        return fragView != null;
    }

}
