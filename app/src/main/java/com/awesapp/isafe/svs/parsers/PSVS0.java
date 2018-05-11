package com.awesapp.isafe.svs.parsers;

import android.content.Context;

/**
 * Created by LV on 2018/5/11.
 */
public class PSVS0 {
    public static native String computeHash(String str1, String str2, String str3);

    static {
        System.loadLibrary("isafe");
    }
}
