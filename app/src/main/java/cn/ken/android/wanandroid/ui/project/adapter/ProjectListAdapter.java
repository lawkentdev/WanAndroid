package cn.ken.android.wanandroid.ui.project.adapter;

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
import cn.ken.android.wanandroid.utils.LogUtil;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {
    // 获取从Activity中传递过来每个item的数据集合
    private List<FeedArticleData> feedArticleList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView describe;
        TextView author;
        TextView niceDate;

        ImageView envelopePic;
        ImageView collection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //如果是headerview或者是footerview,直接返回
//            if (itemView == mFooterView) {
//                return;
//            }
            title = (TextView) itemView.findViewById(R.id.project_frag_list_title);
            describe = (TextView) itemView.findViewById(R.id.project_frag_list_describe);
            author = (TextView) itemView.findViewById(R.id.project_frag_list_author);
            niceDate = (TextView) itemView.findViewById(R.id.project_frag_list_nice_date);
            envelopePic = (ImageView) itemView.findViewById(R.id.project_frag_list_envelope_pic);
            collection = (ImageView) itemView.findViewById(R.id.project_frag_list_collection);
        }
    }

    public ProjectListAdapter(List<FeedArticleData> feedArticleList) {
        this.feedArticleList = feedArticleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_frag_article_list, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FeedArticleData data = feedArticleList.get(position);
//                if (!TextUtils.isEmpty(data.getTags().get(0).getName())) {
//                    holder.tagName.setText(data.getTags().get(0).getName());
//                holder.tagName.setVisibility(View.VISIBLE);
//                }
        LogUtil.e("adapter", data.getTitle());
        holder.title.setText(data.getTitle());
        holder.describe.setText(data.getDesc());
        holder.author.setText(data.getAuthor());
        holder.niceDate.setText(data.getNiceDate());
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
    }

    @Override
    public int getItemCount() {
        return feedArticleList.size();
    }
}
