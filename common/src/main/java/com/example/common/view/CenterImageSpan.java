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
 *      2.在xml中设置android:gravity="center"
 *      3.当textview只有图片没有文字时，就需要给两个占位符，否则没有图片效果(一个给图片用，一个给文字用，虽然这种情况下没有文字)
 *      使用方式如下：
 *      private void setSelectView(TextView v) {
 *         StringBuilder builder = new StringBuilder();
 *         builder.append(" ");
 *         builder.append(v.getText());
 *         SpannableString spannableString = new SpannableString(builder.toString());
 *         Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_beautiful_girl);
 *         int height = DpPx.dp2px(this, 100);
 *         drawable.setBounds(0, 0, height, height);
 *         CenterImageSpan imageSpan = new CenterImageSpan(drawable);
 *         spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
 *         v.setText(spannableString);
 *         v.setIncludeFontPadding(false);
 *     }
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
