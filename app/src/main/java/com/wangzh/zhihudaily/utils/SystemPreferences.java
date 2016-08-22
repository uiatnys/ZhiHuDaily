package com.wangzh.zhihudaily.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WangZH on 2016/8/22.
 */
public class SystemPreferences {


    private static final String SYSTEM_VALUES_NAME = "system_value";
    /**主题日报列表*/
    public static final String SYSTEM_VALUE_THEME_LIST = "theme_list";

    /**
     * 写入系统性参数
     * @param ctx
     * @param key
     * @param value
     */
    public static void writeSystemValue(Context ctx, String key, String value) {
        SharedPreferences system_value = ctx.getSharedPreferences(
                SYSTEM_VALUES_NAME, 0);
        SharedPreferences.Editor eidt = system_value.edit();
        eidt.putString(key, value);
        eidt.commit();
    }

    /**
     * 读出系统性参数
     * @param ctx
     * @param key
     * @return
     */
    public static String readSystemValue(Context ctx, String key) {
        return readSystemValue(ctx, key, "");
    }

    public static String readSystemValue(Context ctx, String key,
                                         String defaultvalue) {
        SharedPreferences system_value = ctx.getSharedPreferences(
                SYSTEM_VALUES_NAME, 0);
        return system_value.getString(key, defaultvalue);
    }

    /**
     * 写入系统性参数-int
     * @param ctx
     * @param key
     * @param value
     */
    public static void writeSystemIntValue(Context ctx, String key, int value){
        SharedPreferences system_value = ctx.getSharedPreferences(SYSTEM_VALUES_NAME, 0);
        SharedPreferences.Editor eidt = system_value.edit();
        eidt.putInt(key, value);
        eidt.commit();
    }

    /**
     * 读出系统性参数-int
     * @param ctx
     * @param key
     * @return
     */
    public static int readSystemIntValue(Context ctx, String key){
        return readSystemIntValue(ctx, key, 0);
    }
    public static int readSystemIntValue(Context ctx, String key, int defaultvalue){
        SharedPreferences system_value = ctx.getSharedPreferences(SYSTEM_VALUES_NAME, 0);
        return system_value.getInt(key, defaultvalue);
    }
    /**
     * 写入系统性参数-bool
     * @param ctx
     * @param key
     * @param value
     */
    public static void writeSystemBooleanValue(Context ctx, String key, boolean value){
        SharedPreferences system_value = ctx.getSharedPreferences(SYSTEM_VALUES_NAME, 0);
        SharedPreferences.Editor eidt = system_value.edit();
        eidt.putBoolean(key, value);
        eidt.commit();
    }
    /**
     * 读出系统性参数-bool
     * @param ctx
     * @param key
     * @return
     */
    public static boolean readSystemBooleanValue(Context ctx, String key){
        return readSystemBooleanValue(ctx, key, false);
    }
    public static boolean readSystemBooleanValue(Context ctx, String key, Boolean defaultvalue){
        SharedPreferences system_value = ctx.getSharedPreferences(SYSTEM_VALUES_NAME, 0);
        return system_value.getBoolean(key, defaultvalue);
    }

}
