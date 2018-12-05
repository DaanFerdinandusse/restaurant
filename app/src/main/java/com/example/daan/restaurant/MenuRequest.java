package com.example.daan.restaurant;

import android.content.Context;
import android.view.Menu;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> MenuList);
        void gotMenuError(String message);
    }

    private Context context;
    private ArrayList<MenuItem> MenuList = new ArrayList<>();
    private MenuRequest.Callback activity;
    private String category;

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray items = response.getJSONArray("items");
            for(int i = 0; i<items.length(); i++){
                JSONObject item = items.getJSONObject(i);
                MenuItem menuItem = new MenuItem(item.getString("name"), item.getString("description"),
                        item.getString("image_url"), item.getInt("price"), item.getString("category"));
                MenuList.add(menuItem);
            }
            activity.gotMenu(MenuList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public MenuRequest(Context context, String category) {
        this.context = context;
        this.category = category;
    }

    public void getMenu(MenuRequest.Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu?category="+category, null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
