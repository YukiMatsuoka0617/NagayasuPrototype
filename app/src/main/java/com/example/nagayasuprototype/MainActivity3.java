package com.example.nagayasuprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaRecorder;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {
    private MediaRecorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initRecord();
        initRecyclerView();




    }

    void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rLayoutManager);

        MyAdapter adapter = new MyAdapter(this, MainActivity.getBitmapArrayList());
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    void initRecord(){
        // MediaRecorderのインスタンスを作成
        mRecorder = new MediaRecorder();
        // マイクからの入力とする
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 記録フォーマットを3GPPに設定
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 音声コーデックをAMR-NBに設定
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // 出力ファイルパスを設定
        mRecorder.setOutputFile("/sdcard/audio_sample.3gp");
        try {
            // レコーダーを準備
            mRecorder.prepare();
        } catch(IllegalStateException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}