package com.lew.mapleleaf.utils.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;

import com.lew.mapleleaf.utils.Constants;

public class SoundUtils {

    public static final String TAG = SoundUtils.class.getSimpleName();

    static final private double EMA_FILTER = 0.6;

    private MediaRecorder recorder = null;
    private double mEMA = 0.0;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    public void playMusic(final String uri, final OnCompletionListener completeListener) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }

        if (uri.startsWith("file://")) {
            playLocalMusic(uri, completeListener);
            return;
        }

        if (uri.startsWith("http://")) {
            final File file = new File(Constants.FILE.BASE_DIR, uri.hashCode() + "");
            if (file.exists()) {
                playLocalMusic(file.getAbsolutePath(), completeListener);
            } else {
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        InputStream is = null;
                        FileOutputStream fos = null;
                        try {
                            is = new URL(uri).openStream();
                            fos = new FileOutputStream(file);
                            byte buf[] = new byte[2048];
                            int numread;
                            while ((numread = is.read(buf)) != -1) {
                                fos.write(buf, 0, numread);
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (is != null)
                                    is.close();
                                if (fos != null)
                                    fos.close();
                            } catch (IOException e) {
                            }
                        }
                        return file.getAbsolutePath();
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if (!TextUtils.isEmpty(result)) {
                            playLocalMusic(result, completeListener);
                        }
                    }
                }.execute();
            }
        }
    }

    public void playLocalMusic(final String name, OnCompletionListener completeListener) {
        try {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.setDataSource(name);
            mediaPlayer.prepare();
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(completeListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRecord(String name) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//			ToastUtils.show(ZJHApplication.getInstance(), R.string.no_storage_device);
            return;
        }
        if (recorder == null) {
            recorder = new MediaRecorder();
        } else {
            recorder.reset();
        }
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File file = new File(name);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            recorder.setOutputFile(name);
            recorder.prepare();
            recorder.start();
            mEMA = 0.0;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecord() {
        try {
            if (recorder != null) {
                recorder.stop();
                recorder.release();
                recorder = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void pauseRecord() {
        try {
            if (recorder != null) {
                recorder.stop();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void startRecord() {
        try {
            if (recorder != null) {
                recorder.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public double getAmplitude() {
        try {
            if (recorder != null)
                return (recorder.getMaxAmplitude() / 2700.0);
            else
                return 0;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }
}
