package com.example.buithevuong_btl_android.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    TextInputEditText fullname , birth , username ,password , repassword;
    RadioButton male ,female;
    Button btnRegister , btnBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        Intent intent = new Intent(this, LoginActivity.class);
        Toast toast1 = Toast.makeText(this , "Dang ki thanh cong" , Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(this , "Mat khau khong khop" , Toast.LENGTH_SHORT);
        Toast toast3 = Toast.makeText(this , "Nhap đay du thong tin" , Toast.LENGTH_SHORT);



        SQLiteHelper sqLiteUser = new SQLiteHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender;
                if(fullname.getText().toString() != "" && birth.getText().toString()!=""){
                    if(male.isChecked()){
                        gender = "Male";
                    }else {
                        gender = "Female";
                    }
                    if(password.getText().toString().equals(repassword.getText().toString())){

                        User regisUser = new User(fullname.getText().toString(),
                                String.valueOf(R.drawable.avatar_basic_man),
                                birth.getText().toString(),
                                gender,
                                username.getText().toString(),
                                password.getText().toString(),
                                "USER",1);
                        sqLiteUser.createUser(regisUser);


                        toast1.show();
                    }else{

                        toast2.show();
                    }
                }else{

                    toast3.show();
                }


            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });

        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                birth.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }




    private void init(){
        fullname = (TextInputEditText) findViewById(R.id.regisName);
        birth =(TextInputEditText) findViewById(R.id.regisDateBirth);
        username = (TextInputEditText) findViewById(R.id.regisUsername);
        repassword =(TextInputEditText) findViewById(R.id.regisRepassword);
        password=(TextInputEditText) findViewById(R.id.regisPassword);
        male = findViewById(R.id.regisMale);
        female = findViewById(R.id.regisFemale);
        btnRegister = findViewById(R.id.register);
        btnBack = findViewById(R.id.backLogin);

    }

}
