package com.example.sample.sample;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.Date;

import static android.net.Uri.fromFile;

public class GFragment extends Fragment {
    Date d = new Date();
    CharSequence s  = DateFormat.format("MM-dd-yy hh:mm:ss", d.getTime());
    String name = s.toString();

    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_g,container,false);
    }

   @Override
    public void onStart() {
        super.onStart();

        button = (Button)getView().findViewById(R.id.snap1);
        imageView = (ImageView)getView().findViewById(R.id.camimg1);
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

        File image_file = new File(folder,name+".jpg");

        return image_file;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/cameratest/"+name+".jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}

