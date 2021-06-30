package com.example.englishforkids.Learn.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.englishforkids.Learn.Model.Color;
import com.example.englishforkids.R;

import java.util.ArrayList;

public class AdapterColor extends BaseAdapter {

    ArrayList<Color> arrayList;
    Context context;

    public AdapterColor(ArrayList<Color> arrayList, Context context) {
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
        AdapterColor.ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new AdapterColor.ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custom_color, null);
            viewHolder.img = (ImageView) view.findViewById(R.id.imv_custom_color);
            view.setTag(viewHolder);
        } else {
            viewHolder = (AdapterColor.ViewHolder) view.getTag();
        }
        Color color = (Color) getItem(i);
        Bitmap bm1 = BitmapFactory.decodeByteArray(color.getImage(), 0, color.getImage().length);
        viewHolder.img.setImageBitmap(bm1);
        return view;
    }
}
