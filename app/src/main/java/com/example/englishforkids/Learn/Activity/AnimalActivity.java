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
import com.example.englishforkids.Learn.Adapter.AdapterAnimal;
import com.example.englishforkids.Learn.Model.Animal;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class AnimalActivity extends AppCompatActivity {

    DatabaseHander db = new DatabaseHander(this);
    ArrayList<Animal> arrAnimal = null;
    GridView grView;
    int ps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_animal_activity);
        toolbar = (Toolbar) findViewById(R.id.TB_AnimalActivity);
        //Copy Database
        db.copyDB2SDCard();
        Actionbar();
        //Hiển thị dữ liệu từ DB vào ListView
        grView = (GridView) findViewById(R.id.gridview_animal);
        grView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AnimalActivity.this, Animal_Detail_Activity.class);
                intent.putExtra("ID", arrAnimal.get(position).ID);
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
        Animal row;
        arrAnimal = new ArrayList<Animal>();
        Cursor c = db.getCursor("select * from animal");
        c.moveToFirst();
        do {
            row = new Animal();
            row.ID = c.getString(0);
            row.image = c.getBlob(3);
            arrAnimal.add(row);
        } while (c.moveToNext());
        AdapterAnimal adapAnimal = new AdapterAnimal(arrAnimal, getApplicationContext());
        grView.setAdapter(adapAnimal);
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