package com.example.raakapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    ImageView splashImg, coffeeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashImg = findViewById(R.id.splashLogo);
        coffeeImg = findViewById(R.id.coffeeSplashLogo);

        Animation fadeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        splashImg.setAnimation(fadeAnimation);
        Animation blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        coffeeImg.setAnimation(blinkAnimation);

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent moveOn = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(moveOn);
            finish(); //
        }, 4000);
    }
}