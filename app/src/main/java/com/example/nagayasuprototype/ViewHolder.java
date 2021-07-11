package com.example.nagayasuprototype;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imageView;
    private final Button recButton;
    private final Button playButton;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        recButton = itemView.findViewById(R.id.button_rec);
        playButton = itemView.findViewById(R.id.button_play);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Button getRecButton() {
        return recButton;
    }

    public Button getPlayButton() {
        return playButton;
    }
}
