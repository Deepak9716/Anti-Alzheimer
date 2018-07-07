package com.ithought.rahul.nozimers;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;

public class Profile extends AppCompatActivity {

    private ImageView image;
    private TextView name;
    private TextView age;
    private TextView relation,place,notes;
    private TextView livesIn;
    private String name1,livesIn1,image1,image2,age1,relation1,place1,time1,notes1;
    private static final String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        name = (TextView)findViewById(R.id.name);
        age = (TextView)findViewById(R.id.age);
        relation = (TextView)findViewById(R.id.relation);
        place = (TextView)findViewById(R.id.place);
//        time = (TextView)findViewById(R.id.time);
        notes = (TextView)findViewById(R.id.notes);
        livesIn = (TextView)findViewById(R.id.livesIn);
        image = (ImageView)findViewById(R.id.imageView);

//        String key = getIntent().getStringExtra("profileDetails");
//        Toast.makeText(this,key,Toast.LENGTH_SHORT).show();
//
//       // String key = "{\"name\" : \"rahul pandey\", \"Lives in\" : \"delhi state\", \"college\": \"MSIT\" , \"image\" : \"http://192.168.43.49:8000/images/nitin.jpg\"}";
//
//        try
//        {
//            JSONObject jsonObject = new JSONObject(key);
//
//            name1 = jsonObject.getString("name");
//            age1 = jsonObject.getString("age");
//             livesIn1 = jsonObject.getString("livesIn");
//             image1 = jsonObject.getString("image");
//             notes1 = jsonObject.getString("notes");
//            time1 = jsonObject.getString("timeOfMeeting");
//            place1 = jsonObject.getString("placeOfMeeting");
//            relation1 = jsonObject.getString("relation");
//
//
//            Toast.makeText(Profile.this, image1, Toast.LENGTH_LONG).show();
//            //Toast.makeText(Profile.this, key, Toast.LENGTH_LONG).show();
//
//        } catch (JSONException je)
//        {
//            Log.e(tag, "Error in JSON", je);
//        }
//
//        image2 = "http://192.168.4.152:5000/images/" + image1;
//        //Toast.makeText(Profile.this, image2, Toast.LENGTH_LONG).show();
//
//
//        name.setText(name1);
//        age.setText(age1);
//        place.setText(place1);
//
//        relation.setText(relation1);
//        notes.setText(notes1);
//        livesIn.setText(livesIn1);
//
//
//        Picasso.with(Profile.this).load(image2).fit().centerCrop().into(image);
    }
}
