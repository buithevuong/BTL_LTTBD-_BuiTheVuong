package com.example.buithevuong_btl_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.adapter.HomeRecyclerViewAdapter;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.User;
import com.example.buithevuong_btl_android.model.UserBook;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistoryFragment extends Fragment {
    private View v;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private TextView txtTotalViewed;
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container , false);
        sqLiteHelper = new SQLiteHelper(getContext());
        txtTotalViewed = v.findViewById(R.id.totalBookViewed);
        recyclerView = v.findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Intent getAccount = getActivity().getIntent();
        User user1 = (User) getAccount.getSerializableExtra("userLogin");

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getAccount);
        List<Book> listBook = new ArrayList<>();

        List<UserBook> list = sqLiteHelper.getAllByUser(user1.getId());
        ;
        for(UserBook ub : list){
            Book book = sqLiteHelper.getBookById(ub.getBookId());
            listBook.add(book);

        }
        txtTotalViewed.setText("You have read "+ list.size() +" books");
        homeRecyclerViewAdapter.setList(listBook);

        recyclerView.setAdapter(homeRecyclerViewAdapter);
        return v;
    }
}
