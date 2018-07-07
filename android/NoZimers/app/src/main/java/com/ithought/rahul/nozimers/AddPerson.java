package com.ithought.rahul.nozimers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddPerson extends AppCompatActivity {

    private EditText livesIn, age, place, time, relation, notes, naam;
    private Button submit;
    private String naamm,livee,agee,placee,timee,relationn,notess,temp;
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        naam = (EditText)findViewById(R.id.nishant);
        livesIn = (EditText)findViewById(R.id.livesIn);
        age = (EditText)findViewById(R.id.age);
        place = (EditText)findViewById(R.id.PlaceOfMeeting);
        time = (EditText)findViewById(R.id.Time);
        relation = (EditText)findViewById(R.id.Relation);
        notes = (EditText)findViewById(R.id.Notes);
        submit = (Button)findViewById(R.id.submit);


        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        // textView is the TextView view that should display it

        time.setText(currentDateTimeString);


        temp = getIntent().getStringExtra("pic");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                naamm = naam.getText().toString();
                livee = livesIn.getText().toString();
                agee = age.getText().toString();
                placee = place.getText().toString();
                timee = time.getText().toString();
                relationn = relation.getText().toString();
                notess = notes.getText().toString();


                HashMap postData = new HashMap();
                postData.put("image", temp);
                postData.put("name",naamm);
                postData.put("livesIn",livee);
                postData.put("age",agee);
                postData.put("placeOfMeeting",placee);
                postData.put("timeOfMeeting",timee);
                postData.put("relation",relationn);
                postData.put("notes",notess);

                String url = "http://192.168.4.152:5000/saveprofile/";


                PostResponseAsyncTask readTask = new PostResponseAsyncTask(AddPerson.this, postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        Intent i = new Intent(AddPerson.this,MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                });
                readTask.execute(url);

            }
        });









    }
}
