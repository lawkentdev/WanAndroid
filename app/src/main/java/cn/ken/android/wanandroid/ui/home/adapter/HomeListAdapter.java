package cn.ken.android.wanandroid.ui.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.ui.main.activity.ReadArticleActivity;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;  //说明是带有Header的
    public static final int ITEM_TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int ITEM_TYPE_NORMAL = 2;  //说明是不带有header和footer的

    // 获取从Activity中传递过来每个item的数据集合
    private List<FeedArticleData> mHomeArticleList;
    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tagName;
        TextView author;
        TextView niceDate;
        TextView title;
        TextView chapter;
        ImageView envelopePic;
        ImageView collection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == mHeaderView) {
                return;
            }
//            if (itemView == mFooterView) {
//                return;
//            }
            tagName = (TextView) itemView.findViewById(R.id.article_list_tags_name);
            author = (TextView) itemView.findViewById(R.id.article_list_author);
            niceDate = (TextView) itemView.findViewById(R.id.article_list_nice_date);
            title = (TextView) itemView.findViewById(R.id.article_list_title);
            chapter = (TextView) itemView.findViewById(R.id.article_list_chapter);
            collection = (ImageView) itemView.findViewById(R.id.article_list_collection);
            envelopePic = (ImageView) itemView.findViewById(R.id.article_list_envelopePic);
        }
    }

    public HomeListAdapter(List<FeedArticleData> mHomeArticleList) {
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
//        if (position == getItemCount() - 1) {
//            //最后一个,应该加载Footer
//            return ITEM_TYPE_FOOTER;
//        }
        return ITEM_TYPE_NORMAL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == ITEM_TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
//        if (mFooterView != null && viewType == ITEM_TYPE_FOOTER) {
//            return new ViewHolder(mFooterView);
//        }
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frag_article_list, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            if (holder instanceof ViewHolder) {
                // 这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
//                holder.tv.setText(mHomeArticleList.get(position - 1));
                final FeedArticleData data = mHomeArticleList.get(position - 1);
//                if (!TextUtils.isEmpty(data.getTags().get(0).getName())) {
//                    holder.tagName.setText(data.getTags().get(0).getName());
//                holder.tagName.setVisibility(View.VISIBLE);
//                }
                holder.author.setText(data.getAuthor());
                holder.niceDate.setText(data.getNiceDate());
                holder.title.setText(data.getTitle());
                holder.chapter.setText(data.getSuperChapterName() + "/" + data.getChapterName());
                if (!TextUtils.isEmpty(data.getEnvelopePic())) {
                    Glide.with(holder.itemView).load(data.getEnvelopePic()).into(holder.envelopePic);
                    holder.envelopePic.setVisibility(View.VISIBLE);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReadArticleActivity.actionStart(v.getContext(), data.getTitle(), data.getLink());
                    }
                });

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
//            return mHomeArticleList.size() + 2;
            return mHomeArticleList.size() + 1;
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
