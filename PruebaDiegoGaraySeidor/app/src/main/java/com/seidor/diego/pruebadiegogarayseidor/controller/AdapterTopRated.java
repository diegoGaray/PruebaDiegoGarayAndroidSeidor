package com.seidor.diego.pruebadiegogarayseidor.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.seidor.diego.pruebadiegogarayseidor.R;
import com.seidor.diego.pruebadiegogarayseidor.models.TopRated;

import java.util.List;

public class AdapterTopRated extends RecyclerView.Adapter<AdapterTopRated.CustomRecyclerView>  {

    public List<TopRated> itemList;

    private RequestQueue mRequestQueue;
    Context context;

    public AdapterTopRated(Context context, List<TopRated> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.mRequestQueue = SingletonRequestQueue.getInstance(context).getRequestQueue();
    }

    @NonNull
    @Override
    public AdapterTopRated.CustomRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        AdapterTopRated.CustomRecyclerView rcv = new AdapterTopRated.CustomRecyclerView(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTopRated.CustomRecyclerView holder, int position) {
        final TopRated myData = itemList.get(position);
        holder.txtTitle.setText("Title: " + myData.getTitle());
        holder.txtPopularity.setText("Popularity: " + myData.getPopularity());
        holder.txtLanguage.setText("Language: " + myData.getOriginal_language());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("overview", myData.getOverview());
                bundle.putString("poster_path", myData.getPoster_path());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class CustomRecyclerView extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtPopularity;
        TextView txtLanguage;

        CustomRecyclerView(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtPopularity = itemView.findViewById(R.id.txt_popularity);
            txtLanguage = itemView.findViewById(R.id.txt_language);
        }
    }
}
