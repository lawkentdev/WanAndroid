package cn.ken.android.wanandroid.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;  //说明是带有Header的
    public static final int ITEM_TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int ITEM_TYPE_NORMAL = 2;  //说明是不带有header和footer的

    //获取从Activity中传递过来每个item的数据集合
    private List<String> mHomeArticleList;
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                return;
            }
            tv = (TextView) itemView.findViewById(R.id.home_frag_tv);
        }
    }

    public HomeListAdapter(List<String> mHomeArticleList) {
        this.mHomeArticleList = mHomeArticleList;
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return ITEM_TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return ITEM_TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return ITEM_TYPE_FOOTER;
        }
        return ITEM_TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == ITEM_TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == ITEM_TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_frag_article, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            if (holder instanceof ViewHolder) {
                // 这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.tv.setText(mHomeArticleList.get(position - 1));
                return;
            }
            return;
        } else if (getItemViewType(position) == ITEM_TYPE_HEADER) {
            return;
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        if (mHeaderView == null && mFooterView == null) {
            return mHomeArticleList.size();
        } else if (mHeaderView == null && mFooterView != null) {
            return mHomeArticleList.size() + 1;
        } else if (mHeaderView != null && mFooterView == null) {
            return mHomeArticleList.size() + 1;
        } else {
            return mHomeArticleList.size() + 2;
        }
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

}
