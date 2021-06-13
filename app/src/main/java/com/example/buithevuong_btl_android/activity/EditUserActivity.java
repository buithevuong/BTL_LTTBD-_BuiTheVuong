package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditUserActivity extends AppCompatActivity {
    Button btnEdit, btnBack, btnChangeRole;
    TextView fullname, gender, birth, role;
    RadioButton enable, disable;
    ImageView avatar;
    Spinner btnChooseAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        init();

        Intent getIntent = this.getIntent();
        Toast toast = Toast.makeText(this, "Update thanh cong !!!", Toast.LENGTH_SHORT);
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        User getUser = (User) getIntent.getSerializableExtra("account");
        User user = sqLiteHelper.getUserByUsername(getUser.getUsername());


        fullname.setText(user.getFullname());
        gender.setText(user.getGender());
        birth.setText(user.getDateOfBirth());
        role.setText(user.getRoles());
        if (user.getAvatar().equals("No Avatar")) {
            avatar.setImageResource(R.drawable.avatar_basic_man);
        } else {
            avatar.setImageResource(Integer.parseInt(user.getAvatar()));
        }

        if (user.getIsActive() == 1) {
            enable.setChecked(true);
        } else {
            disable.setChecked(true);
        }


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user2 = new User();
                user2.setId(user.getId());
                user2.setFullname(fullname.getText().toString());
                user2.setGender(gender.getText().toString());
                user2.setDateOfBirth(birth.getText().toString());
                user2.setUsername(user.getUsername());
                user2.setPassword(user.getPassword());
                user2.setRoles(role.getText().toString());
                if (enable.isChecked()) {
                    user2.setIsActive(1);
                } else if (disable.isChecked()) {
                    user2.setIsActive(0);
                }

                int imgPo = (int) btnChooseAvatar.getSelectedItem();

                        user2.setAvatar(String.valueOf(imgPo));


                        sqLiteHelper.updateUser(user2);
                        toast.show();
                }
            });


        btnChangeRole.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                if (role.getText().toString().equals("ADMIN")) {
                    role.setText("USER");
                } else {
                    role.setText("ADMIN");
                }
            }
            });
            List<Integer> listImage = new ArrayList<>();
        listImage.add(Integer.parseInt(user.getAvatar()));
        listImage.add(R.drawable.avatar_basic_man);
        listImage.add(R.drawable.avatar_basic_woman);
        listImage.add(R.drawable.avatar_man);
        listImage.add(R.drawable.avatar_woman);


            ArrayAdapter spinner1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listImage);
        btnChooseAvatar.setAdapter(spinner1);

        btnChooseAvatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

            {
                @Override
                public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){

                    avatar.setImageResource(listImage.get(i));

            }

                @Override
                public void onNothingSelected (AdapterView < ? > adapterView){

            }
            });


        }

        private void init () {
            btnChooseAvatar = findViewById(R.id.btnPickAvatar);
            btnEdit = findViewById(R.id.btnEditUser);
            btnBack = findViewById(R.id.btnBackManageUser);
            fullname = findViewById(R.id.txtFullname);
            btnChangeRole = findViewById(R.id.btnChangeRole);
            avatar = findViewById(R.id.imageAvatarPicked);
            gender = findViewById(R.id.txtGender);
            birth = findViewById(R.id.txtDateBirth);
            role = findViewById(R.id.txtRole);
            enable = findViewById(R.id.radioEnable);
            disable = findViewById(R.id.radioDisable);
        }
    }
