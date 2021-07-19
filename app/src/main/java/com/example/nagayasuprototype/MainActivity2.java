package com.example.nagayasuprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    Context mContext;
    Activity mActivity;
    PaintView mPaintView;

    static ArrayList<int[]> arrayList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mContext = this;
        mActivity = this;

        mPaintView = new PaintView(mContext);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapManager.getInstance().setBitmapArrayList(mPaintView.getBitmap());
                mActivity.finish();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapManager.getInstance().setBitmapArrayList(mPaintView.getBitmap());
                arrayList.add(mPaintView.getVertex());
                mPaintView.invalidate();
            }
        });
    }
}