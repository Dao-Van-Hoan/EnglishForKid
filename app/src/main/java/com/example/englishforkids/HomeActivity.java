package com.example.englishforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private static int SplashScreen = 5000;
    ImageView imgEng, imgFor, imgKids;
    public MediaPlayer MP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout._home_activity);
        MP = MediaPlayer.create(this, R.raw.music_open);
        MP.start();
        AnhXa();
    }

    private void AnhXa() {
        imgEng = (ImageView) findViewById(R.id.imgEnglish);
        imgFor = (ImageView) findViewById(R.id.imgFor);
        imgKids = (ImageView) findViewById(R.id.imgKids);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_english);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_kids);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_for);
        imgEng.startAnimation(animation);
        imgKids.startAnimation(animation1);
        imgFor.startAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, SplashScreen);
    }

}