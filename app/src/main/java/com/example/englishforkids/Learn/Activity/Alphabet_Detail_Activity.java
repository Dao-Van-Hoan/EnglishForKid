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
import com.example.englishforkids.Learn.Model.Alphabet;
import com.example.englishforkids.R;

import java.util.Locale;

public class Alphabet_Detail_Activity extends AppCompatActivity {

    TextView tvAlpha, tvTXT, tvMH;
    ImageView imvAlphaS, imvTXTS, imvTXT, imvMH;
    String macc;
    Toolbar toolbar;
    TextToSpeech textToSpeech;
    DatabaseHander db = new DatabaseHander(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_alphabet_activity_detail);
        AnhXa();
        Actionbar();
        LoadData();
        ClickSpeak();
    }

    private void LoadData() {
        Bundle bundle = getIntent().getExtras();
        macc = bundle.getString("ID");
        Cursor cursor = db.getCursor("select * from alphabet where ID='" + macc + "'");
        if (cursor.moveToFirst()) {
            Alphabet al = new Alphabet();
            al.setAlpha(cursor.getString(1));
            al.setWord(cursor.getBlob(2));
            al.setTranslateWord(cursor.getString(3));
            al.setSoundWord(cursor.getString(4));
            al.setImage(cursor.getBlob(5));
            Bitmap bm2 = BitmapFactory.decodeByteArray(al.getWord(), 0, al.getWord().length);
            Bitmap bm1 = BitmapFactory.decodeByteArray(al.getImage(), 0, al.getImage().length);
            tvAlpha.setText("" + al.getAlpha());
            tvTXT.setText("" + al.getSoundWord());
            tvMH.setText("" + al.getTranslateWord());
            imvMH.setImageBitmap(bm1);
            imvTXT.setImageBitmap(bm2);
        }
    }

    private void ClickSpeak() {
        imvAlphaS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speak(tvAlpha.getText().toString());
            }
        });
        imvTXTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speak(tvTXT.getText().toString());
            }
        });
    }

    private void AnhXa() {
        tvAlpha = (TextView) findViewById(R.id.tv_alpha_speak);
        tvTXT = (TextView) findViewById(R.id.tv_txt_speak);
        tvMH = (TextView) findViewById(R.id.tv_mh);
        imvAlphaS = (ImageView) findViewById(R.id.imv_Volumn);
        imvTXTS = (ImageView) findViewById(R.id.imv_Volumn1);
        imvTXT = (ImageView) findViewById(R.id.imv_txt);
        imvMH = (ImageView) findViewById(R.id.imv_mh);
        toolbar = (Toolbar) findViewById(R.id.TB_Alphabet_Detail);
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
        textToSpeech = new TextToSpeech(Alphabet_Detail_Activity.this, new TextToSpeech.OnInitListener() {
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