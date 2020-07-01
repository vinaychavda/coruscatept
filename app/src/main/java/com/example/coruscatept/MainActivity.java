package com.example.coruscatept;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL_PRODUCTS="https://jsonplaceholder.typicode.com/users";//in this http address your server's API address
    private ArrayList<Pojo> mArrayList;
    RecyclerView recyclerView;
    DataAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        mArrayList=new ArrayList<>();
        loaddrive();
    }
    private void loaddrive() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray((response));
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

//                                JSONObject obj1=object.getJSONObject("street");
                                mArrayList.add(new Pojo(
                                        object.getString("name"),
                                        object.getString("address")

                                ));
                            }

                            mainAdapter=new DataAdapter(MainActivity.this,mArrayList);
                            recyclerView.setAdapter(mainAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "network  error", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
