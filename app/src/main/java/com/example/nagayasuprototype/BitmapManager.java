package com.example.nagayasuprototype;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class BitmapManager {
    private static BitmapManager mInstance;
    private ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();

    private BitmapManager(){
    }

    public static BitmapManager getInstance(){
        if(mInstance == null){
            mInstance = new BitmapManager();
        }
        return mInstance;
    }

    public void setBitmapArrayList(Bitmap bitmap) {
        bitmapArrayList.add(bitmap);
    }

    public ArrayList<Bitmap> getBitmapArrayList() {
        return bitmapArrayList;
    }

    public void deleteBitmapArrayList(int position) {
        bitmapArrayList.remove(position);
    }
}
