package com.example.daan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lv = findViewById(R.id.menuList);

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        MenuRequest x = new MenuRequest(this, category);
        x.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuList) {
        Log.d("length", Integer.toString(menuList.size()));
        MenuAdapter menuItems = new MenuAdapter(this, R.layout.menu_item, R.id.name, menuList);
        lv.setAdapter(menuItems);
        lv.setOnItemClickListener(new MenuActivity.ListItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {

    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(MenuActivity.this, MenuInfo.class);
            intent.putExtra("item", item);
            startActivity(intent);
        }
    }
}
