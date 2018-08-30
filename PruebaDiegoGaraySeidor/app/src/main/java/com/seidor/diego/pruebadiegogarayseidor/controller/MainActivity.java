package com.seidor.diego.pruebadiegogarayseidor.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.seidor.diego.pruebadiegogarayseidor.R;
import com.seidor.diego.pruebadiegogarayseidor.controller.AdapterTopRated;
import com.seidor.diego.pruebadiegogarayseidor.controller.SingletonRequestQueue;
import com.seidor.diego.pruebadiegogarayseidor.controller.Adapter;
import com.seidor.diego.pruebadiegogarayseidor.models.PopularRated;
import com.seidor.diego.pruebadiegogarayseidor.models.TopRated;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
//Acvitividad principal de la aplicacion, donde se hacen las peticiones GET
// mediante la libreria Volley y las entradas desde la Vista que serian los layout.
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<PopularRated> popularRated = new ArrayList<PopularRated>();
    ArrayList<TopRated> topRated = new ArrayList<TopRated>();
    RecyclerView recyclerView;
    Adapter adapter;
    AdapterTopRated adapterTopRated;
    Button btnTop, btnPopular;
    private String URL_BASE = "https://api.themoviedb.org/3/movie";
    private String posterBase = "http://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        btnTop = findViewById(R.id.btn_top);
        btnPopular = findViewById(R.id.btn_popular);
        btnTop.setOnClickListener(this);
        btnPopular.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_popular:
                getPopularRated();
                break;
            case R.id.btn_top:
                getTopRated();
                break;
        }
    }

    public void getPopularRated(){
        popularRated.clear();
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BASE + "/popular?api_key=34738023d27013e6d1b995443764da44", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject parObj = (JSONObject) jsonArray.get(i);
                        PopularRated pop = new PopularRated();
                        pop.setTitle(parObj.getString("title"));
                        pop.setPopularity(parObj.getString("popularity"));
                        pop.setOriginal_language(parObj.getString("original_language"));
                        pop.setOverview(parObj.getString("overview"));
                        pop.setPoster_path(posterBase + parObj.getString("poster_path"));
                        Log.d("model: ", pop.toString());

                        popularRated.add(pop);
                    }
                } catch (JSONException e) {
                    Log.e("error json", "json parsing error: " + e.getMessage());
                }

            }
        }, errorListener) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                adapterGet();
            }
        });
    }

    public void adapterGet(){

        adapter = new Adapter(this, popularRated);
        recyclerView.setAdapter(adapter);
    }

    public void getTopRated(){
        topRated.clear();
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_BASE + "/top_rated?api_key=34738023d27013e6d1b995443764da44", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject parObj = (JSONObject) jsonArray.get(i);
                        TopRated top = new TopRated();
                        top.setTitle(parObj.getString("title"));
                        top.setPopularity(parObj.getString("popularity"));
                        top.setOriginal_language(parObj.getString("original_language"));
                        top.setOverview(parObj.getString("overview"));
                        top.setPoster_path(posterBase + parObj.getString("poster_path"));
                        Log.d("model: ", top.toString());

                        topRated.add(top);
                    }
                } catch (JSONException e) {
                    Log.e("error json", "json parsing error: " + e.getMessage());
                }

            }
        }, errorListener) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    if (request.getCacheEntry() != null) {
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                adapterTopGet();
            }
        });
    }
    public void adapterTopGet(){

        adapterTopRated = new AdapterTopRated(this, topRated);
        recyclerView.setAdapter(adapterTopRated);
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };
}
