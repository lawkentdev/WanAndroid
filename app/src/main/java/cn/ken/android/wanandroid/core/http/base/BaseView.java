package cn.ken.android.wanandroid.core.http.base;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showLoading();

    void hideLoading();

    void showError();

//    boolean isActive();
}
