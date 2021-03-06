package cn.ken.android.wanandroid.core.http.home;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeModel implements HomeController.Model {

    private static HomeModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private HomeModel() {
    }

    public static HomeModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HomeModel();
        }
        return INSTANCE;
    }

    @Override
    public void getBannerData(final BaseCallback<BaseResponse<List<BannerData>>> callback) {
        geeksApis.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<BannerData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<BannerData>> listBaseResponse) {
                        LogUtil.e("HomeModelBanner", listBaseResponse.toString());
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

    @Override
    public void getArticleListData(int pageNum, final BaseCallback<BaseResponse<FeedArticleListData>> callback) {
        geeksApis.getFeedArticleList(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                        LogUtil.e("HomeModelArticleList", feedArticleListDataBaseResponse.toString());
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

//    @Override
//    public void getData(final BaseCallback callback) {
////        Observable<BaseResponse<LoginData>> mLoginObservable = mDataManager.getLoginData(getLoginAccount(), getLoginPassword());
//        GeeksApis geeksApis = HttpUtils.createRetrofit().create(GeeksApis.class);
//        HttpHelper httpHelper = new HttpHelper(geeksApis);
//        Observable<BaseResponse<List<BannerData>>> mBannerObservable = httpHelper.getBannerData();
//        Observable<BaseResponse<FeedArticleListData>> mArticleObservable = httpHelper.getFeedArticleList(0);
//        Observable.zip(mBannerObservable, mArticleObservable, new BiFunction<BaseResponse<List<BannerData>>, BaseResponse<FeedArticleListData>, HashMap<String, Object>>() {
//            @Override
//            public HashMap<String, Object> apply(BaseResponse<List<BannerData>> listBaseResponse, BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) throws Exception {
//                HashMap<String, Object> map = new HashMap<>(3);
////                map.put(Constants.LOGIN_DATA, loginResponse);
//                map.put(Constants.BANNER_DATA, listBaseResponse);
//                map.put(Constants.ARTICLE_DATA, feedArticleListDataBaseResponse);
//                return map;
//            }
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new Observer<HashMap<String, Object>>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(HashMap<String, Object> map) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        GeeksApis geeksApis = HttpUtils.createRetrofit().create(GeeksApis.class);
//        geeksApis.getBannerData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<BaseResponse<List<BannerData>>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse<List<BannerData>> listBaseResponse) {
//                        for (BannerData data : listBaseResponse.getData()) {
//                            LogUtil.e("Model", data.getTitle());
//                        }
//                        callback.onSuccess(listBaseResponse);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callback.onError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        callback.onFinish();
//                    }
//                });
//    }

}
