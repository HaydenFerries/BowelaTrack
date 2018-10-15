package com.hayden.bowelatrack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {

    ImageView logo;
    ImageView heading;

    Animation fromBottom;
    Animation fromTop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        logo = findViewById(R.id.logo);
        heading = findViewById(R.id.heading);

        int secondsDelayed = 3;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splashscreen.this,MainActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);

        fromBottom = AnimationUtils.loadAnimation(this,R.anim.translate);
        fromTop = AnimationUtils.loadAnimation(this,R.anim.translate2);

        logo.setAnimation(fromTop);
        heading.setAnimation(fromBottom);

        }
    }