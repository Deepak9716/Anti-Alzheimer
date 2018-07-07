package com.ithought.rahul.nozimers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hypertrack.lib.HyperTrack;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 0;
    private Button capture,location;
    private Bitmap photo;
    private String temp;
    private ByteArrayOutputStream bao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = (Button)findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this,GeoLocation.class);
//                startActivity(intent);


            }
        });



        capture = (Button)findViewById(R.id.capture);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });






    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");


            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] b=baos.toByteArray();
            temp=Base64.encodeToString(b, Base64.DEFAULT);

            String url = "http://192.168.4.152:5000/connect/";

            HashMap postData = new HashMap();
            postData.put("image", temp);
            PostResponseAsyncTask readTask = new PostResponseAsyncTask(MainActivity.this, postData, new AsyncResponse() {
                @Override
                public void processFinish(String s) {

                    if (s.equals("error")){

                        Intent i = new Intent(MainActivity.this,AddPerson.class);
                        i.putExtra("pic",temp);
                        startActivity(i);

                    }else{
                        Intent intent = new Intent(MainActivity.this,Profile.class);
                        intent.putExtra("profileDetails",s);
                        startActivity(intent);
                    }


                }
            });
            readTask.execute(url);

        }
    }
}
