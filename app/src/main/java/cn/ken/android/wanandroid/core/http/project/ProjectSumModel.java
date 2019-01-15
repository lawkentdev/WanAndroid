package cn.ken.android.wanandroid.core.http.project;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.utils.HttpUtils;
import cn.ken.android.wanandroid.utils.LogUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectSumModel implements ProjectSumController.Model {
    private static ProjectSumModel INSTANCE = null;
    private GeeksApis geeksApis = HttpUtils.geeksApis;

    private ProjectSumModel() {
    }

    public static ProjectSumModel newInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProjectSumModel();
        }
        return INSTANCE;
    }

    @Override
    public void getProjectListData(int page, int id, final BaseCallback<BaseResponse<ProjectListData>> callback) {
        geeksApis.getProjectListData(page, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<ProjectListData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<ProjectListData> projectListDataBaseResponse) {
                        LogUtil.e("ProjectListModel", projectListDataBaseResponse.getData().getDatas().get(0).getTitle());
                        callback.onSuccess(projectListDataBaseResponse);
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
