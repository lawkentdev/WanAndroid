package cn.ken.android.wanandroid.ui.home.model;

public interface HomeFragView {
    void showLoading();
    void hideLoading();
    void showDatas(String data);
    void showFailureMessage(String failureMsg);
    void showErrorMessage(String errorMsg);
}
