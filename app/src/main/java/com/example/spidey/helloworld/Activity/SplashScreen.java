package com.example.spidey.helloworld.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.spidey.helloworld.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

//        Thread timer = new Thread() {
//            public void run() {
//                try {
//                    sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//
//
//                    Intent i = new Intent(SplashScreen.this, LoginActivity.class);
//
//                    startActivity(i);
//
//                }
//            }
//        };
//        timer.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        };
        new Handler().postDelayed(runnable, 3000);

    }
}
