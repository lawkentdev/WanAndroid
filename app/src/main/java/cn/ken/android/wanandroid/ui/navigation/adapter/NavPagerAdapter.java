package cn.ken.android.wanandroid.ui.navigation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Random;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.main.collect.FeedArticleData;
import cn.ken.android.wanandroid.core.bean.navigation.NavigationListData;
import cn.ken.android.wanandroid.ui.main.activity.ReadArticleActivity;
import cn.ken.android.wanandroid.utils.LogUtil;

public class NavPagerAdapter extends RecyclerView.Adapter<NavPagerAdapter.ViewHolder> {
    List<NavigationListData> navigationListDataList;
    Context mContext;
    int[] tagTextColors = {R.color.navigation_flow_text_view_c0, R.color.navigation_flow_text_view_c1,
            R.color.navigation_flow_text_view_c2, R.color.navigation_flow_text_view_c3,
            R.color.navigation_flow_text_view_c4, R.color.navigation_flow_text_view_c5,
            R.color.navigation_flow_text_view_c6, R.color.navigation_flow_text_view_c7,
            R.color.navigation_flow_text_view_c8, R.color.navigation_flow_text_view_c9,
            R.color.navigation_flow_text_view_c10, R.color.navigation_flow_text_view_c11,
            R.color.navigation_flow_text_view_c12, R.color.navigation_flow_text_view_c13,
            R.color.navigation_flow_text_view_c14, R.color.navigation_flow_text_view_c15,
            R.color.navigation_flow_text_view_c16, R.color.navigation_flow_text_view_c17,
            R.color.navigation_flow_text_view_c18, R.color.navigation_flow_text_view_c19};
    Random r = new Random();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TagFlowLayout tagFlowLayout;
        TextView flowTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagFlowLayout = (TagFlowLayout) itemView.findViewById(R.id.item_nav_frag_pager_tab_flow_layout);
            flowTitle = (TextView) itemView.findViewById(R.id.item_nav_flow_title);
        }
    }

    public NavPagerAdapter(Context context, List<NavigationListData> navigationListDataList1) {
        mContext = context;
        this.navigationListDataList = navigationListDataList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav_frag_recycler_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavigationListData datas = navigationListDataList.get(position);
        holder.flowTitle.setText(datas.getName());
        LogUtil.e("NavPagerAdapter", datas.getName());
        final List<FeedArticleData> articleDatas = datas.getArticles();
        holder.tagFlowLayout.setAdapter(new TagAdapter<FeedArticleData>(articleDatas) {
            @Override
            public View getView(FlowLayout parent, int position, FeedArticleData data) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_flow_layout_tag_text_view, parent, false);
                tv.setTextColor(mContext.getResources().getColor(tagTextColors[r.nextInt(20)]));
                tv.setText(data.getTitle());
                return tv;
            }
        });
        holder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                ReadArticleActivity.actionStart(view.getContext(), articleDatas.get(position).getTitle(), articleDatas.get(position).getLink());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return navigationListDataList.size();
    }

}
