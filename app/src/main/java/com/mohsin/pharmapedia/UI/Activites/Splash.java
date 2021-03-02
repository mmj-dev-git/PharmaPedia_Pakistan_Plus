package com.mohsin.pharmapedia.UI.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.mohsin.pharmapedia.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        startNewActivity(1000);
    }

    public void startNewActivity(int time) {

        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {

                    Intent activitySignRegister =  new Intent(Splash.this, Main.class);
                    startActivity(activitySignRegister);
                    finish();



            }
        };
        handler.postDelayed(r, time);
    }
}