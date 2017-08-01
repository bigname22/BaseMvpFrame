package com.baobing.cc.basemvpframemaster01.other.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * author:Created by LiangSJ
 * date: 2017/6/1.
 * description:通用Rv的适配器以及viewholder
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter  {
    private Context context;
    private List<T> datas;
    private int resId;
    private LayoutInflater inflater;
    OnItemClickListener onItemClickListener;


    public BaseRvAdapter(Context context, List<T> datas, int resId) {
        this.context = context;
        this.datas = datas;
        this.resId = resId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        resId = getLayoutId(viewType);
        return BaseRvViewHolder.getInstance(parent,resId);
    }

    public abstract int getLayoutId(int viewType) ;

    public abstract void convert(BaseRvViewHolder holder, T t);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        BaseRvViewHolder holder1 = (BaseRvViewHolder) holder;
        ((BaseRvViewHolder) holder).convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(datas.get(position),position);
                }
            }
        });
        convert(holder1,datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }



    public static class BaseRvViewHolder extends RecyclerView.ViewHolder{
        SparseArray<View> childViews;
        View convertView;


        private BaseRvViewHolder(View itemView) {
            super(itemView);
            convertView = itemView;
            childViews = new SparseArray<>();
        }

        public static BaseRvViewHolder getInstance(ViewGroup parent,int resId) {
            View item = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
            return new BaseRvViewHolder(item);
        }

        public <T extends View> T getView(int viewId) {
            View view = childViews.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                childViews.put(viewId,view);
            }
            return (T)view;
        }

        }

    /*
    * 点击接口，把事件外传给用户自定义
    * */
    public interface OnItemClickListener<T>{
        void onItemClick(T t, int position);
    }
}



