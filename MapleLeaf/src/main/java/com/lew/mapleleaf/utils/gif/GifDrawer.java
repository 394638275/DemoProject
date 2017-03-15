package com.lew.mapleleaf.utils.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Gif加载
 * Created by liuqian 16/7/10.
 */
public class GifDrawer {
    private static final int FRAME_DUALRATION = 16;
    private InputStream inputStream;
    private ImageView mImageView;
    private Movie mMovie;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            draw();
            mHandler.postDelayed(mRunnable, FRAME_DUALRATION);
        }

        private void draw() {
            mCanvas.save();
            mMovie.draw(mCanvas, 0, 0);
            mCanvas.restore();
            mMovie.setTime((int) (System.currentTimeMillis() % mMovie.duration()));
            mImageView.setImageBitmap(mBitmap);
        }
    };

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public ImageView getmImageView() {
        return mImageView;
    }

    public void into(ImageView mImageView) {
        this.mImageView = mImageView;
        if(inputStream == null){
            return;
        } else if(mImageView == null){
            throw new RuntimeException("imageView can not be null");
        } else {

            mMovie = Movie.decodeStream(inputStream);
            if (mMovie == null){
                throw new IllegalArgumentException("Illegal gif source");
            }
            if (mMovie.width() <= 0 || mMovie.height() <= 0){
                return;
            }
            // 需要一个bitmap
            mBitmap = Bitmap.createBitmap(mMovie.width(), mMovie.height(), Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);

        }
    }
}
