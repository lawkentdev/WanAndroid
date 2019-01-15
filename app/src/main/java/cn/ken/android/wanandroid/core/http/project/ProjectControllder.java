package cn.ken.android.wanandroid.core.http.project;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class ProjectControllder {
    public interface Presenter {
        void getProjectClassifyData();
    }

    public interface View extends BaseView<Presenter> {
        void setProjectClassifyData(BaseResponse<List<ProjectClassifyData>> listBaseResponse);
    }

    public interface Model {
        void getProjectClassifyData(BaseCallback<BaseResponse<List<ProjectClassifyData>>> callback);
    }
}
