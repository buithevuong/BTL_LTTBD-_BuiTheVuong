package com.example.buithevuong_btl_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.adapter.CategoryAdapter;
import com.example.buithevuong_btl_android.adapter.HomeRecyclerViewAdapter;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private View v;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerView, recyclerViewCategory;
    private SQLiteHelper sqLiteHelper;
    private Spinner sortByDate;
    private List<String> listSortRate ;


    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        sqLiteHelper = new SQLiteHelper(getContext());
        recyclerView = v.findViewById(R.id.recyclerViewHome);
        recyclerViewCategory = v.findViewById(R.id.recyclerViewCategory);
        sortByDate = v.findViewById(R.id.spinnerSortBook);


        listSortRate = new ArrayList<>();
        listSortRate.add("By Date");
        listSortRate.add("By Rate");
        listSortRate.add("By Rating");
        ArrayAdapter spinner = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, listSortRate);
        sortByDate.setAdapter(spinner);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        Intent getAccount = getActivity().getIntent();
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(getAccount);

        categoryAdapter = new CategoryAdapter(getAccount);

        Category cate1 = new Category(1 , "nam cao", R.drawable.truyen_ngan_nam_cao);
        Category cate2 = new Category(2 , "kim lan", R.drawable.truyenj_ngan_kim_lan);
        Category cate3 = new Category(3 , "ngo tat to", R.drawable.truyen_ngan_nam_cao);
        Category cate4 = new Category(4 , "nam cao", R.drawable.truyenj_ngan_kim_lan);
        List<Category> listCate = Arrays.asList(cate1 , cate2 , cate3,cate4,cate1 , cate2 , cate3,cate4);

        if (listCate != null) {
            categoryAdapter.setList(listCate);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            recyclerViewCategory.setLayoutManager(layoutManager);
            recyclerViewCategory.setAdapter(categoryAdapter);
        }










        sortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<Book> list = new ArrayList<>();

                if(listSortRate.get(i).equals("By Rate")){
                    list = sqLiteHelper.getAllBookSortByRate();
                }else if(listSortRate.get(i).equals("By Date")){
                    list = sqLiteHelper.getAllBook();
                }else {
                    list = sqLiteHelper.getAllBookSortByRateGiam();
                }

                if (list != null) {
                    homeRecyclerViewAdapter.setList(list);

                    recyclerView.setAdapter(homeRecyclerViewAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                List<Book> list = new ArrayList<>();
                list = sqLiteHelper.getAllBook();
                if (list != null) {
                    homeRecyclerViewAdapter.setList(list);

                    recyclerView.setAdapter(homeRecyclerViewAdapter);
                }
            }
        });




        return v;
    }


}
