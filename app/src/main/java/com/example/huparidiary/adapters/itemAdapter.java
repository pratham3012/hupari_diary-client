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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huparidiary.ItemDetailActivity;
import com.example.huparidiary.ItemsActivity;
import com.example.huparidiary.R;
import com.example.huparidiary.model.category;
import com.example.huparidiary.model.items;
import com.example.huparidiary.network.CategoryJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class itemAdapter extends  RecyclerView.Adapter<itemAdapter.MyViewHolder>

{

    private List<items> mDataset;
    String catname_text;
    Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public TextView itemname;
        public RatingBar ratingBar;
        public TextView  totalRatings;
        public TextView status;
        public TextView catname;
        public  CardView cardView;
        public MyViewHolder(View v) {
            super(v);
            itemname = v.findViewById(R.id.item_name);
            ratingBar=v.findViewById(R.id.ratingbar);
            totalRatings=v.findViewById(R.id.totalrating);
            catname = v.findViewById(R.id.catname);
            status = v.findViewById(R.id.status);
            cardView=v.findViewById(R.id.card_item);
        }



    }

    // Provide a suitable constructor (depends on the kind of dataset)

    public itemAdapter(Activity activity, List<items> myDataset,String catname_text) {
        mDataset=new ArrayList<items>();
        this.catname_text=catname_text;
        mDataset = myDataset;
        this.activity=activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public itemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcat_items, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final items item= mDataset.get(position);
        holder.itemname.setText(item.getName());
        holder.totalRatings.setText(item.getRatings());
        holder.catname.setText(catname_text);
        holder.status.setText(item.getStatus());
        holder.ratingBar.setIsIndicator(true);
        holder.ratingBar.setRating(Float.valueOf(item.getStars()));
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText( activity, "pressed", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(activity);
                builder.setMessage("Do you want to delete"+holder.itemname.getText().toString()+"  ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);

                builder
                        .setPositiveButton(
                                "Yes",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {
                                        // When the user click yes button
                                        RequestQueue queue = Volley.newRequestQueue(activity);
                                        StringRequest request=new StringRequest("https://mibtechnologies.in/hupariapp/deletecat.php?uid="+ holder.catname.getText().toString().trim() , new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.e("TAG", "onErrorResponse:",error );
                                            }
                                        });


                                        queue.add(request);
                                        //

                                    }
                                });
                builder
                        .setNegativeButton(
                                "No",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        dialog.cancel();
                                    }
                                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();

                return false;
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= null;
                try {
                    intent = new Intent(activity, ItemDetailActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                intent.putExtra("MyClass", item);
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