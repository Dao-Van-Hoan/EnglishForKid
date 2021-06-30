package com.example.englishforkids.Learn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.englishforkids.DatabaseHander;
import com.example.englishforkids.Learn.Model.Alphabet;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class AlphabetActivity extends AppCompatActivity {

    DatabaseHander db = new DatabaseHander(this);
    ArrayList<Alphabet> arrAlpha = null;
    ArrayAdapter<Alphabet> adapApha = null;
    GridView grView;
    int ps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_alphabet_activity);
        toolbar = (Toolbar) findViewById(R.id.TB_AlphabetActivity);
        //Copy Database
        db.copyDB2SDCard();
        Actionbar();
        //Hiển thị dữ liệu từ DB vào ListView
        grView = (GridView) findViewById(R.id.gridview_alphabet);
        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AlphabetActivity.this, Alphabet_Detail_Activity.class);
                intent.putExtra("ID", arrAlpha.get(position).ID);
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
        Alphabet row;
        arrAlpha = new ArrayList<Alphabet>();
        Cursor c = db.getCursor("select * from alphabet");
        c.moveToFirst();
        do {
            row = new Alphabet();
            row.ID = c.getString(0);
            row.alpha = c.getString(1);
            arrAlpha.add(row);
        } while (c.moveToNext());
        adapApha = new ArrayAdapter<Alphabet>(this, R.layout.custom_alpha, arrAlpha);
        grView.setAdapter(adapApha);
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