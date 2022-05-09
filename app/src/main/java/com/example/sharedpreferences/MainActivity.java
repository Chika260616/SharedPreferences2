package com.example.sharedpreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //    Initialize variables
    ImageView imageView;
    Button btOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      Assign variables
        imageView = findViewById(R.id.imageView);
        btOpen = findViewById(R.id.btnCamera);


//        Request for camera permission

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode ==100){
//            Get capture image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
//            Set capture image to ImageView
            imageView.setImageBitmap(captureImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void openVoice(View view) {
        Intent intent = new Intent(this, VoiceActivity2.class);
        startActivity(intent);
    }
}

