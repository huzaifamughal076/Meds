package com.MP.meds.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.MP.meds.R;

public class SplashScreen extends AppCompatActivity {

    ImageView img;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        img = findViewById(R.id.img);
        animation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        img.setAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                startActivity(intent);
                finish();
            }

        },5000);
    }


    }
