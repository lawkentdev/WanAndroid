package cn.ken.android.wanandroid.core.http.wx;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class WxSumController {
    public interface Presenter {
        void getWxSumData(int id, int page);
    }

    public interface View extends BaseView<Presenter> {
        void setWxSumData(BaseResponse<FeedArticleListData> listData);
    }

    public interface Model {
        void getWxSumData(int id, int page, BaseCallback<BaseResponse<FeedArticleListData>> callback);
    }
}
