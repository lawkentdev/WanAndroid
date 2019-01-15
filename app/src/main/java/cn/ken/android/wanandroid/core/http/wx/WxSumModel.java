package cn.ken.android.wanandroid.core.http.wx;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WxSumModel implements WxSumController.Model {
    private static WxSumModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private WxSumModel() {
    }

    public static WxSumModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WxSumModel();
        }
        return INSTANCE;
    }

    @Override
    public void getWxSumData(int id, int page, final BaseCallback<BaseResponse<FeedArticleListData>> callback) {
        geeksApis.getWxSumData(id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                        LogUtil.e("WxSumModel", feedArticleListDataBaseResponse.getData().getDatas().get(0).getTitle());
                        callback.onSuccess(feedArticleListDataBaseResponse);
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
