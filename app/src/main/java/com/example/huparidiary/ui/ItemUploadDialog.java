package com.example.huparidiary.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;

import com.example.huparidiary.R;

public class ItemUploadDialog  extends Dialog {
    public  String name;
    public EditText itemName;
    public EditText phoneNumber;
    public RatingBar ratingBar;
    String rating;
   public RadioGroup radiostatus;
    public EditText rank;
    public  EditText address;
    public static ImageView itemImage;
    public Button catSaveBtn,catCancelBtn;
public  RadioButton radioButton_status;
    public ItemUploadDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.upload_item_dialog);
        itemName=findViewById(R.id.item_Name_dialog);
        itemImage=findViewById(R.id.itemImage_dialog);
        phoneNumber=findViewById(R.id.phoneNumber);
        ratingBar=findViewById(R.id.ratingbar_item);
        rank =findViewById(R.id.rank);
        address =findViewById(R.id.address);
        catSaveBtn=findViewById(R.id.btn_save_item_dialog);
        catCancelBtn=findViewById(R.id.btn_cancel_item_dialog);
        radiostatus=findViewById(R.id.status_item);
        catCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
