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
import com.example.englishforkids.R;

import java.util.Locale;

public class Color_Detail_Activity extends AppCompatActivity {

    String IdColor;
    Toolbar toolbar;
    TextToSpeech textToSpeech;
    DatabaseHander db = new DatabaseHander(this);
    TextView tv_color, tv_translate;
    ImageView image, imageVolum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_color_activity_detail);
        AnhXa();
        Actionbar();
        LoadData();
        ClickSpeak();
    }

    private void LoadData() {
        Bundle bundle = getIntent().getExtras();
        IdColor = bundle.getString("ID");
        Cursor cursor = db.getCursor("select * from color where ID='" + IdColor + "'");
        if (cursor.moveToFirst()) {
            Color al = new Color();
            al.setColor(cursor.getString(1));
            al.setImage(cursor.getBlob(3));
            al.setTranslate(cursor.getString(2));
            Bitmap bm = BitmapFactory.decodeByteArray(al.getImage(), 0, al.getImage().length);
            tv_color.setText("" + al.getColor());
            tv_translate.setText("" + al.getTranslate());
            image.setImageBitmap(bm);
        }
    }

    private void AnhXa() {
        tv_color = (TextView)findViewById(R.id.tv_color);
        tv_translate = (TextView)findViewById(R.id.tv_translate);
        image = (ImageView)findViewById(R.id.imv_color);
        imageVolum = (ImageView)findViewById(R.id.imv_Volumn_color);
        toolbar = (Toolbar)findViewById(R.id.TB_Color_Detail_Activity);
    }

    private void ClickSpeak() {
        imageVolum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speak(tv_color.getText().toString());
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
        textToSpeech = new TextToSpeech(Color_Detail_Activity.this, new TextToSpeech.OnInitListener() {
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