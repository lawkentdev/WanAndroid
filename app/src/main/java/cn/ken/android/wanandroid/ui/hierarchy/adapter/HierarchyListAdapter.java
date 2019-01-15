package cn.ken.android.wanandroid.ui.hierarchy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.ken.android.wanandroid.R;
import cn.ken.android.wanandroid.core.bean.hierarchy.KnowledgeHierarchyData;
import cn.ken.android.wanandroid.ui.hierarchy.activity.HierarchyChildrenActivity;
import cn.ken.android.wanandroid.utils.LogUtil;

public class HierarchyListAdapter extends RecyclerView.Adapter<HierarchyListAdapter.ViewHolder> {

    List<KnowledgeHierarchyData> hierarchyDataList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView article_title;
        TextView article_children_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_title = (TextView) itemView.findViewById(R.id.knowledge_article_list_title);
            article_children_name = (TextView) itemView.findViewById(R.id.knowledge_article_list_children_name);
        }
    }

    public HierarchyListAdapter(List<KnowledgeHierarchyData> hierarchyDataList) {
        this.hierarchyDataList = hierarchyDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge_frag_article_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final KnowledgeHierarchyData datas = hierarchyDataList.get(position);
        holder.article_title.setText(datas.getName());
        String childrenNname = "";
        for (KnowledgeHierarchyData childrenDatas : datas.getChildren()) {
            childrenNname += childrenDatas.getName() + "   ";
        }
        holder.article_children_name.setText(childrenNname);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HierarchyChildrenActivity.actionStart(v.getContext(), datas);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hierarchyDataList.size();
    }

}
