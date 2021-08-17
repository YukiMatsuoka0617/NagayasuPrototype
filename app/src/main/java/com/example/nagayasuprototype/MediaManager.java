package com.example.nagayasuprototype;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MediaManager {
    private static MediaManager mInstance;

    private final Context mContext;

    public static MediaRecorder mRecorder;
    public static String mFileName;
    public static MediaPlayer mPlayer;
    public ArrayList<Bitmap> mBitmapArrayList;

    private MediaManager(Context context) {
        mContext = context;
        mFileName = mContext.getExternalCacheDir().getAbsolutePath();
        mBitmapArrayList = BitmapManager.getInstance().getBitmapArrayList();
    }

    public static MediaManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MediaManager(context);
        }
        return mInstance;
    }

    public void startRecord(int i) {
        String filename = mFileName;
        filename += "/test" + i + ".3gp";

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(filename);
        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecord() {
        try {
            mRecorder.stop();
            mRecorder.reset();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void startPlay(int position) {
        String filename = mFileName;
        filename += "/test" + position + ".3gp";
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(filename);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.d("test", "prepare() failed");
        }
    }

    public void startPlayAndImage(int position) {
        String filename = mFileName;
        filename += "/test" + position + ".3gp";
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(filename);
            mPlayer.prepare();
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (position + 1 < mBitmapArrayList.size()) {
                        startPlayAndImage(position + 1);
                        MainActivity4.imageView.setImageDrawable(new BitmapDrawable(mBitmapArrayList.get(position + 1)));
                    }
                }
            });
        } catch (IOException e) {
            Log.d("test", "prepare() failed");
        }
    }
}
