package com.example.edu.trymediaplayer1114;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonPaly,buttonPause,buttonFF,buttonRW,btnRecord,btnRecoreVideo;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requirePermission();

        buttonPaly = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonFF= findViewById(R.id.buttonFF);
        buttonRW= findViewById(R.id.buttonRW);
        btnRecord= findViewById(R.id.btnRecord);
        btnRecoreVideo= findViewById(R.id.btnRecoreVideo);

        buttonPause.setOnClickListener(this);
        buttonPaly.setOnClickListener(this);
        buttonFF.setOnClickListener(this);
        buttonRW.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnRecoreVideo.setOnClickListener(this);

        setButtons(null);

        mediaPlayer = MediaPlayer.create(this, R.raw.vibe);
    }

    private void requirePermission(){
        String[] permissions = new String[] {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,Manifest.permission.RECORD_AUDIO};
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission: permissions) {
            if( ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_DENIED){
                //권한 거부 당했을때,요청할 권한들을 add한다
                listPermissionsNeeded.add(permission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()){
            //권한 요청
            listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Button btn = (Button)v;
        switch (v.getId()){
            case R.id.buttonPlay:
                mediaPlayer.start();
                setButtons(btn);
                break;
            case R.id.buttonPause:
                mediaPlayer.pause();
                setButtons(btn);
                break;
            case R.id.buttonFF:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+15000);
                break;
            case R.id.buttonRW:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-15000);
                break;
            case R.id.btnRecord:
                intent = new Intent(this,AudioRecordActivity.class);
                startActivityForResult(intent,2000);
                break;
            case R.id.btnRecoreVideo:
                intent = new Intent(this,RecordVideoActivity.class);
                startActivityForResult(intent,3000);
                break;
        }
        setButtons(btn);
    }

    private void setButtons(@Nullable Button btn){
        buttonPaly.setEnabled(true);
        buttonPause.setEnabled(false);
        buttonFF.setEnabled(false);
        buttonRW.setEnabled(false);
        if(btn!=null){
            if (btn.getId()==buttonPaly.getId()) {
                btn.setEnabled(false);
                buttonPause.setEnabled(true);
                buttonFF.setEnabled(true);
                buttonRW.setEnabled(true);
            }
            if (mediaPlayer.isPlaying()) {
                buttonPaly.setEnabled(false);
                buttonPause.setEnabled(true);
                buttonFF.setEnabled(true);
                buttonRW.setEnabled(true);
            }
        }


    }
}
