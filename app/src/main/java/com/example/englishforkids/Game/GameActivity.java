package com.example.englishforkids.Game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.englishforkids.MenuActivity;
import com.example.englishforkids.R;

public class GameActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgArt, imgPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        AnhXa();
        Actionbar();
        setOnClick();
    }

    private void setOnClick() {
        imgArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameActivity.this, ArtActivity.class);
                startActivity(intent);
            }
        });
        imgPaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameActivity.this, PaintActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.TB_GameActivity);
        imgArt = (ImageView) findViewById(R.id.imgArt);
        imgPaint = (ImageView) findViewById(R.id.imgPaint);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}