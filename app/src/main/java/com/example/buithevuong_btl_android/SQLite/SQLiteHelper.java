package com.example.buithevuong_btl_android.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.buithevuong_btl_android.model.Book;
import com.example.buithevuong_btl_android.model.User;
import com.example.buithevuong_btl_android.model.UserBook;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "btlDB.db";
    private static final Integer DATABASE_VERSION = 1;

    private static final String TABLE_USER= "Users";
    private static final String TABLE_BOOK = "book";
    private static final String TABLE_USER_BOOK= "UserBook";

    private static final String CREATE_TABLE_USER = "CREATE TABLE Users(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Username TEXT," +
            "password TEXT,"+
            "roles TEXT,"+
            "fullname TEXT,"+
            "avatar TEXT,"+
            "isEnable INTEGER,"+
            "date_birth TEXT,"+
            "gender TEXT)";

    private static final String CREATE_TABLE_BOOK = "CREATE TABLE book(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "image TEXT," +
            "title TEXT," +
            "description TEXT,"+
            "content TEXT,"+
            "rate REAL)";

    private static final String CREATE_TABLE_USER_BOOK = "CREATE TABLE UserBook(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "viewed INTEGER," +
            "voted REAL,"+
            "viewlate INTEGER,"+
            "user_id INTEGER,"+
            "book_id INTEGER,"+
            " FOREIGN KEY (user_id) REFERENCES Users (id), "+
            " FOREIGN KEY (book_id) REFERENCES book (id) )";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);


        sqLiteDatabase.execSQL(CREATE_TABLE_BOOK);

        sqLiteDatabase.execSQL(CREATE_TABLE_USER_BOOK);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_BOOK);
        // create new tables
        onCreate(sqLiteDatabase);
    }
    /*--------------------------------------------------------------------------------------*/
    public long createUser(User u){
        ContentValues v=new ContentValues();
        v.put("Username",u.getUsername());
        v.put("password",u.getPassword());
        v.put("roles",u.getRoles());
        v.put("fullname",u.getFullname());
        v.put("date_birth",u.getDateOfBirth());
        v.put("gender",u.getGender());
        v.put("isEnable",1);
        v.put("avatar",u.getAvatar());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert(TABLE_USER,null,v);
    }

    public List<User> getAllUser(){
        List<User> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query(TABLE_USER,null,
                null,null,
                null,null,"id DESC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String Username=rs.getString(1);
            String password=rs.getString(2);
            String role=rs.getString(3);
            String fullname=rs.getString(4);
            String avatar=rs.getString(5);
            Integer isEnable=rs.getInt(6);
            String birth=rs.getString(7);
            String gender=rs.getString(8);

            list.add(new User(id,fullname,avatar,birth,gender,Username,password,role,isEnable));
        }
        return  list;
    }
    public User getUserByUsername(String searchUsername){
        String whereClause="Username=?";
        String[] whereArgs={searchUsername};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_USER,null,whereClause,
                whereArgs,null,null,null);
        if(rs.moveToNext()){
            int id=rs.getInt(0);
            String Username=rs.getString(1);
            String password=rs.getString(2);
            String role=rs.getString(3);
            String fullname=rs.getString(4);
            String avatar=rs.getString(5);
            Integer isEnable=rs.getInt(6);
            String birth=rs.getString(7);
            String gender=rs.getString(8);
            return new User(id,fullname,avatar,birth,gender,Username,password,role,isEnable);
        }
        return null;
    }

    public int updateUser(User u){
        ContentValues v=new ContentValues();
        v.put("Username",u.getUsername());
        v.put("password",u.getPassword());
        v.put("roles",u.getRoles());
        v.put("fullname",u.getFullname());
        v.put("date_birth",u.getDateOfBirth());
        v.put("gender",u.getGender());
        v.put("avatar",u.getAvatar());
        v.put("isEnable", u.getIsActive());
        SQLiteDatabase st=getWritableDatabase();
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(u.getId())};
        return st.update(TABLE_USER,v,whereClause,whereArgs);
    }

    public  int deleteUserById(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete(TABLE_USER,whereClause,whereArgs);
    }

    public List<User> findUserByFullName(String name){
        List<User> list = new ArrayList<>();
        String whereClause="fullname like ?";
        String[] whereArgs={"%"+name+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_USER,new String[]{"*"},whereClause,
                whereArgs,null,null,"id DESC");
        while((rs!=null)&&rs.moveToNext()){
            int id=rs.getInt(0);
            String Username=rs.getString(1);
            String password=rs.getString(2);
            String role=rs.getString(3);
            String fullname=rs.getString(4);
            String avatar = rs.getString(5);
            Integer isEnable = rs.getInt(6);
            String birth=rs.getString(7);
            String gender=rs.getString(8);
            list.add(new User(id,fullname,avatar,birth,gender,Username,password,role,isEnable));

        }
        return list;
    }

    public List<User> getAllUserByRole(String searchByRole){
        List<User> list = new ArrayList<>();
        String whereClause="roles=?";
        String[] whereArgs={searchByRole};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_USER,null,whereClause,
                whereArgs,null,null,"id DESC");
        while((rs!=null)&&rs.moveToNext()){
            int id=rs.getInt(0);
            String Username=rs.getString(1);
            String password=rs.getString(2);
            String role=rs.getString(3);
            String fullname=rs.getString(4);
            String avatar = rs.getString(5);
            Integer isEnable = rs.getInt(6);
            String birth=rs.getString(7);
            String gender=rs.getString(8);
            list.add(new User(id,fullname,avatar,birth,gender,Username,password,role,isEnable));

        }
        return list;
    }
    /*--------------------------------------------------------------------------------------*/

    public long createBook(Book b){
        ContentValues v=new ContentValues();
        v.put("image",b.getImage());
        v.put("title",b.getTitle());
        v.put("description",b.getDescription());
        v.put("content",b.getContent());
        v.put("rate",b.getRate());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert(TABLE_BOOK,null,v);
    }

    public List<Book> getAllBook(){
        List<Book> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query(TABLE_BOOK,null,
                null,null,
                null,null,"id DESC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            list.add(new Book(id,image,title,description,content,rate));
        }
        if(list != null){
            return  list;
        }
        return null;
    }

    public List<Book> getAllBookSortByRate(){
        List<Book> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query(TABLE_BOOK,null,
                null,null,
                null,null,"rate DESC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            list.add(new Book(id,image,title,description,content,rate));
        }
        if(list != null){
            return  list;
        }
        return null;
    }

    public List<Book> getAllBookSortByRateGiam(){
        List<Book> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query(TABLE_BOOK,null,
                null,null,
                null,null,"rate ASC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            list.add(new Book(id,image,title,description,content,rate));
        }
        if(list != null){
            return  list;
        }
        return null;
    }

    public List<Book> getBookByTitle(String title1){
        List<Book> list=new ArrayList<>();
        String whereClause="title like ?";
        String[] whereArgs={"%"+title1+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_BOOK,new String[]{"*"},whereClause,
                whereArgs,null,null,"id DESC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            list.add(new Book(id,image,title,description,content,rate));
        }
        if(list != null){
            return  list;
        }
        return null;
    }

    public List<Book> getBookByAuthor(String author){
        List<Book> list=new ArrayList<>();
        String whereClause="description like ?";
        String[] whereArgs={"%"+author+"%"};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_BOOK,new String[]{"*"},whereClause,
                whereArgs,null,null,"id DESC");
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            list.add(new Book(id,image,title,description,content,rate));
        }
        if(list != null){
            return  list;
        }
        return null;
    }


    public Book getBookById(int id){

        String whereClause="id = ?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_BOOK,new String[]{"*"},whereClause,
                whereArgs,null,null,null);
        if(rs.moveToNext()){
            int idB = rs.getInt(0);
            String image=rs.getString(1);
            String title=rs.getString(2);
            String description=rs.getString(3);
            String content=rs.getString(4);
            Float rate = rs.getFloat(5);
            return new Book(idB,image,title,description,content,rate);
        }

        return null;
    }

    public int updateBook(Book b){
        ContentValues v=new ContentValues();
        v.put("image",b.getImage());
        v.put("title",b.getTitle());
        v.put("description",b.getDescription());
        v.put("content",b.getContent());
        v.put("rate",b.getRate());
        SQLiteDatabase st=getWritableDatabase();
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(b.getId())};
        return st.update(TABLE_BOOK,v,whereClause,whereArgs);
    }

    public  int deleteBookById(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete(TABLE_BOOK,whereClause,whereArgs);
    }


    /*--------------------------------------------------------------------------------------*/

    public long createUserBook(UserBook us){
        ContentValues v=new ContentValues();
        v.put("viewed",us.getViewed());
        v.put("voted",us.getVoted());
        v.put("viewlate",us.getViewlate());
        v.put("user_id",us.getUserId());
        v.put("book_id",us.getBookId());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert(TABLE_USER_BOOK,null,v);
    }


    public List<UserBook> getAllByUser(Integer idUser){
        List<UserBook> list=new ArrayList<>();
        String whereClause="user_id = ?";
        String[] whereArgs={String.valueOf(idUser)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(true, TABLE_USER_BOOK, new String[]{"*"}
                , whereClause, whereArgs, "book_id", null, "id DESC", null);
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            int viewed=rs.getInt(1);
            float voted=rs.getFloat(2);
            int viewlate=rs.getInt(3);
            int UserId =rs.getInt(4);
            int bookId = rs.getInt(5);
            list.add(new UserBook(id,viewed,voted,viewlate,UserId,bookId));
        }
        if(list != null){
            return  list;
        }
        return null;
    }

    public List<UserBook> getAllByBook(Integer idBook){
        List<UserBook> list=new ArrayList<>();
        String whereClause="book_id = ?";
        String[] whereArgs={String.valueOf(idBook)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query(TABLE_USER_BOOK,new String[]{"*"},whereClause,
                whereArgs,null,null,null);
        while((rs!=null)&& rs.moveToNext()){
            int id=rs.getInt(0);
            int viewed=rs.getInt(1);
            float voted=rs.getFloat(2);
            int viewlate=rs.getInt(3);
            int UserId =rs.getInt(4);
            int bookId = rs.getInt(5);
            list.add(new UserBook(id,viewed,voted,viewlate,UserId,bookId));
        }
        if(list != null){
            return  list;
        }
        return null;
    }
    //update
    public int updateUserBook(UserBook ub){
        ContentValues v=new ContentValues();
        v.put("viewed",ub.getViewed());
        v.put("voted",ub.getVoted());
        v.put("viewlate",ub.getViewlate());
        v.put("user_id",ub.getUserId());
        v.put("book_id",ub.getBookId());
        SQLiteDatabase st=getWritableDatabase();
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(ub.getId())};
        return st.update(TABLE_USER_BOOK,v,whereClause,whereArgs);
    }
    //delete
    public  int deleteUserBook(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete(TABLE_USER_BOOK,whereClause,whereArgs);
    }

}
