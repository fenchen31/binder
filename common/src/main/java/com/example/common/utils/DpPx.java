package com.example.common.utils;

import android.content.Context;

/**
 * @author 黎亮亮
 * @Date 2024/12/4
 * @describe
 */
public class DpPx {
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static int px2dp(Context context, float px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
