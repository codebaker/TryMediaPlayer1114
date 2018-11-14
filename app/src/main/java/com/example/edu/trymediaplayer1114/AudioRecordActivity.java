package com.example.edu.trymediaplayer1114;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class AudioRecordActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnPlay,btnStop,btnRecord;
    private MediaRecorder myAudioRecorder;
    private String outputFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        btnRecord = findViewById(R.id.btnRecord);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnRecord.setOnClickListener(this);

        btnStop.setEnabled(false);
        btnPlay.setEnabled(false);

        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);

    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnRecord:
                    btnRecord.setEnabled(false);
                    btnStop.setEnabled(true);
                    myAudioRecorder.prepare();
                    myAudioRecorder.start();
                    break;
                case R.id.btnPlay:
                    btnStop.setEnabled(true);
                    btnPlay.setEnabled(false);
                    MediaPlayer player = new MediaPlayer();
                    player.setDataSource(outputFile);
                    player.start();
                    break;
                case R.id.btnStop:
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);
                    myAudioRecorder.stop();
                    myAudioRecorder.release();
                    myAudioRecorder=null;
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
