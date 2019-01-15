package cn.ken.android.wanandroid.core.http.hierarchy;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class HierarchyController {
    public interface Presenter {
        void getKnowledgeHierarchyData();
    }

    public interface View extends BaseView<Presenter> {
        void setKnowledgeHierarchyData(BaseResponse<List<KnowledgeHierarchyData>> listBaseResponse);
    }

    public interface Model {
        void getKnowledgeHierarchyData(BaseCallback<BaseResponse<List<KnowledgeHierarchyData>>> callback);
    }
}
