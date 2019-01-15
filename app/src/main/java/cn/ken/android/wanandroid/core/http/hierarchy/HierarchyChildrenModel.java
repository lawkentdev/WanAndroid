package cn.ken.android.wanandroid.core.http.hierarchy;

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

public class HierarchyChildrenModel implements HierarchyChildrenController.Model {
    private static HierarchyChildrenModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private HierarchyChildrenModel() {
    }

    public static HierarchyChildrenModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HierarchyChildrenModel();
        }
        return INSTANCE;
    }

    @Override
    public void getKnowledgeHierarchyDetailData(int page, int cid, final BaseCallback<BaseResponse<FeedArticleListData>> callback) {
        geeksApis.getKnowledgeHierarchyDetailData(page, cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<FeedArticleListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse) {
                        LogUtil.e("HierarchyChildrenModel", feedArticleListDataBaseResponse.getData().getDatas().get(0).getTitle());
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
