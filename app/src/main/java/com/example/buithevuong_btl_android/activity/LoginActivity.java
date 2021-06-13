package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buithevuong_btl_android.MainActivity;
import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private Button login, signup;
    private TextInputEditText username, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
         SQLiteHelper sqLiteUser = new SQLiteHelper(this);
        Intent intentSignup = new Intent(this, RegisterActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);
        Intent intentAdmin = new Intent(this, AdminActivity.class);

        Toast toast1 = Toast.makeText(this, "Sai thông tin tài khoản hoặc tài khoản tạm thời bị khóa, vui lòng nhập lại!!", Toast.LENGTH_LONG);
        Toast toast2 = Toast.makeText(this, "Không được để trống , vui lòng nhập đầy đủ !!", Toast.LENGTH_LONG);


        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentSignup);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!username.getText().toString().equals("") || !password.getText().toString().equals("")){
                    User user = sqLiteUser.getUserByUsername(username.getText().toString());
                    if (user != null && user.getPassword().equals(password.getText().toString()) && user.getIsActive() == 1) {

                        if (user.getRoles().equals("USER")) {
                            intentMain.putExtra("userLogin", user);
                            startActivity(intentMain);

                            finish();
                        } else if (user.getRoles().equals("ADMIN")) {
                            intentAdmin.putExtra("userLogin", user);
                            startActivity(intentAdmin);
                            finish();
                        }

                    } else {

                        toast1.show();
                    }
                }else {
                    toast2.show();
                }


            }
        });





    }



    private void init() {
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);

    }


}
