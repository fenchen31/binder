package com.example.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe  单布局RecyclerView的adapter
 */
public abstract class SingleAdapter<Binding extends ViewDataBinding,ItemType> extends RecyclerView.Adapter<SingleViewHolder>{

    public ArrayList<ItemType> originData;
    public Context context;
    private int layoutId;

    public SingleAdapter(Context context, ArrayList<ItemType> data, int layoutId) {
        this.originData = data;
        this.context = context;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
        return new SingleViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder holder, int position) {
        Binding binding = DataBindingUtil.getBinding(holder.itemView);
        onBindData(binding, originData, position);
    }

    @Override
    public int getItemCount() {
        return originData == null ? 0 : originData.size();
    }

    public abstract void onBindData(Binding binding, ItemType itemData);

    public void onBindData(Binding binding, ArrayList<ItemType> originData, int position){
        onBindData(binding, originData.get(position));
    }
}
