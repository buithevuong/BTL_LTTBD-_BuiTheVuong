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
import com.example.buithevuong_btl_android.activity.EditUserActivity;
import com.example.buithevuong_btl_android.model.User;

import java.util.ArrayList;
import java.util.List;

public class ManageUserRecyclerAdapter extends RecyclerView.Adapter<ManageUserRecyclerAdapter.UserViewHolder>{
    List<User> list ;

    public ManageUserRecyclerAdapter() {
        this.list = new ArrayList<>();
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_user_recycler_view , parent , false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);
        holder.avatar.setImageResource(Integer.parseInt(user.getAvatar()));
        holder.fullname.setText(user.getFullname());
        holder.birth.setText(user.getDateOfBirth());
        holder.gender.setText(user.getGender());
        holder.role.setText(user.getRoles());

        if(user.getIsActive() == 1){
            holder.isEnable.setImageResource(R.drawable.ic_enable);
        }else {
            holder.isEnable.setImageResource(R.drawable.ic_disable);
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(view.getContext() , EditUserActivity.class);
                intent.putExtra("account" , user);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView avatar , isEnable;
        TextView fullname , birth , gender , role;
        private ItemClickListener itemClickListener;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.fullnameUser);
            birth = itemView.findViewById(R.id.birthUser);
            gender = itemView.findViewById(R.id.genderUser);
            role = itemView.findViewById(R.id.roleUser);
            avatar = itemView.findViewById(R.id.avatarUser);
            isEnable = itemView.findViewById(R.id.imageActive);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view , getAdapterPosition() , false);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

}
