package cn.ken.android.wanandroid.ui.home.model;

public interface HomeNetTask<T> {
    void execute(T data, HomeCallBack callBack);
}
