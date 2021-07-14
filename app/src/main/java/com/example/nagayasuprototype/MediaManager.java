package com.example.nagayasuprototype;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

public class MediaManager {
    private static MediaManager mInstance;

    private final Context mContext;

    public static MediaRecorder mRecorder;
    public static String mFileName;
    public static MediaPlayer mPlayer;

    private MediaManager(Context context){
        mContext = context;
        mFileName = mContext.getExternalCacheDir().getAbsolutePath();
    }

    public static MediaManager getInstance(Context context){
        if(mInstance == null){
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

}
