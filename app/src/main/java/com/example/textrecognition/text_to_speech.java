package com.example.textrecognition;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class text_to_speech {
    private TextToSpeech mtts = null;
    private boolean isLoaded = false;

    public void init(Context context) {
        try {
            mtts = new TextToSpeech(context, onInitListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            Locale english = new Locale("EN", "US");
            if (status == TextToSpeech.SUCCESS) {
                int result = mtts.setLanguage(english);
                isLoaded = true;
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("Error", "Lenguaje No Permitido");
                }
            } else {
                Log.e("Error", "Falló Inicialización");
            }
        }
    };

    public void shutDown() {
        mtts.shutdown();
    }

    public void addQueue(String text) {
        if (isLoaded)
            mtts.speak(text, TextToSpeech.QUEUE_ADD, null);
        else
            Log.e("Error", "Text to Speech No Inicializado.");
    }

    public void initQueue(String text) {
        if (isLoaded)
            mtts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        else
            Log.e("Error", "Text to Speech No Inicializado.");
    }
}



