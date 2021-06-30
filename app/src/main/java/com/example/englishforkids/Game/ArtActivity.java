package com.example.englishforkids.Game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.englishforkids.R;

public class ArtActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    Canvas canvas;
    Paint paint;
    Bitmap b;
    ImageView img;
    Button btClear, btYellow, btGreen, btRed, btBlack, btBlue;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    Integer kt = 0;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);
        AnhXa();
        Actionbar();
        SelectColor();
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

    private void SelectColor() {
        btGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kt = 1;
            }
        });
        btBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kt = 2;
            }
        });
        btBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kt = 3;
            }
        });
        btRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kt = 4;
            }
        });
        btYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kt = 5;
            }
        });
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.TB_ArtActivity);
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnTouchListener(this);
        btClear = (Button) findViewById(R.id.btxoa);
        btGreen = (Button) findViewById(R.id.btluc);
        btRed = (Button) findViewById(R.id.btdo);
        btBlack = (Button) findViewById(R.id.btden);
        btYellow = (Button) findViewById(R.id.btvang);
        btBlue = (Button) findViewById(R.id.btlam);
        b = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setDither(false);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        canvas = new Canvas(b);
        canvas.drawColor(Color.WHITE);
        img.setImageBitmap(b);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canvas.drawColor(Color.WHITE);
                img.invalidate();
            }
        });
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                upy = event.getY();
                if(kt==1) paint.setColor(Color.GREEN);
                if(kt==2) paint.setColor(Color.BLUE);
                if(kt==3) paint.setColor(Color.BLACK);
                if(kt==4) paint.setColor(Color.RED);
                if(kt==5) paint.setColor(Color.YELLOW);
                canvas.drawLine(downx, downy, upx, upy, paint);
                img.invalidate();
                downx = upx;
                downy = upy;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }

    public void onClick(View v){

    }
}