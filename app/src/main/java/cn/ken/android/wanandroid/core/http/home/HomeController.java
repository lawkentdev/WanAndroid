package cn.ken.android.wanandroid.core.http.home;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class HomeController {
    public interface Presenter {
        void getBannerData();
        void getArticleListData(int pageNum);
//        void attachView(Controller.View view);
//        void detachView();
//        boolean isActive();
    }

    public interface View extends BaseView<Presenter> {
        void setBannerData(BaseResponse<List<BannerData>> listBaseResponse);
        void setArticleListData(BaseResponse<FeedArticleListData> feedArticleListDataBaseResponse);
    }

    public interface Model {
        void getBannerData(BaseCallback<BaseResponse<List<BannerData>>> callback);
        void getArticleListData(int pageNum, BaseCallback<BaseResponse<FeedArticleListData>> callback);
    }
}
