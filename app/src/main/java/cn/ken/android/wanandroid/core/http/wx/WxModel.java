package cn.ken.android.wanandroid.core.http.wx;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WxModel implements WxController.Model {
    private static WxModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private WxModel() {
    }

    public static WxModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WxModel();
        }
        return INSTANCE;
    }

    @Override
    public void getWxAuthorData(final BaseCallback<BaseResponse<List<WxAuthor>>> callback) {
        geeksApis.getWxAuthorListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<WxAuthor>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<WxAuthor>> listBaseResponse) {
                        LogUtil.e("WxModel", listBaseResponse.getData().get(0).getName());
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
