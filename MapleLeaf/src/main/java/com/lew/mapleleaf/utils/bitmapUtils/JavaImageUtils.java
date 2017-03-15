package com.lew.mapleleaf.utils.bitmapUtils;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by liuqian 16/7/9.
 */
public class JavaImageUtils {
    public static final float BRIGHTNESS = 0.2f;
    public static final float CONSTRATE = 0.2f;

    public static Bitmap getImage(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int a, r, g, b;
        //调用亮度
        int bright = (int) (255 * BRIGHTNESS);
        //对比度
        float ca = 1.0f + CONSTRATE;
        ca *= ca;
        int cab = (int) ((ca * 65536) + 1);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //获取每一个像素点的颜色
                int color = bitmap.getPixel(x, y);
                a = Color.alpha(color);
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);
                int ri = r - bright;
                int gi = g - bright;
                int bi = b - bright;
                // 检查边界
                r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
                // 变化对比度
                ri = r - 128;
                gi = g - 128;
                bi = b - 128;
                ri = (ri * bright) >> 16;
                gi = (gi * bright) >> 16;
                bi = (bi * bright) >> 16;
                ri = ri + 128;
                gi = gi + 128;
                bi = bi + 128;
                // 检查边界
                r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);
                result.setPixel(x, y, Color.argb(a, r, g, b));
            }
        }
        return result;
    }
}
