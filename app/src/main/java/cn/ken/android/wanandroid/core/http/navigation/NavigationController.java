package cn.ken.android.wanandroid.core.http.navigation;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class NavigationController {
    public interface Presenter {
        void getNavigationListData();
    }

    public interface View extends BaseView<Presenter> {
        void setNavigationListData(BaseResponse<List<NavigationListData>> navigationListData);
    }

    public interface Model {
        void getNavigationListData(BaseCallback<BaseResponse<List<NavigationListData>>> callback);
    }
}
