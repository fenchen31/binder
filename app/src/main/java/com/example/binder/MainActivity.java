package com.example.binder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.example.binder.adapter.MainAdapter;
import com.example.binder.databinding.ActivityMainBinding;
import com.example.binder.databinding.ItemMainBinding;
import com.example.common.utils.DpPx;
import com.example.common.view.CenterImageSpan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setView(binding.tvText);
        setView(binding.tvText2);
        setRecyclerView();
    }

    private void setRecyclerView(){
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String s = "这是第" + i + "个数据";
            data.add(s);
        }
        MainAdapter adapter = new MainAdapter(this, data, R.layout.item_main);
        binding.rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rvMain.setAdapter(adapter);
    }

    private void setView(TextView v){
        int height = DpPx.dp2px(this, 100);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(0, heightSpec);
        int childHeight = v.getMeasuredHeight() - v.getPaddingBottom() - v.getPaddingTop();
        setPicture(v, R.mipmap.ic_beautiful_girl, childHeight);
    }

    private void setPicture(TextView v, int resourceId, int remainHeight){
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append(v.getText().toString());
        Drawable drawable = ContextCompat.getDrawable(this, resourceId);
        drawable.setBounds(0, 0, remainHeight, remainHeight);
        CenterImageSpan span = new CenterImageSpan(drawable, this);
        SpannableString text = new SpannableString(builder.toString());
        text.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        v.setText(text);
    }
}