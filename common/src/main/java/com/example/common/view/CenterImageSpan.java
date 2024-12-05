package com.example.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.common.utils.DpPx;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe
 */
public class CenterImageSpan extends ImageSpan {

    private Drawable drawable;
    private Context context;

    public CenterImageSpan(@NonNull Drawable drawable, Context context) {
        super(drawable);
        this.drawable = drawable;
        this.context = context;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return DpPx.px2dp(context, drawable.getIntrinsicWidth());
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Drawable drawable = getDrawable();
        //控件高度 / 2  - 图片高度 / 2
        int translateY = (top + bottom) / 2 - (drawable.getBounds().top + drawable.getBounds().bottom) / 2;
        canvas.save();
        canvas.translate(x, translateY);
        drawable.draw(canvas);
        canvas.restore();
    }
}
