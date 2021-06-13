package com.example.buithevuong_btl_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.activity.ManageBookActivity;
import com.example.buithevuong_btl_android.adapter.HomeRecyclerViewAdapter;
import com.example.buithevuong_btl_android.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private View v;
    private TextView txtTotalResult;
    private EditText inputSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    List<Book> list = null;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_search, container, false);
        txtTotalResult = v.findViewById(R.id.totalResultsSearch);
        inputSearch = v.findViewById(R.id.inputSearchBook);
        btnSearch = v.findViewById(R.id.btnSearchBook);
        recyclerView = v.findViewById(R.id.recyclerBookSearch);
        sqLiteHelper = new SQLiteHelper(getContext());
        Intent getAccount = getActivity().getIntent();
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getAccount);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    list = sqLiteHelper.getBookByTitle(inputSearch.getText().toString());
                    homeRecyclerViewAdapter.setList(list);
                txtTotalResult.setText(list.size() + " results found");
                recyclerView.setAdapter(homeRecyclerViewAdapter);
            }
        });




        return v;
    }


}
