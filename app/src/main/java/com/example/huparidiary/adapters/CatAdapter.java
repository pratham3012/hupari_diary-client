package com.example.huparidiary.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huparidiary.ItemsActivity;
import com.example.huparidiary.R;
import com.example.huparidiary.model.category;
import com.example.huparidiary.network.CategoryJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.MyViewHolder>

{

    private List<category> mDataset;

Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView catText;
        public ImageView catImage;
        public MyViewHolder(View v) {
            super(v);
            catText = v.findViewById(R.id.catText);
            catImage=v.findViewById(R.id.catImage);

        }



    }

    // Provide a suitable constructor (depends on the kind of dataset)

    public CatAdapter(Activity activity, List<category> myDataset) {
        mDataset=new ArrayList<category>();
        mDataset = myDataset;
        this.activity=activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       category catcalss= mDataset.get(position);
       holder.catText.setText(catcalss.getCatName());
       Picasso.get().load(catcalss.getCatImage()).into(holder.catImage);


        holder.catImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity, ItemsActivity.class);
                intent.putExtra("CATNAME",holder.catText.getText().toString().trim());
                activity.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}