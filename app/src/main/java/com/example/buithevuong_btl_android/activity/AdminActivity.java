package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.model.User;

public class AdminActivity extends AppCompatActivity {
    TextView wellcome;
    Button btnManageBook, btnManageUser , btnLogout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
        Intent intentManageBook = new Intent(this , ManageBookActivity.class);
        Intent intentManageUser = new Intent(this , ManageUserActivity.class);
        Intent intentLogout = new Intent(this , LoginActivity.class);
        Intent getIntent = getIntent();
        User getUser = (User) getIntent.getSerializableExtra("userLogin");
        wellcome.setText("Wellcome "+getUser.getFullname());

        btnManageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentManageBook);
            }
        });
        btnManageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentManageUser);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentLogout);
                finish();
            }
        });
    }

    private void init(){
        wellcome = findViewById(R.id.wellcomeName);
        btnManageBook = findViewById(R.id.managerBook);
        btnManageUser = findViewById(R.id.manageUser);
        btnLogout = findViewById(R.id.btnLogoutAdmin);
    }
}
