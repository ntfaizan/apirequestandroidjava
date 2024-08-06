package com.nutech.apirequestandroidjava.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nutech.apirequestandroidjava.R;
import com.nutech.apirequestandroidjava.adapters.AuthorAdapter;
import com.nutech.apirequestandroidjava.databinding.ActivityMainBinding;
import com.nutech.apirequestandroidjava.models.Author;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String TAG = "cust_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Set up the RecyclerView
        binding.recyclerViewAuthors.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter(JSONObject response){
        try{
            List<Author> authorList = new ArrayList<>();
            JSONArray data = response.getJSONArray("data");
            for(int i = 0; i<data.length(); i++){
                JSONObject auth = data.getJSONObject(i);
                Author author = new Author(auth.getInt("id"), auth.getString("name"), auth.getString("created_at"),auth.getString("updated_at") );
                authorList.add(author);
            }
            AuthorAdapter adapter = new AuthorAdapter(authorList);
            binding.recyclerViewAuthors.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readResource(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Handle response
                                setAdapter(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                Log.e(TAG, error.toString());
                            }
                        });

        Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();
        readResource("https://email.nsdd.cloud/api/authors");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}