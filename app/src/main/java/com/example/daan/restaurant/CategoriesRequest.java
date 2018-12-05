package com.example.daan.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    private Context context;
    private ArrayList<String> categoriesList = new ArrayList<String>();
    private Callback activity;

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray categories = response.getJSONArray("categories");
            for(int i = 0; i<categories.length(); i++){
                categoriesList.add(categories.getString(i));
            }
            activity.gotCategories(categoriesList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public void getCategories(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }
}
