package com.lew.mapleleaf.utils;

import android.content.Context;

import java.io.File;
import java.util.HashSet;

/**
 * Created by liuqian 16/7/17.
 */
public class FixDexUtils {
    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        loadedDex.clear();
    }

    public static void loadFixedDex(Context context){
        if (context == null){
            return;
        }
        File dir = context.getDir(Constants.FILE.BASE_DIR, Context.MODE_PRIVATE);
    }

    
}
