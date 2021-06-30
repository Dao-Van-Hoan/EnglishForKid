package com.example.englishforkids.Learn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishforkids.DatabaseHander;
import com.example.englishforkids.Learn.Model.Color;
import com.example.englishforkids.Learn.Model.Number;
import com.example.englishforkids.R;

import java.util.Locale;

public class Number_Detail_Activity extends AppCompatActivity {

    String IdNumber;
    Toolbar toolbar;
    TextToSpeech textToSpeech;
    DatabaseHander db = new DatabaseHander(this);
    TextView tv_number, tv_translate;
    ImageView image, imageVolum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_number_activity_detail);
        AnhXa();
        Actionbar();
        LoadData();
        ClickSpeak();
    }

    private void LoadData() {
        Bundle bundle = getIntent().getExtras();
        IdNumber = bundle.getString("ID");
        Cursor cursor = db.getCursor("select * from number where ID='" + IdNumber + "'");
        if (cursor.moveToFirst()) {
            Number al = new Number();
            al.setNumber(cursor.getString(1));
            al.setImage(cursor.getBlob(3));
            al.setTranslate(cursor.getString(2));
            Bitmap bm = BitmapFactory.decodeByteArray(al.getImage(), 0, al.getImage().length);
            tv_number.setText("" + al.getNumber());
            tv_translate.setText("" + al.getTranslate());
            image.setImageBitmap(bm);
        }
    }

    private void AnhXa() {
        tv_number = (TextView)findViewById(R.id.tv_number_speak);
        tv_translate = (TextView)findViewById(R.id.tv_translate_number);
        image = (ImageView)findViewById(R.id.imv_number);
        imageVolum = (ImageView)findViewById(R.id.imv_Volumn_number);
        toolbar = (Toolbar)findViewById(R.id.TB_Number_Detail);
    }

    private void ClickSpeak() {
        imageVolum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speak(tv_number.getText().toString());
            }
        });
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

    private void Speak(final String ab) {
        textToSpeech = new TextToSpeech(Number_Detail_Activity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(new Locale("EN"));
                    textToSpeech.speak(ab, TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}