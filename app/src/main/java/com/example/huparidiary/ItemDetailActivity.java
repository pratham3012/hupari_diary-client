package com.example.huparidiary;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.huparidiary.model.items;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import static android.Manifest.permission.CALL_PHONE;

public class ItemDetailActivity extends AppCompatActivity {
  TextView name,rank,address;
  ImageView image;
  Button contactBtn;
  RatingBar ratingBar;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        name=findViewById(R.id.item_name_final);
        rank=findViewById(R.id.rank_final);
        address=findViewById(R.id.address_final);
        image=findViewById(R.id.item_image);
        contactBtn=findViewById(R.id.contact);
        ratingBar=findViewById(R.id.rating);

        // To retrieve object in second Activity
    items item=  (items)  getIntent().getSerializableExtra("MyClass");
    name.setText(item.getName());
    rank.setText(item.getRanks());
    address.setText(item.getAddress());
    Picasso.get().load(item.getImage()).into(image);
    ratingBar.setRating(Float.parseFloat(item.getStars()));
        number=item.getPhone().trim();
      contactBtn.setOnClickListener(new View.OnClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onClick(View view) {
              Intent callIntent = new Intent(Intent.ACTION_CALL);
              callIntent.setData(Uri.parse("tel:"+number));
//              startActivity(callIntent);
              if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                  startActivity(callIntent);
              } else {
                  requestPermissions(new String[]{CALL_PHONE}, 1);
              }

          }
      });

    }

}