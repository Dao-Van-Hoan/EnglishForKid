package com.example.englishforkids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.englishforkids.Game.GameActivity;
import com.example.englishforkids.Learn.LearnActivity;
import com.example.englishforkids.Music.MusicActivity;
import com.example.englishforkids.Video.VideoActivity;

public class MenuActivity extends AppCompatActivity {

    ImageView imgLearn, imgGame, imgMusic, imgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._menu_activity);
        AnhXa();
        Click();
    }

    private void Click() {
        imgLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, LearnActivity.class);
                startActivity(intent);
            }
        });
        imgGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        imgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
        imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MenuActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        imgLearn=(ImageView)findViewById(R.id.imgLearn);
        imgGame=(ImageView)findViewById(R.id.imgGame);
        imgMusic=(ImageView)findViewById(R.id.imgMusic);
        imgVideo=(ImageView)findViewById(R.id.imgVideo);
    }
}