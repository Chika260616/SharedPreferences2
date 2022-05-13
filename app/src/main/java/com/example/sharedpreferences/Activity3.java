package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class Activity3 extends AppCompatActivity {
    private static final int VIDEO_REQUEST = 101;
    private Uri videoUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void captureVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if (videoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            videoUri = data.getData();
        }
    }
}