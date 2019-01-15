package cn.ken.android.wanandroid.core.http.navigation;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NavigationModel implements NavigationController.Model {
    private static NavigationModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private NavigationModel() {
    }

    public static NavigationModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NavigationModel();
        }
        return INSTANCE;
    }

    @Override
    public void getNavigationListData(final BaseCallback<BaseResponse<List<NavigationListData>>> callback) {
        geeksApis.getNavigationListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<NavigationListData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<NavigationListData>> listBaseResponse) {
                        LogUtil.e("NavigationModel", listBaseResponse.getData().get(0).getName());
                        callback.onSuccess(listBaseResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish();
                    }
                });
    }
}
