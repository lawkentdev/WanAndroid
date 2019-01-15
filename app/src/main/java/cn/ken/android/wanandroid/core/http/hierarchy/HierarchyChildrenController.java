package cn.ken.android.wanandroid.core.http.hierarchy;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class HierarchyChildrenController {
    public interface Presenter {
        void getKnowledgeHierarchyDetailData(int page, int cid);
    }

    public interface View extends BaseView<Presenter> {
        void setKnowledgeHierarchyDetailData(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse);
    }

    public interface Model {
        void getKnowledgeHierarchyDetailData(int page, int cid, BaseCallback<BaseResponse<FeedArticleListData>> callback);
    }
}
