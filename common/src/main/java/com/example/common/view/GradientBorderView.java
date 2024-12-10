package com.example.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
 *                  使用
 *         app:border_width="2dp"
 *         app:corners="30dp"
 *         app:is_gradient="true"
 */
public class GradientBorderView extends View {

    private Paint paint;
    private int[] colors;
    private RectF rect;
    private Shader shader;
    private boolean isGradient;//是否是渐变，否则为单一颜色，取值是borderColor
    private boolean gradientVertiacl;//是否垂直渐变,否则为水平渐变
    private float borderWidth;//边框宽度
    private int startColor, endColor;
    private int borderColor;//边框颜色
    private float corners;

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
        borderWidth = array.getDimension(R.styleable.GradientBorderView_border_width, 1);
        startColor = array.getColor(R.styleable.GradientBorderView_gradient_startColor, ContextCompat.getColor(context, R.color.color_000000));
        endColor = array.getColor(R.styleable.GradientBorderView_gradient_endColor, ContextCompat.getColor(context, R.color.color_C3C1C1));
        borderColor = array.getColor(R.styleable.GradientBorderView_border_color, ContextCompat.getColor(context, R.color.color_000000));
        gradientVertiacl = array.getBoolean(R.styleable.GradientBorderView_gradient_vertical, true);
        corners = array.getDimension(R.styleable.GradientBorderView_corners, 5);
        array.recycle();
        borderWidth = DpPx.dp2px(context, borderWidth);
        corners = DpPx.dp2px(context, corners);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.STROKE);
        colors = new int[]{startColor, endColor};
        paint.setColor(borderColor);
        rect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float halfBorderWidth = borderWidth / 2;
        rect.set(halfBorderWidth, halfBorderWidth, w - halfBorderWidth, h - halfBorderWidth);
        if (isGradient) {
            if (gradientVertiacl) {
                shader = new LinearGradient(w / 2, 0, w / 2, h, colors, null, Shader.TileMode.CLAMP);
            } else {
                shader = new LinearGradient(0, h / 2, w / 2, h / 2, colors, null, Shader.TileMode.CLAMP);
            }
        } else {
            shader = null;
        }
        paint.setShader(shader);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRoundRect(rect, corners, corners, paint);
    }
}
