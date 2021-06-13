package com.example.buithevuong_btl_android.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.activity.LoginActivity;
import com.example.buithevuong_btl_android.model.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {
    private View v;
    private ImageView accoutImg;
    private TextInputEditText fullname , gender , birth;
    private Button btnEdit ,btnDelete,btnLogout;
    private Spinner btnEditAvatar ;
    public AccountFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account, container , false);
            accoutImg = v.findViewById(R.id.accountImage);
        fullname = v.findViewById(R.id.accountFullname);
        gender = v.findViewById(R.id.accountGender);
        birth = v.findViewById(R.id.accountDatebirth);
        btnEdit = v.findViewById(R.id.btnAccountEdit);
        btnDelete = v.findViewById(R.id.btnAccountDelete);
        btnLogout = v.findViewById(R.id.btnLogout);
        btnEditAvatar = v.findViewById(R.id.btnEditAvatar);
        Intent logout = new Intent(getActivity() , LoginActivity.class);
       SQLiteHelper sqLiteHelper = new SQLiteHelper(getContext());
       Toast toast =  Toast.makeText(getContext() , "Update thanh cong !!",Toast.LENGTH_LONG);
        Intent getAccount = getActivity().getIntent();
        User user = (User) getAccount.getSerializableExtra("userLogin");
        accoutImg.setImageResource(Integer.parseInt(user.getAvatar()));
        fullname.setText(user.getFullname());

        gender.setText(user.getGender());
        birth.setText(user.getDateOfBirth());


        btnLogout.setText("Logout");


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(logout);
                getActivity().finish();
            }
        });




        List<Integer> listImage = new ArrayList<>();
        listImage.add(Integer.parseInt(user.getAvatar()));
        listImage.add(R.drawable.avatar_basic_man);
        listImage.add(R.drawable.avatar_basic_woman);
        listImage.add(R.drawable.avatar_man);
        listImage.add(R.drawable.avatar_woman);


        ArrayAdapter spinner1 = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, listImage);
        btnEditAvatar.setAdapter(spinner1);

        btnEditAvatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected (AdapterView < ? > adapterView, View view,int i, long l){

                accoutImg.setImageResource(listImage.get(i));

            }

            @Override
            public void onNothingSelected (AdapterView < ? > adapterView){

            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User editUser = new User();
                editUser.setId(user.getId());
                editUser.setUsername(user.getUsername());
                editUser.setPassword(user.getPassword());

                int imgPo = (int) btnEditAvatar.getSelectedItem();

                editUser.setAvatar(String.valueOf(imgPo));

                editUser.setIsActive(user.getIsActive());
                editUser.setFullname(fullname.getText().toString());
                editUser.setGender(gender.getText().toString());
                editUser.setDateOfBirth(birth.getText().toString());
                editUser.setRoles(user.getRoles());
                sqLiteHelper.updateUser(editUser);
                toast.show();
            }
        });



            AlertDialog.Builder b = new AlertDialog.Builder(getContext());
            b.setTitle("Are you sure ?");
            b.setPositiveButton("Agree and log out!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    sqLiteHelper.deleteUserById(user.getId());
                    startActivity(logout);
                    getActivity().finish();
                    dialog.cancel();
                }
            });
            b.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog al = b.create();
                al.show();
            }
        });

        return v;
    }


}
