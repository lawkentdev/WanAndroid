package cn.ken.android.wanandroid.core.http;

import java.util.List;

import cn.ken.android.wanandroid.core.api.GeeksApis;
import cn.ken.android.wanandroid.core.bean.BaseResponse;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.core.bean.main.banner.BannerData;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleListData;
import cn.ken.android.wanandroid.core.bean.main.login.LoginData;
import cn.ken.android.wanandroid.core.bean.main.search.TopSearchData;
import cn.ken.android.wanandroid.core.bean.main.search.UsefulSiteData;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.core.bean.project.ProjectClassifyData;
import cn.ken.android.wanandroid.core.bean.project.ProjectListData;
import cn.ken.android.wanandroid.core.bean.wx.WxAuthor;
import io.reactivex.Observable;

public class HttpHelper {
    private GeeksApis mGeeksApis;

    public HttpHelper(GeeksApis geeksApis) {
        mGeeksApis = geeksApis;
    }

    public Observable<BaseResponse<FeedArticleListData>> getFeedArticleList(int pageNum) {
        return mGeeksApis.getFeedArticleList(pageNum);
    }

    public Observable<BaseResponse<FeedArticleListData>> getSearchList(int pageNum, String k) {
        return mGeeksApis.getSearchList(pageNum, k);
    }

    public Observable<BaseResponse<List<TopSearchData>>> getTopSearchData() {
        return mGeeksApis.getTopSearchData();
    }

    public Observable<BaseResponse<List<UsefulSiteData>>> getUsefulSites() {
        return mGeeksApis.getUsefulSites();
    }

    public Observable<BaseResponse<List<KnowledgeHierarchyData>>> getKnowledgeHierarchyData() {
        return mGeeksApis.getKnowledgeHierarchyData();
    }

    public Observable<BaseResponse<FeedArticleListData>> getKnowledgeHierarchyDetailData(int page, int cid) {
        return mGeeksApis.getKnowledgeHierarchyDetailData(page, cid);
    }

    public Observable<BaseResponse<List<NavigationListData>>> getNavigationListData() {
        return mGeeksApis.getNavigationListData();
    }

    public Observable<BaseResponse<List<ProjectClassifyData>>> getProjectClassifyData() {
        return mGeeksApis.getProjectClassifyData();
    }

    public Observable<BaseResponse<ProjectListData>> getProjectListData(int page, int cid) {
        return mGeeksApis.getProjectListData(page, cid);
    }

    public Observable<BaseResponse<List<WxAuthor>>> getWxAuthorListData() {
        return mGeeksApis.getWxAuthorListData();
    }

    public Observable<BaseResponse<FeedArticleListData>> getWxSumData(int id, int page) {
        return mGeeksApis.getWxSumData(id, page);
    }

    public Observable<BaseResponse<FeedArticleListData>> getWxSearchSumData(int id, int page, String k) {
        return mGeeksApis.getWxSearchSumData(id, page, k);
    }

    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mGeeksApis.getLoginData(username, password);
    }

    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mGeeksApis.getRegisterData(username, password, repassword);
    }

    public Observable<BaseResponse<LoginData>> logout() {
        return mGeeksApis.logout();
    }

    public Observable<BaseResponse<FeedArticleListData>> addCollectArticle(int id) {
        return mGeeksApis.addCollectArticle(id);
    }

    public Observable<BaseResponse<FeedArticleListData>> addCollectOutsideArticle(String title, String author, String link) {
        return mGeeksApis.addCollectOutsideArticle(title, author, link);
    }

    public Observable<BaseResponse<FeedArticleListData>> getCollectList(int page) {
        return mGeeksApis.getCollectList(page);
    }

    public Observable<BaseResponse<FeedArticleListData>> cancelCollectPageArticle(int id) {
        return mGeeksApis.cancelCollectPageArticle(id, -1);
    }

    public Observable<BaseResponse<FeedArticleListData>> cancelCollectArticle(int id) {
        return mGeeksApis.cancelCollectArticle(id, -1);
    }

    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mGeeksApis.getBannerData();
    }
}
