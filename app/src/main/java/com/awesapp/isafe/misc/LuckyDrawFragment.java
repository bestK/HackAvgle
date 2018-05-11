package com.awesapp.isafe.misc;

/**
 * Created by LV on 2018/5/11.
 */
public class LuckyDrawFragment {
    public static native String computeHash(String str, String str2);

    static {
        System.loadLibrary("isafe");
    }
}
