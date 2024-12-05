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
public abstract class SimpleAdapter<Binding extends ViewDataBinding,ItemType> extends RecyclerView.Adapter<SimpleViewHolder>{

    public ArrayList<ItemType> originData;
    public Context context;
    private int layoutId;

    public SimpleAdapter(Context context, ArrayList<ItemType> data, int layoutId) {
        this.originData = data;
        this.context = context;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
        return new SimpleViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        Binding binding = DataBindingUtil.getBinding(holder.itemView);
        onBindData(binding, originData.get(position));
        onBindData(binding, originData, position);
    }

    @Override
    public int getItemCount() {
        return originData == null ? 0 : originData.size();
    }

    public abstract void onBindData(Binding binding, ItemType itemData);

    public void onBindData(Binding binding, ArrayList<ItemType> originData, int position){}
}
