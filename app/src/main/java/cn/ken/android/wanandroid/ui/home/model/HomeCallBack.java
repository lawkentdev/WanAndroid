package cn.ken.android.wanandroid.ui.home.model;

public interface HomeCallBack<T> {
    void Success(T datas);
    void onFailure(String failureMsg);
    void onError(String errorMsg);
    void onComplete();
}
