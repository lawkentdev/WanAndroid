package cn.ken.android.wanandroid.core.http.project;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.wx.WxModel;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectModel implements ProjectControllder.Model {
    private static ProjectModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private ProjectModel() {
    }

    public static ProjectModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProjectModel();
        }
        return INSTANCE;
    }


    @Override
    public void getProjectClassifyData(final BaseCallback<BaseResponse<List<ProjectClassifyData>>> callback) {
        geeksApis.getProjectClassifyData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<ProjectClassifyData>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<List<ProjectClassifyData>> listBaseResponse) {
                        LogUtil.e("ProjectModel", listBaseResponse.getData().get(0).getName());
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
