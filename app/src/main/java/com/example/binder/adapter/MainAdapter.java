package com.example.binder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewbinding.ViewBinding;

import com.example.binder.R;
import com.example.binder.databinding.ItemMainBinding;
import com.example.common.utils.StringUtil;
import com.example.common.view.SimpleAdapter;

import java.util.ArrayList;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe
 */
public class MainAdapter extends SimpleAdapter<ItemMainBinding, String>{

    public MainAdapter(Context context, ArrayList<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBindData(ItemMainBinding binding, String itemData) {
        if (StringUtil.isNotEmpty(itemData)){
            binding.tvText.setText(itemData);
        }
    }
}
