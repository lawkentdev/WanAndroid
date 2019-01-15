package cn.ken.android.wanandroid.core.http.project;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class ProjectSumController {
    public interface Presenter {
        void getProjectListData(int page, int id);
    }

    public interface View extends BaseView<Presenter> {
        void setProjectListData(BaseResponse<ProjectListData> listBaseResponse);
    }

    public interface Model {
        void getProjectListData(int page, int id, BaseCallback<BaseResponse<ProjectListData>> callback);
    }
}
