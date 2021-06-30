package com.example.englishforkids.Learn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.englishforkids.DatabaseHander;
import com.example.englishforkids.Learn.Adapter.AdapterColor;
import com.example.englishforkids.Learn.Model.Alphabet;
import com.example.englishforkids.Learn.Model.Color;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

    DatabaseHander db = new DatabaseHander(this);
    ArrayList<Color> arrColor = null;
    GridView grView;
    int ps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_color_activity);
        toolbar = (Toolbar) findViewById(R.id.TB_ColorActivity);
        //Copy Database
        db.copyDB2SDCard();
        Actionbar();
        //Hiển thị dữ liệu từ DB vào ListView
        grView = (GridView) findViewById(R.id.gridview_color);
        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ColorActivity.this, Color_Detail_Activity.class);
                intent.putExtra("ID", arrColor.get(position).ID);
                startActivity(intent);
            }
        });
        grView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ps = position;
                return false;
            }
        });
        Data2ListView();
    }

    public void Data2ListView() {
        Color row;
        arrColor = new ArrayList<Color>();
        Cursor c = db.getCursor("select * from color");
        c.moveToFirst();
        do {
            row = new Color();
            row.ID = c.getString(0);
            row.image = c.getBlob(3);
            arrColor.add(row);
        } while (c.moveToNext());
        AdapterColor adapColor = new AdapterColor(arrColor, getApplicationContext());
        grView.setAdapter(adapColor);
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