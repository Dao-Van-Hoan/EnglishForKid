package com.example.englishforkids.Learn.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.englishforkids.DatabaseHander;
import com.example.englishforkids.Learn.Adapter.AdapterColor;
import com.example.englishforkids.Learn.Adapter.AdapterNumber;
import com.example.englishforkids.Learn.Model.Color;
import com.example.englishforkids.Learn.Model.Number;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {

    DatabaseHander db = new DatabaseHander(this);
    ArrayList<Number> arrNumber = null;
    GridView grView;
    int ps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_number_activity);
        toolbar = (Toolbar) findViewById(R.id.TB_NumberActivity);
        //Copy Database
        db.copyDB2SDCard();
        Actionbar();
        //Hiển thị dữ liệu từ DB vào ListView
        grView = (GridView) findViewById(R.id.gridview_number);
        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(NumberActivity.this, Number_Detail_Activity.class);
                intent.putExtra("ID", arrNumber.get(position).ID);
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
        Number row;
        arrNumber = new ArrayList<Number>();
        Cursor c = db.getCursor("select * from number");
        c.moveToFirst();
        do {
            row = new Number();
            row.ID = c.getString(0);
            row.image = c.getBlob(3);
            arrNumber.add(row);
        } while (c.moveToNext());
        AdapterNumber adapNumber = new AdapterNumber(arrNumber, getApplicationContext());
        grView.setAdapter(adapNumber);
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