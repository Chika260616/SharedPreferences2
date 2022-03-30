package com.example.sharedpreferences;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    private SharedPreferences shp;
    private EditText editSave;
    private final String save_key = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Button btnCamera = (Button) findViewById(R.id.btnCamera);

        imageView = (ImageView) findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }

    private void init()
    {
        shp = getSharedPreferences("dataRow",MODE_PRIVATE);
        editSave = findViewById(R.id.editText);
        editSave.setText(shp.getString(save_key,"Text"));
    }

    public void onClickDelete(View view) {

        SharedPreferences.Editor edit = shp.edit();
        edit.clear();
        edit.commit();
        Toast.makeText(MainActivity.this, "Text Deleted", Toast.LENGTH_SHORT).show();

    }

    public void onClickGet(View view) {
        editSave.setText(shp.getString(save_key,"empty"));
        Toast.makeText(MainActivity.this, "Text Loaded", Toast.LENGTH_SHORT).show();
    }

    public void oClickSave(View view) {
        SharedPreferences.Editor edit = shp.edit();
        edit.putString(save_key, editSave.getText().toString());
        edit.commit();
        Toast.makeText(MainActivity.this,"Text Saved",Toast.LENGTH_SHORT).show();

    }
}