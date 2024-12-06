package com.example.binder.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;

import androidx.core.content.ContextCompat;

import com.example.binder.R;
import com.example.binder.databinding.ItemMainNavigationBinding;
import com.example.common.utils.DpPx;
import com.example.common.view.CenterImageSpan;
import com.example.common.view.SingleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 黎亮亮
 * @Date 2024/12/6
 * @describe
 */
public class MainNavigationAdapter extends SingleAdapter<ItemMainNavigationBinding, String> {

    public static final String SELECTED = "selected";
    public static final String UNSELECTED = "unselected";
    private int selectPosition = 0;
    private HashMap<String, Integer> pictures;
    private int itemHeight = 50;

    public MainNavigationAdapter(Context context, ArrayList<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBindData(ItemMainNavigationBinding binding, String itemData) {
    }

    @Override
    public void onBindData(ItemMainNavigationBinding binding, ArrayList<String> originData, int position) {
        boolean selected = (position == selectPosition);
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append(selected ? originData.get(position) : " ");
        String key = (selected ? SELECTED : UNSELECTED) + position;
        Drawable drawable = ContextCompat.getDrawable(context, pictures.get(key));
        int height = DpPx.dp2px(context, itemHeight);
        drawable.setBounds(0, 0, height, height);
        CenterImageSpan imageSpan = new CenterImageSpan(drawable);
        SpannableString spannableString = new SpannableString(builder.toString());
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.tvText.setText(spannableString);
        binding.tvText.setBackground(selected ? ContextCompat.getDrawable(context, R.drawable.shape_radius40) : null);
        binding.getRoot().setOnClickListener(v -> {
            setSelectPosition(position);
        });
    }

    public void setPicture(HashMap<String, Integer> pictures) {
        this.pictures = pictures;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    public void setSelectPosition(int selectPosition) {
        if (this.selectPosition != selectPosition) {
            notifyItemChanged(this.selectPosition);
            this.selectPosition = selectPosition;
            notifyItemChanged(selectPosition);
        }
    }
}
