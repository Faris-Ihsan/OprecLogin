package com.example.opreclogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private ImageView logo;
    private static int splashTime = 3000;
    //aaaaaaaaaaaaaaa
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
        //aaaaaaaaaaa
        db = new DatabaseHelper(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //ngecek sesi user sebelumnya
                boolean checkSession = db.checkSession("ada");

                //Kalo ada sesi, loginnya di skip
                if (checkSession == true){
                    Intent intent = new Intent(SplashActivity.this, AfterLogin.class);
                    startActivity(intent);
                    finish();
                }
                //kalo gaada ya login dulu lah om
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },splashTime);

        Animation splashanimation = AnimationUtils.loadAnimation( this,R.anim.splashanimation);
        logo.startAnimation(splashanimation);
    }
}