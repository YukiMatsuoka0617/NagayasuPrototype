package com.example.nagayasuprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity4 extends AppCompatActivity {
    ArrayList<Bitmap> mBitmapArrayList;

    MediaManager mMediaManager;

    public static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mBitmapArrayList = BitmapManager.getInstance().getBitmapArrayList();

        Button previewStartButton = findViewById(R.id.preview_staert_button);

        imageView = findViewById(R.id.image_view);

        mMediaManager = MediaManager.getInstance(this);

        previewStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(new BitmapDrawable(mBitmapArrayList.get(0)));
                mMediaManager.startPlayAndImage(0);

            }
        });
    }
}