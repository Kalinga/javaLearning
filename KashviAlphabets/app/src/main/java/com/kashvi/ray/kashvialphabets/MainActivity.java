package com.kashvi.ray.kashvialphabets;

import android.support.v7.app.AppCompatActivity;;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.content.Intent;
import java.util.Locale;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  OnInitListener {
    private static final String TAG = "RAYActivity";

    //TTS object
    private TextToSpeech myTTS;
    //status check code
    private int MY_DATA_CHECK_CODE = 0;
    private EditText mEditText;

    //create the Activity
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get a reference to the button element listed in the XML layout
        mEditText = findViewById(R.id.editor);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub

                int cursorPosition = mEditText.getSelectionStart();
                Log.v(TAG, "value of cursorPosition:" + String.valueOf(cursorPosition));

                String wholeText = s.toString();
                Log.v(TAG, "value of s:" + wholeText);
                if (wholeText.length() > 0) {
                    Log.v(TAG, "value of character:" + wholeText.charAt(cursorPosition - 1));
                    String words;
                    if (cursorPosition > 0) {
                        words = String.valueOf(s.toString().charAt(cursorPosition - 1));

                    } else {
                        words = String.valueOf(s.toString().charAt(cursorPosition));
                    }

                    Log.v(TAG, "value of word:" + words);
                    speakWords(words);
                }
            }

        });

        //check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
    }


    //speak the user text
    private void speakWords(String speech) {

        //speak straight away
        if(null != myTTS)
            myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    //act on result of TTS data check
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                //the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(this, this);
            }
            else {
                //no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            if(myTTS.isLanguageAvailable(Locale.GERMAN)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.GERMAN);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }
}