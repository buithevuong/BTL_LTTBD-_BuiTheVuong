package com.example.buithevuong_btl_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.example.buithevuong_btl_android.NotifyBuilder;
import com.example.buithevuong_btl_android.R;
import com.example.buithevuong_btl_android.SQLite.SQLiteHelper;
import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.User;
import com.example.buithevuong_btl_android.model.UserBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReaderActivity extends AppCompatActivity {
    private TextView title , content , author;
    private RatingBar rate ;
    private Button btnSendRate;
    private ImageView imageBookReader;
    private DatabaseReference mDatabase;
    private NotificationManagerCompat notificationManagerCompat;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
          init();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        Book getBook = (Book) intent.getSerializableExtra("readerBook");
        System.out.println(getBook.getTitle());
        if(getBook != null){
            title.setText(getBook.getTitle());
            rate.setRating(getBook.getRate());
            author.setText(getBook.getDescription());
            imageBookReader.setImageResource(Integer.valueOf(getBook.getImage()));
            mDatabase.child(getBook.getContent()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    content.setText(snapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            rate.setRating(getBook.getRate());
        }

        sqLiteHelper = new SQLiteHelper(this);
        Intent getUser = getIntent();
        User user = (User) getUser.getSerializableExtra("userLogin");

        UserBook ub = new UserBook();
        if(user != null){
            ub.setUserId(user.getId());
            ub.setBookId(getBook.getId());
            ub.setViewed(1);
            ub.setVoted(0);
            ub.setViewlate(0);
            sqLiteHelper.createUserBook(ub);
        }



        NotifyBuilder notifyBuilder = new NotifyBuilder();

        notifyBuilder.createNotificationChannels(this);


        notificationManagerCompat = NotificationManagerCompat.from(this);

        int notificationId = 1;
        notificationManagerCompat.notify(notificationId,  notifyBuilder.createNotify(this ,
                "Bạn đã đọc truyện ngắn : "+ getBook.getTitle(), getBook.getDescription() ,R.drawable.truyen_ngan_nam_cao ));


        btnSendRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBook.setRate(rate.getRating());
                sqLiteHelper.updateBook(getBook);
                Toast.makeText(ReaderActivity.this , "Rate success !!!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        title = findViewById(R.id.TitleBook);
        content = findViewById(R.id.contentBook);
        rate = findViewById(R.id.RateBook);
        author = findViewById(R.id.AuthorBook);
        btnSendRate = findViewById(R.id.btnSendRate);
        imageBookReader = findViewById(R.id.imageBookReader);
    }


}
