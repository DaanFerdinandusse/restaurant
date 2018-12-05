package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);
        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView price = findViewById(R.id.price);
        ImageView picture = findViewById(R.id.picture);

        name.setText(item.getName());
        description.setText(item.getDescription());
        price.setText("\u20ac" + item.getPrice());
        Picasso.get().load(item.getImageUrl()).into(picture);
    }
}
