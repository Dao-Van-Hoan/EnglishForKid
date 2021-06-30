package com.example.englishforkids.Learn.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishforkids.DatabaseHander;
import com.example.englishforkids.Learn.Model.Quiz;
import com.example.englishforkids.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHander db;
    ArrayList<Quiz> list_quest;
    int tongCH, tlDung;
    TextView tvdiem;
    ImageView imvCauHoi;
    Button bt1, bt2, bt3, bt4, bt, btRestart;
    Quiz recent_q;
    TextToSpeech tts;
    int stt;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_quiz_activity);
        db = new DatabaseHander(this);
        db.copyDB2SDCard();
        AnhXa();
        Actionbar();
        TableToAL();
        ALtoLayout();
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        btRestart.setOnClickListener(this);
    }

    public void TableToAL() {
        list_quest = new ArrayList<>();
        String sql = "select * from quiz";
        Cursor c = db.getCursor(sql);
        if (c.moveToFirst()) {
            do {
                Quiz q = new Quiz();
                q.setID(c.getString(0));
                q.setND(c.getBlob(1));
                q.setDA1(c.getString(2));
                q.setDA2(c.getString(3));
                q.setDA3(c.getString(4));
                q.setDA4(c.getString(5));
                q.setDAD(c.getString(6));
                list_quest.add(q);
            } while (c.moveToNext());
        }
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

    public void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.TB_QuizActivity);
        tvdiem = findViewById(R.id.tvScoreq);
        imvCauHoi = findViewById(R.id.imvQuiz);
        bt1 = findViewById(R.id.btAns1q);
        bt2 = findViewById(R.id.btAns2q);
        bt3 = findViewById(R.id.btAns3q);
        bt4 = findViewById(R.id.btAns4q);
        btRestart = findViewById(R.id.btResetq);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                Locale language = new Locale("EN");
                tts.setLanguage(language);
            }
        });
        tts.setSpeechRate((float) 0.8);
    }

    public void ALtoLayout() {
        tongCH = list_quest.size();
        tlDung = 0;
        stt = 0;
        recent_q = randomQ(list_quest);
        tvdiem.setText(tlDung + " / " + tongCH);
        Bitmap bm = BitmapFactory.decodeByteArray(recent_q.getND(), 0, recent_q.getND().length);
        imvCauHoi.setImageBitmap(bm);
        bt1.setText(recent_q.getDA1());
        bt2.setText(recent_q.getDA2());
        bt3.setText(recent_q.getDA3());
        bt4.setText(recent_q.getDA4());
    }

    public Quiz randomQ(ArrayList<Quiz> questions) {
        Quiz q;
        Random rd = new Random();
        if (questions.size() > 0) {
            int cs = rd.nextInt(questions.size());
            q = questions.get(cs);
            questions.remove(cs);
            return q;
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void speakOut(String toSpeak){
        String utteranceid = UUID.randomUUID().toString();
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, utteranceid);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ktra(int id) {
        bt = findViewById(id);
        String ans = bt.getText().toString();
        if (stt == 0) {
            if (ans.equals(recent_q.getDAD())) {
                tlDung++;
                speakOut("Correct");
            } else
                speakOut("Incorrect");
            tvdiem.setText(tlDung + " / " + tongCH);
        }
        nextQuest();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void nextQuest() {
        recent_q = randomQ(list_quest);
        if (recent_q != null) {
            Bitmap bm = BitmapFactory.decodeByteArray(recent_q.getND(), 0, recent_q.getND().length);
            imvCauHoi.setImageBitmap(bm);
            bt1.setText(recent_q.getDA1());
            bt2.setText(recent_q.getDA2());
            bt3.setText(recent_q.getDA3());
            bt4.setText(recent_q.getDA4());
        } else {
            if (stt == 1) speakOut("Correct"+tlDung+" and let's restart");
            Toast.makeText(this,"Complete! Correct:"+tlDung,Toast.LENGTH_LONG).show();
            stt = 1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Restart() {
        TableToAL();
        ALtoLayout();
        speakOut("Restart");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btResetq)
            Restart();
        else
            ktra(view.getId());
    }
}