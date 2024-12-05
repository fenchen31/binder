package com.example.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe  单布局RecyclerView的adapter
 */
public abstract class SimpleAdapter<Type> extends RecyclerView.Adapter<SimpleViewHolder>{

    public ArrayList<Type> originData;
    public Context context;
    private LayoutInflater inflater;
    private int layoutId;

    public SimpleAdapter(Context context, ArrayList<Type> data, int layoutId) {
        this.originData = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleViewHolder(inflater.inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        onBindData(holder.itemView, originData.get(position));
        onBindData(holder.itemView, originData, position);
    }

    @Override
    public int getItemCount() {
        return originData == null ? 0 : originData.size();
    }

    public abstract void onBindData(View v, Type itemData);

    public void onBindData(View v, ArrayList<Type> originData, int position){}
}
