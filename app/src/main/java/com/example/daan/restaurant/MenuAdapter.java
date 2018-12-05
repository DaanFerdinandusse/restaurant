package com.example.daan.restaurant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> items;

    public MenuAdapter(Context context, int resource, int resource2, ArrayList<MenuItem> objects) {
        super(context, resource, resource2, objects);

        this.items = objects;

        Log.d("length2", String.valueOf(items.size()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        Log.d("testadapter", "getview called");

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }
        MenuItem item = items.get(position);

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        ImageView picture = convertView.findViewById(R.id.picture);

        name.setText(item.getName());
        price.setText("\u20ac" + String.valueOf(item.getPrice()));
        Picasso.get().load(item.getImageUrl()).into(picture);

        return convertView;

    }

    @Override
    public int getCount() {
        return items.size();
    }

}
