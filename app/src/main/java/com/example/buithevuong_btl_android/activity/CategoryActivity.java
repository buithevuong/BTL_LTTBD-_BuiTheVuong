package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.adapter.CategoryAdapter;
import com.example.buithevuong_btl_android.adapter.HomeRecyclerViewAdapter;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.Category;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter listBookAdapter;
    private TextView txtCategory , txtTotalResults;
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        txtCategory = findViewById(R.id.txtCategoryBook);
        txtTotalResults = findViewById(R.id.totalBookCategory);
        recyclerView = findViewById(R.id.listBookCategory);
        sqLiteHelper = new SQLiteHelper(this);
        Intent getIntent = getIntent();

        listBookAdapter = new HomeRecyclerViewAdapter(getIntent);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Category getCate = (Category) getIntent.getSerializableExtra("category");


       List<Book> list = sqLiteHelper.getBookByAuthor(getCate.getTitle());

        txtCategory.setText(getCate.getTitle());


        if(list != null){
            txtTotalResults.setText(list.size() + " results found");
            listBookAdapter.setList(list);

            recyclerView.setAdapter(listBookAdapter);
        }

    }
}
