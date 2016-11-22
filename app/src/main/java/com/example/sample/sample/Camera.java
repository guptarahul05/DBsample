package com.example.sample.sample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

import static android.net.Uri.fromFile;

public class Camera extends AppCompatActivity {

    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        button = (Button) findViewById(R.id.snap);
        imageView = (ImageView) findViewById(R.id.camimg);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent camera_intend = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intend.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intend,CAM_REQUEST);

            }

        });
    }
    private File getFile(){
       File folder = new File("sdcard/cameratest");
        if(!folder.exists()){
            folder.mkdir();
        }
        File image_file = new File(folder,"camtest.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/cameratest/camtest.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}

