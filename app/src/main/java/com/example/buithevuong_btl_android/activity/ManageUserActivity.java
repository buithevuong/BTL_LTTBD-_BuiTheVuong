package com.example.buithevuong_btl_android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.adapter.ManageUserRecyclerAdapter;
import com.example.buithevuong_btl_android.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageUserActivity extends AppCompatActivity {
    EditText search;
    Button btnSearch;
    Spinner spinnerFilter;
    private ManageUserRecyclerAdapter manageUserRecyclerAdapter;
    private RecyclerView recyclerView;
    SQLiteHelper sqLiteHelper;

    List<String> menuSpinnerFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        init();
        manageUserRecyclerAdapter = new ManageUserRecyclerAdapter();
        sqLiteHelper = new SQLiteHelper(this);


        ArrayAdapter spinner1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, menuSpinnerFilter);


        spinnerFilter.setAdapter(spinner1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> list = sqLiteHelper.getAllUser();



        if(list!= null){
            manageUserRecyclerAdapter.setList(list);
            recyclerView.setAdapter(manageUserRecyclerAdapter);
        }

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!menuSpinnerFilter.get(i).equals("ALL")){

                    List<User> listByRole = getUsersByRole(menuSpinnerFilter.get(i));
                    manageUserRecyclerAdapter.setList(listByRole);
                    recyclerView.setAdapter(manageUserRecyclerAdapter);
                }
                else {
                    List<User> listByRole = getUsersByRole("ALL");
                    if (listByRole != null) {

                        manageUserRecyclerAdapter.setList(listByRole);
                        recyclerView.setAdapter(manageUserRecyclerAdapter);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                List<User> listByRole = getUsersByRole("ALL");
                if (listByRole != null) {

                    manageUserRecyclerAdapter.setList(listByRole);
                    recyclerView.setAdapter(manageUserRecyclerAdapter);
                }
            }

        });


        btnSearch.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    List<User> list2 = sqLiteHelper.findUserByFullName(search.getText().toString());
                    if (list2 != null) {

                        manageUserRecyclerAdapter.setList(list2);
                        recyclerView.setAdapter(manageUserRecyclerAdapter);
                    }
                }
            });



        }

        private void init() {
            search = findViewById(R.id.inputSearchUser);
            btnSearch = findViewById(R.id.btnSearchUser);
            recyclerView = findViewById(R.id.recyclerManagerUser);
            spinnerFilter = findViewById(R.id.spinnerFilterUser);
            menuSpinnerFilter = new ArrayList<>();
            menuSpinnerFilter.add("ALL");
            menuSpinnerFilter.add("ADMIN");
            menuSpinnerFilter.add("USER");

        }

        private List<User> getUsersByRole(String role){
            List<User> list = new ArrayList<>();
        if(role.equals("ALL")){
           list =  sqLiteHelper.getAllUser();
        }else {
            list = sqLiteHelper.getAllUserByRole(role);
        }


         return list;
        }

    }

