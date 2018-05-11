package com.awesapp.isafe.svs.parsers;

import android.content.Context;

/**
 * Created by LV on 2018/5/11.
 */
public class PSVS21 {

    public static native String computeHash(Context context, String str, String str2);

    static {
        System.loadLibrary("isafe");
    }
}
