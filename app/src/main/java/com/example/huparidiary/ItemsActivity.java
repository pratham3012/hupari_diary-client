package com.example.huparidiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        TextView text =findViewById(R.id.name);
        Intent intent= getIntent();
       String name= intent.getStringExtra("CATNAME");
           text.setText(name);
    }
}