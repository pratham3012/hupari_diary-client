package com.example.huparidiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CatAdapter extends  RecyclerView.Adapter<CatAdapter.MyViewHolder>

{

    private List mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
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

    public CatAdapter(List myDataset) {
        mDataset=new ArrayList();
        mDataset = myDataset;
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

       holder.catText.setText(mDataset.get(position).toString());
        holder.catImage.setImageResource(R.drawable.resturnat_logo);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}