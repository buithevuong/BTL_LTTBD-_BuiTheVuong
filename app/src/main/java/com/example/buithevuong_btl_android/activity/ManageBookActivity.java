package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;

import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.adapter.ManageBookRecyclerAdapter;
import com.example.buithevuong_btl_android.model.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageBookActivity extends AppCompatActivity {
    EditText search;
    Button btnSearch , btnAdd;
    private ManageBookRecyclerAdapter manageBookRecyclerAdapter;
    private RecyclerView recyclerView;
    SQLiteHelper sqLiteBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_book);

        init();
        manageBookRecyclerAdapter = new ManageBookRecyclerAdapter();
        recyclerView = findViewById(R.id.recyclerManagerBook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteBook = new SQLiteHelper(this);
        List<Book> list = sqLiteBook.getAllBook();


        if(list!= null){
            manageBookRecyclerAdapter.setList(list);
            recyclerView.setAdapter(manageBookRecyclerAdapter);
        }



        Intent intentAddBook = new Intent(this , AddAndEditBook.class);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(intentAddBook);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                List<Book> list2 = sqLiteBook.getBookByTitle(search.getText().toString());
                if (list2 != null) {
                    if(list2!= null){
                        manageBookRecyclerAdapter.setList(list2);
                        recyclerView.setAdapter(manageBookRecyclerAdapter);

                    }
                }
            }
        });



    }


   private void init(){
        search = findViewById(R.id.inputSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnAdd= findViewById(R.id.btnAddBook);
    }
}
