package com.example.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.common.R;
import com.example.common.utils.DpPx;

/**
 * @author 黎亮亮
 * @Date 2024/12/6
 * @describe 渐变边框view
 */
public class GradientBorderView extends View {

    private Paint paint;
    private int[] colors;
    private Path path;
    private int width, height;
    private RectF rect;
    private Shader shader;
    private boolean isGradient;//是否渐变
    private boolean gradientVertiacl;//是否垂直渐变
    private float borderWidth;//边框宽度
    private int startColor, endColor;
    private int borderColor;//边框颜色

    public GradientBorderView(Context context) {
        this(context, null);
    }

    public GradientBorderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public GradientBorderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public GradientBorderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GradientBorderView);
        isGradient = array.getBoolean(R.styleable.GradientBorderView_is_gradient, true);
        isGradient = array.getBoolean(R.styleable.GradientBorderView_is_gradient, true);
        borderWidth = array.getDimension(R.styleable.GradientBorderView_border_width, 1);
        startColor = array.getColor(R.styleable.GradientBorderView_gradient_startColor, ContextCompat.getColor(context, R.color.color_000000));
        endColor = array.getColor(R.styleable.GradientBorderView_gradient_endColor, ContextCompat.getColor(context, R.color.color_C3C1C1));
        borderColor = array.getColor(R.styleable.GradientBorderView_border_color, ContextCompat.getColor(context, R.color.color_000000));
        gradientVertiacl = array.getBoolean(R.styleable.GradientBorderView_gradient_vertical, true);
        array.recycle();
        borderWidth = DpPx.dp2px(context, borderWidth);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        colors = new int[]{startColor, endColor};
        path = new Path();
        paint.setColor(borderColor);
        rect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        float halfBorderWidth = borderWidth / 2;
        rect.set(halfBorderWidth, halfBorderWidth, w - halfBorderWidth, h - halfBorderWidth);
        if (isGradient) {
            if (gradientVertiacl) {
                shader = new LinearGradient(width / 2, 0, width / 2, height, colors, null, Shader.TileMode.CLAMP);
            } else {
                shader = new LinearGradient(0, height / 2, width / 2, height / 2, colors, null, Shader.TileMode.CLAMP);
            }
        } else {
            shader = null;
        }
        paint.setShader(shader);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        path.lineTo(width, 0);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(0, 0);
        canvas.drawRoundRect(rect, width / 2, height / 2, paint);
    }
}
