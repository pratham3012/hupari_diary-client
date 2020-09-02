package com.example.huparidiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.huparidiary.adapters.CatAdapter;
import com.example.huparidiary.network.CategoryJson;
import com.example.huparidiary.network.imageupload;
import com.example.huparidiary.ui.CategoryUploadDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.huparidiary.model.category;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    String url="https://www.mibtechnologies.in/hupariapp/index.php";
   List<category> myDataset;
    public static final int PICK_IMAGE = 1;
    Bitmap     bmp;
    boolean canDelete=false;

 SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        swipeRefreshLayout = findViewById(R.id.swiperefreshcat);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        toolbar = (Toolbar) findViewById(R.id.include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("category page");
        }
        toolbar.setSubtitle("category choose");
        toolbar.inflateMenu(R.menu.menu);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.i("TAG", "onCreate: ");
        RequestQueue queue = Volley.newRequestQueue(this);

        myDataset = new ArrayList();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        doYourUpdate();
                    }
                }
        );
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                GsonBuilder gsonBuilder=new GsonBuilder();
//                Gson gson =gsonBuilder.create();
//                CategoryJson categoryJson=gson.fromJson(response,CategoryJson.class);
//                Log.d("TAG", "onResponse: "+categoryJson.getName());
//                Log.d("TAG", "onResponse: "+categoryJson.getUid().trim());
                GsonBuilder builder = new GsonBuilder();
                Gson gson1 = builder.create();
                int i = 0;
                CategoryJson[] categoryJsons = gson1.fromJson(response, CategoryJson[].class);
                for (CategoryJson categoryJson1 : categoryJsons) {
                    Log.i("TAG", "onResponse: " + categoryJson1.getCatname());
                    category cat = new category(categoryJson1.getUid(), categoryJson1.getCatname(), categoryJson1.getCatimage());
                    myDataset.add(cat);
                    i++;
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse:", error);
            }
        });
        queue.add(request);

        // specify an adapter (see also next example)
        mAdapter = new CatAdapter(this, myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addCategory:
                Log.i("geraa", "onOptionsItemSelected: ");
                Log.i("dialog", "onOptionsItemSelected: "+"clicked");
                final CategoryUploadDialog uploadDialog = new CategoryUploadDialog(this);
                uploadDialog.show();
                uploadDialog.catImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        getIntent.setType("image/*");

                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("image/*");

                        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                        startActivityForResult(chooserIntent, PICK_IMAGE);
                    }
                });
                uploadDialog.catSaveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String uuid = UUID.randomUUID().toString().replace("-", "");
                        Log.i("uuid is ", "onClick: "+uuid);
                        String name=   new imageupload().uploadImageToImgur(bmp,uuid,uploadDialog.catName.getText().toString().trim(), getApplicationContext());
                        Log.i("war", "onActivityResult: "+name);

                        uploadDialog.dismiss();
                    }
                });
                return true;

            case R.id.deleteCategory:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                InputStream inputStream = this.getContentResolver().openInputStream(data.getData());
                bmp = BitmapFactory.decodeStream(inputStream);
         Picasso.get().load(data.getData()).into(CategoryUploadDialog.catImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    private void doYourUpdate() {
        // TODO implement a refresh
        RequestQueue queue = Volley.newRequestQueue(this);

        myDataset=new ArrayList();
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


//                GsonBuilder gsonBuilder=new GsonBuilder();
//                Gson gson =gsonBuilder.create();
//                CategoryJson categoryJson=gson.fromJson(response,CategoryJson.class);
//                Log.d("TAG", "onResponse: "+categoryJson.getName());
//                Log.d("TAG", "onResponse: "+categoryJson.getUid().trim());
                GsonBuilder builder=new GsonBuilder();

                Gson gson1= builder.create();
                int i=0;
                CategoryJson[] categoryJsons=  gson1.fromJson(response,CategoryJson[].class);
                for (CategoryJson categoryJson1: categoryJsons)
                {
                    Log.i("TAG", "onResponse: "+categoryJson1.getCatname());
                    category cat= new category(categoryJson1.getUid(),categoryJson1.getCatname(),categoryJson1.getCatimage());
                    myDataset.add(cat);
                    i++;
                }
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse:",error );
            }
        });



        queue.add(request);
        // specify an adapter (see also next example)
        mAdapter = new CatAdapter(this,myDataset);
        recyclerView.setAdapter(mAdapter);


        swipeRefreshLayout.setRefreshing(false); // Disables the refresh icon

    }

}