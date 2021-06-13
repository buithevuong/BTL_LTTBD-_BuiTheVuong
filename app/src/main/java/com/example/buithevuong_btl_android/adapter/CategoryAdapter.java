package com.example.buithevuong_btl_android.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.activity.CategoryActivity;
import com.example.buithevuong_btl_android.activity.ReaderActivity;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.Category;
import com.example.buithevuong_btl_android.model.User;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    private List<Category> list;
    private Intent getUser;


    public CategoryAdapter(Intent intent) {
        list = new ArrayList<>();
        getUser = intent;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.item_category, parent , false);
        return new CategoryAdapter.CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
            Category cate = list.get(position);

            holder.imgCate.setImageResource(cate.getImage());
            holder.nameCate.setText(cate.getTitle());
        User user = (User) getUser.getSerializableExtra("userLogin");

        holder.setItemClickListener(new ManageUserRecyclerAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Intent intent = new Intent(view.getContext() , CategoryActivity.class);
                intent.putExtra("category" , cate);
                intent.putExtra("userLogin" , user);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imgCate;
        private TextView nameCate;
        private ManageUserRecyclerAdapter.ItemClickListener itemClickListener;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCate = itemView.findViewById(R.id.imgCategory);
            nameCate = itemView.findViewById(R.id.nameCategory);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ManageUserRecyclerAdapter.ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view , getAdapterPosition() , false);
        }
    }
}
