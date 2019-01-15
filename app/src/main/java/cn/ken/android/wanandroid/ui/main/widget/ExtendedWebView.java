package cn.ken.android.wanandroid.ui.main.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * 主要解决viewPager嵌套webView横向滚动问题
 */

public class ExtendedWebView extends WebView {

    public ExtendedWebView(Context context) {
        super(context);
    }

    public ExtendedWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExtendedWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滑轮处理
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        if (callback != null)
            return callback.onGenericMotionEvent(event);
        return super.onGenericMotionEvent(event);
    }

    //定义一个接口，把滚动事件传递出去
    public interface GenericMotionCallback {
        boolean onGenericMotionEvent(MotionEvent event);
    }

    GenericMotionCallback callback;

    public void setCallback(GenericMotionCallback callback) {
        this.callback = callback;
    }

}
