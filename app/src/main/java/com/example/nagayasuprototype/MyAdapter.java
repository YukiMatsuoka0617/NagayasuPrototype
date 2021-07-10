package com.example.nagayasuprototype;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.internal.ManufacturerUtils;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<Bitmap> mBitmapArrayList;
    Context mContext;
    Activity mActivity;

    public MyAdapter(Activity activity, ArrayList<Bitmap> bitmapArrayList) {
//        mContext = context;
        mActivity = activity;
        mBitmapArrayList = bitmapArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImageView().setImageDrawable(new BitmapDrawable(mBitmapArrayList.get(position)));
        holder.getRecButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mActivity)
                        .setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mActivity.finish();
                            }
                        })
                        .show();

            }
        });
        holder.getPlayButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mBitmapArrayList.size();
    }
}
