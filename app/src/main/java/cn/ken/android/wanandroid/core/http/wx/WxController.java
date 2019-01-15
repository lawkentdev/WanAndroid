package cn.ken.android.wanandroid.core.http.wx;

import java.util.List;

import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import cn.ken.android.wanandroid.core.http.base.BaseCallback;
import cn.ken.android.wanandroid.core.http.base.BaseView;

public class WxController {
    public interface Presenter {
        void getWxAuthorData();
    }

    public interface View extends BaseView<Presenter> {
        void setWxAuthorData(BaseResponse<List<WxAuthor>> listBaseResponse);
    }

    public interface Model {
        void getWxAuthorData(BaseCallback<BaseResponse<List<WxAuthor>>> callback);
    }
}
