package com.lew.mapleleaf.utils.gif;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by liuqian 16/7/10.
 */
public class GifDecoder {
    public static GifDecoder instance = new GifDecoder();
    public static Context mContext;

    public static GifDecoder width(Context context) {
        return instance;
    }

    private GifDecoder() {

    }

    public GifDrawer load(InputStream inputStream) {
        GifDrawer drawer = new GifDrawer();
        drawer.setInputStream(inputStream);
        return drawer;
    }
}
