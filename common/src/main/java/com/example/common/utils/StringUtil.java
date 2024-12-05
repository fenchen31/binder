package com.example.common.utils;

/**
 * @author 黎亮亮
 * @Date 2024/12/5
 * @describe
 */
public class StringUtil {
    public static boolean isEmpty(String s){
        return s == null || "".equals(s);
    }

    public static boolean isNotEmpty(String s){
        return !(s == null || "".equals(s));
    }
}
