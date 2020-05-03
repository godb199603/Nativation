package com.example.nativation.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nativation.R;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者：Create on 2020/4/2 20:21  by  wzl
 * 描述：
 * 最近修改：2020/4/2 20:21 modify by wzl
 */
public class PagingAdapter extends PagedListAdapter<ArticleResponse.DataBean.DatasBean, PagingAdapter.ViewHolder> {

    protected PagingAdapter() {
        super(new DiffUtil.ItemCallback<ArticleResponse.DataBean.DatasBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull ArticleResponse.DataBean.DatasBean oldItem, @NonNull ArticleResponse.DataBean.DatasBean newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ArticleResponse.DataBean.DatasBean oldItem, @NonNull ArticleResponse.DataBean.DatasBean newItem) {
//                比较内容是否相等
//                return oldItem.equals(newItem);
                return  oldItem.getTitle().equals(newItem.getTitle());
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paginglistlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleResponse.DataBean.DatasBean bean = getItem(position);
//        容器可能为空
//        if(bean==null)  holder.tvname.setText("LOADONG");
        holder.tvname.setText(bean.getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
        }
    }
}

