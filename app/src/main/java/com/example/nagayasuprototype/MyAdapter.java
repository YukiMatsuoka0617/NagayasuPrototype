package com.example.nagayasuprototype;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Bitmap> mBitmapArrayList;
    Activity mActivity;

    public MyAdapter(Activity activity, ArrayList<Bitmap> bitmapArrayList) {
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
                        .setTitle("Recording")
                        .setMessage("contents")
                        .setView(makeView(new BitmapDrawable(mBitmapArrayList.get(position)), position))
                        .setPositiveButton("BACK", null)
                        .show();

            }
        });

        holder.getPlayButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity3.startPlay(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBitmapArrayList.size();
    }

    View makeView(Drawable drawable, int position) {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog, (ViewGroup) mActivity.findViewById(R.id.layout));

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageDrawable(drawable);

        Button buttonStart = view.findViewById(R.id.start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity3.startRecord(position);
            }
        });

        Button buttonStop = view.findViewById(R.id.stop);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity3.stopRecord();
            }
        });
        return view;
    }
}
