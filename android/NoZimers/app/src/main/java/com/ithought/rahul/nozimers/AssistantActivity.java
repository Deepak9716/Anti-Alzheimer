package com.ithought.rahul.nozimers;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class AssistantActivity extends AppCompatActivity implements AIListener{

    private static final String TAG = "AssistantActivity";
    private AIService aiService;
    private TextView assistance_question_text, assistance_answer_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);

        assistance_question_text = (TextView)findViewById(R.id.assistance_question_text);
        assistance_answer_text = (TextView)findViewById(R.id.assistance_answer_text);


        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }


        final AIConfiguration config = new AIConfiguration("0c5e69cba64e4159bc510f79056d92e4",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);

        aiService.setListener(this);

        ImageView mic = (ImageView)findViewById(R.id.mic_button);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiService.startListening();
            }
        });
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.RECORD_AUDIO},
                101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 101: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                return;
            }
        }
    }


    @Override
    public void onResult(AIResponse result) {

        Log.d(TAG, "onResult: this is the result returned from dialogflow" + result.toString());
        Result result1 = result.getResult();
        assistance_question_text.setText(result1.getResolvedQuery());
        assistance_answer_text.setText(result1.getAction());
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
