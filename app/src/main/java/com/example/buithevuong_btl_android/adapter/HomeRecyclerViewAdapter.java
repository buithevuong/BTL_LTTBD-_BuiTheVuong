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
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.activity.ReaderActivity;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.BookViewHolder> {
    private List<Book> list;
    private Intent getUser;


    public HomeRecyclerViewAdapter(Intent intent) {
        list = new ArrayList<>();
        getUser = intent;
    }

    public void setList(List<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.item_book_recycler_view, parent , false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = list.get(position);
        User user = (User) getUser.getSerializableExtra("userLogin");

        holder.image.setImageResource(Integer.parseInt(book.getImage()));

        holder.title.setText(book.getTitle());
        holder.description.setText(book.getDescription());
        holder.rate.setText("Rate : "+book.getRate());

        holder.setItemClickListener(new ManageUserRecyclerAdapter.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {


                Intent intent = new Intent(view.getContext() , ReaderActivity.class);
                intent.putExtra("userLogin", user);
                intent.putExtra("readerBook" , book);
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

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView title, description , rate ,viewed;
        private ManageUserRecyclerAdapter.ItemClickListener itemClickListener;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_book);
            title = itemView.findViewById(R.id.title_book);
            description = itemView.findViewById(R.id.des_book);
            rate = itemView.findViewById(R.id.rate_book);

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
