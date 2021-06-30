package com.example.englishforkids.Learn.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.englishforkids.Learn.Model.Animal;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class AdapterAnimal extends BaseAdapter {

    ArrayList<Animal> arrayList;
    Context context;

    public AdapterAnimal(ArrayList<Animal> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        ImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdapterAnimal.ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new AdapterAnimal.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_animal, null);
            viewHolder.img = (ImageView) view.findViewById(R.id.imv_custom_animal);
            view.setTag(viewHolder);
        } else {
            viewHolder = (AdapterAnimal.ViewHolder) view.getTag();
        }
        Animal animal = (Animal) getItem(i);
        Bitmap bm1 = BitmapFactory.decodeByteArray(animal.getImage(), 0, animal.getImage().length);
        viewHolder.img.setImageBitmap(bm1);
        return view;
    }
}
