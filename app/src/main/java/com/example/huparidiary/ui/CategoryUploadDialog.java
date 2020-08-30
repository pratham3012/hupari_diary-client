package com.example.huparidiary.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.huparidiary.R;

public class CategoryUploadDialog extends Dialog {
    public  String name;
    public  EditText catName;
    public static ImageView catImage;
    public  Button catSaveBtn,catCancelBtn;
    public CategoryUploadDialog(@NonNull Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.upload_cat_dialog);
        catName=findViewById(R.id.catName_dialog);
        catImage=findViewById(R.id.catImage_dialog);
        catSaveBtn=findViewById(R.id.btn_save_cat_dialog);
        catCancelBtn=findViewById(R.id.btn_cancel_cat_dialog);
        catCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
