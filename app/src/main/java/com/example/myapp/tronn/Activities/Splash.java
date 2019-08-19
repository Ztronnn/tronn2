package com.example.myapp.tronn.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.myapp.tronn.R;


public class Splash extends AppCompatActivity {

    protected int _splashTime = 5000;

    private Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Load().execute();

        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        private class Load extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.fade, R.anim.fade);

                return "executed";
            }








//        splashTread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    synchronized(this){
//                        wait(_splashTime);
//                    }
//
//                } catch(InterruptedException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    finish();
//
//                    Intent i = new Intent();
//                    i.setClass(Splash.this, MainActivity.class);
//                    startActivity(i);
//
//                    //stop();
//                }
//            }
//        };
//
//        splashTread.start();
    }

    }

