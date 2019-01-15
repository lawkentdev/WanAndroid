package cn.ken.android.wanandroid.core.http.hierarchy;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.home.HomeModel;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HierarchyModel implements HierarchyController.Model {
    private static HierarchyModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private HierarchyModel() {
    }

    public static HierarchyModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HierarchyModel();
        }
        return INSTANCE;
    }

    @Override
    public void getKnowledgeHierarchyData(final BaseCallback<BaseResponse<List<KnowledgeHierarchyData>>> callback) {
        geeksApis.getKnowledgeHierarchyData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<KnowledgeHierarchyData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse) {
                        LogUtil.e("HierarchyModelData", listBaseResponse.getData().get(0).getName());
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
