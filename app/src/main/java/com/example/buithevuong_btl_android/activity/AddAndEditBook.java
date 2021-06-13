package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buithevuong_btl_android.R;

import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.model.Book;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AddAndEditBook extends AppCompatActivity {
    Button  btnCreateBook, btnBack;
    Spinner pickImageBook;
    TextInputEditText inputTitle, inputDes, inputContent;
    ImageView imageBook;
    List<Integer> listImageBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        init();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);

        ArrayAdapter spinner = new ArrayAdapter<>(this ,R.layout.support_simple_spinner_dropdown_item, listImageBook);
        pickImageBook.setAdapter(spinner);

        pickImageBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageBook.setImageResource(listImageBook.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                imageBook.setImageResource(R.drawable.avatar_man);
            }
        });

        btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book1 = new Book();
                book1.setTitle(inputTitle.getText().toString());
                book1.setDescription(inputDes.getText().toString());
                book1.setContent(inputContent.getText().toString());
                book1.setRate(0);


                       book1.setImage(String.valueOf(pickImageBook.getSelectedItem()));

             sqLiteHelper.createBook(book1);
                Toast.makeText(AddAndEditBook.this , "Add success !!!" , Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void init() {

        listImageBook = Arrays.asList(R.drawable.doi_thua,R.drawable.chi_pheo
                ,R.drawable.lang_kim_lan,R.drawable.vonhat,
                R.drawable.cho_san__kim_lan,R.drawable.lao_hac);
        pickImageBook = findViewById(R.id.btnPickImageBook);
        btnCreateBook = findViewById(R.id.btnCreateBook);
        btnBack = findViewById(R.id.btnBack);
        inputTitle = findViewById(R.id.addTitleBook);
        inputDes = findViewById(R.id.addDesBook);
        imageBook = findViewById(R.id.imageBookPicked);
        inputContent = findViewById(R.id.addContentBook);
    }


}


