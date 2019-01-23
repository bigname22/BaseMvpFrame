package com.example.administrator.mvpframedemo.test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mvpframedemo.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 *  createBy: bigname
 *  desc: recyclerview: 新列表控件
 *        规范了ViewHolder的实现；封装隐藏了converView；简化了那些重复操作。提供onCreateViewHolder；onBindViewHolder两个方法去定制化
 * */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    private List<String> data;

    public RvAdapter (List<String> data) {
        this.data = data;
    }


    /**
     *  需要构建ViewHolder，确认viewholder的布局元素
     * */
    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_test, viewGroup,false);
        return new RvViewHolder(itemView);
    }

    /**
     *  元素与数据绑定
     * */
    @Override
    public void onBindViewHolder(@NonNull RvViewHolder rvViewHolder, int position) {
        String itemData = data.get(position);
        rvViewHolder.tv.setText(itemData);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // ViewHolder
    public static class RvViewHolder extends RecyclerView.ViewHolder{
        View item;
        TextView tv;
        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            tv = (TextView)itemView.findViewById(R.id.tv_name);
        }
    }

}
