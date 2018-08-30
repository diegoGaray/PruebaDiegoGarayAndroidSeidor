package com.seidor.diego.pruebadiegogarayseidor.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.seidor.diego.pruebadiegogarayseidor.R;
import com.squareup.picasso.Picasso;

public class DescriptionActivity extends AppCompatActivity {

    private TextView txtOverview;
    private ImageView imgPoster;
    private String overview;
    private String poster;


    public DescriptionActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_main);

        txtOverview = (TextView) findViewById(R.id.txt_overview);
        imgPoster = (ImageView) findViewById(R.id.img_poster);

        Bundle bundle = getIntent().getExtras();
        overview  = bundle.getString("overview");
        poster = bundle.getString("poster_path");
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtOverview.setText(overview.toString());
        Picasso.get().load(poster).into(imgPoster);
    }

}
