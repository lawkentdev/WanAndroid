package cn.ken.android.wanandroid.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.base.BaseActivity;

public class ReadArticleActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView toolbaAarticleTitle;
    private ProgressBar content_progress_bar;
    private WebView webView;
    private LinearLayout contentLayout;
    //    private TextView errorTv;
    private String articleTitle;
    private String articleLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);
        articleTitle = getIntent().getStringExtra("title");
        articleLink = getIntent().getStringExtra("link");
        initView();
        if (!TextUtils.isEmpty(articleTitle)) {
            toolbaAarticleTitle.setText(articleTitle);
        }
        if (!TextUtils.isEmpty(articleLink)) {
            loadContent(articleLink);
        } else {
            contentLayout.setVisibility(View.GONE);
//            errorTv.setVisibility(View.VISIBLE);
        }
    }

    public static void actionStart(Context context, String title, String link) {
        Intent intent = new Intent(context, ReadArticleActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("link", link);
        context.startActivity(intent);
    }

    private void loadContent(String link) {
//        webView.setCallback(new ExtendedWebView.GenericMotionCallback() {
//            @Override
//            public boolean onGenericMotionEvent(MotionEvent event) {
//                return nestedScrollView.onGenericMotionEvent(event);
//            }
//        });
        webView.loadUrl(link);
        webView.setWebChromeClient(new MyWebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 允许使用js
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");
        // 自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        // 自动缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //不 显示webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        // 支持获取手势焦点
        webView.requestFocusFromTouch();
    }

    private void initView() {
        content_progress_bar = (ProgressBar) findViewById(R.id.content_progress_bar);
        webView = (WebView) findViewById(R.id.web_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar_article);
        toolbaAarticleTitle = (TextView) findViewById(R.id.toolbar_article_title_tv);
        contentLayout = (LinearLayout) findViewById(R.id.read_article_content_layout);
        // errorTv = (TextView) findViewById(R.id.load_error_tv);
        initToolbar();
    }

    private void initToolbar() {
        // 替换ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_article_share:
                break;
            case R.id.menu_article_collection:
                break;
            case R.id.menu_article_browser:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_article, menu);
        return true;
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            content_progress_bar.setVisibility(View.VISIBLE);
            if (newProgress == 100) {
                content_progress_bar.setVisibility(View.GONE);
            } else {
                content_progress_bar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

}
