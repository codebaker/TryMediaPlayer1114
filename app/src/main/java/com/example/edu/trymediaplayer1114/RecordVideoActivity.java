package com.example.edu.trymediaplayer1114;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class RecordVideoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRecVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_video);

        btnRecVideo = findViewById(R.id.btnRecVideo);
        btnRecVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(intent,4000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri uri = data.getData();
            Log.e("VideoRecord",uri.toString());
            VideoView videoView = findViewById(R.id.videoView);
            videoView.setVideoURI(uri);
            videoView.start();

        }
    }
}
