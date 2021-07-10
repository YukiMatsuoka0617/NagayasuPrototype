package com.example.nagayasuprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context mContext;
    public static ArrayList<Bitmap> bitmapArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity2.class);
                startActivity(intent);
            }
        });

        Button buttonList = findViewById(R.id.button_list);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MainActivity3.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test",""+bitmapArrayList);
        for (Bitmap bitmap : bitmapArrayList){
            Log.d("test",""+bitmap.getWidth()+","+bitmap.getHeight());
        }
    }

    public static void setBitmapArrayList(Bitmap bitmap){
        bitmapArrayList.add(bitmap);
    }

    public static ArrayList<Bitmap> getBitmapArrayList(){
        return  bitmapArrayList;
    }
}