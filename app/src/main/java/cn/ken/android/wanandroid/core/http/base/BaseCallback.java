package cn.ken.android.wanandroid.core.http.base;

public interface BaseCallback<T> {
    void onSuccess(T data);

    void onError(String error);

    void onFinish();
}
