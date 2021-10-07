package com.example.nagayasuprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;

import java.io.FileDescriptor;
import java.io.IOException;

public class OpenFileViewer extends AppCompatActivity {
    private static final int RESULT_PICK_IMAGE_FILE = 1001;
    public static Bitmap mBitmap;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file_viewer);
        mContext = this;

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, RESULT_PICK_IMAGE_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == RESULT_PICK_IMAGE_FILE && resultCode == Activity.RESULT_OK) {
            if (resultData.getData() != null) {
                ParcelFileDescriptor pfDescriptor = null;
                try {
                    Uri uri = resultData.getData();
                    pfDescriptor = getContentResolver().openFileDescriptor(uri, "r");
                    if (pfDescriptor != null) {
                        FileDescriptor fileDescriptor = pfDescriptor.getFileDescriptor();
                        mBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                        pfDescriptor.close();
                        Intent intent = new Intent(mContext, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (pfDescriptor != null) {
                            pfDescriptor.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static Bitmap getBitmap() {
        return mBitmap;
    }
}