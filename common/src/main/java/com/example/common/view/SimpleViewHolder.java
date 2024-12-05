package com.example.common.view;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe
 */
public class SimpleViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder{

    public Binding binding;

    public SimpleViewHolder(Binding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
