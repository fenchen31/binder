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
 * @describe  设置图片相对控件位置垂直居中，考虑了padding的情况
 * 注意：
 *      1.针对多行文字：在xml中设置android:singleLine="true"，不能用android:lines="1"
 */
public class CenterImageSpan extends ImageSpan {

    public CenterImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        return getDrawable().getBounds().right;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Drawable drawable = getDrawable();
        int pictureHeight = drawable.getBounds().bottom - drawable.getBounds().top;
        //图片高度 / 2  - 文字高度 / 2
        int translateY = pictureHeight / 2 - (bottom - top) / 2;
        canvas.save();
        canvas.translate(x, -translateY);
        drawable.draw(canvas);
        canvas.restore();
    }
}
