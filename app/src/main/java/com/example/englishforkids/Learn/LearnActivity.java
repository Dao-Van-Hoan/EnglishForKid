package com.example.englishforkids.Learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.englishforkids.Learn.Activity.AlphabetActivity;
import com.example.englishforkids.Learn.Activity.AnimalActivity;
import com.example.englishforkids.Learn.Activity.ColorActivity;
import com.example.englishforkids.Learn.Activity.NumberActivity;
import com.example.englishforkids.Learn.Activity.QuizActivity;
import com.example.englishforkids.Learn.Activity.TestActivity;
import com.example.englishforkids.R;

public class LearnActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgAlphabet, imgColor, imgAnimal, imgNumber, imgTest, imgQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        AnhXa();
        Actionbar();
        Click();
    }

    private void Click() {
        imgAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, AlphabetActivity.class);
                startActivity(intent);
            }
        });
        imgAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, AnimalActivity.class);
                startActivity(intent);
            }
        });
        imgNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, NumberActivity.class);
                startActivity(intent);
            }
        });
        imgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, ColorActivity.class);
                startActivity(intent);
            }
        });
        imgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
        imgQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.TB_LearnActivity);
        imgAlphabet = (ImageView) findViewById(R.id.imgAlphabet);
        imgAnimal = (ImageView) findViewById(R.id.imgAnimal);
        imgColor = (ImageView) findViewById(R.id.imgColor);
        imgNumber = (ImageView) findViewById(R.id.imgNumber);
        imgTest = (ImageView) findViewById(R.id.imgTest);
        imgQuiz = (ImageView) findViewById(R.id.imgQuiz);
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