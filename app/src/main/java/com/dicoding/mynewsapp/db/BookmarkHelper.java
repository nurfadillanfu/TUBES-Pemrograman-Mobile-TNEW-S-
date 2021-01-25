package com.dicoding.mynewsapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.mynewsapp.Business.Article;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.AUTHOR;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.CONTENT;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.DATE;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.IMAGE;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.TITLE;
import static com.dicoding.mynewsapp.db.DatabeseContract.BookmarkColumns.URL;
import static com.dicoding.mynewsapp.db.DatabeseContract.TABLE_BOOKMARK_NEW;

public class BookmarkHelper {
    public static final String DATABASE_TABLE = TABLE_BOOKMARK_NEW ;
    private static  DatabaseHelper databaseHelper;
    private static BookmarkHelper INSTANCE;
    private static SQLiteDatabase database;

    public BookmarkHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public static BookmarkHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new BookmarkHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException{
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        databaseHelper.close();
        if (database.isOpen()){
            database.close();
        }
    }
    public ArrayList<Article>getAllNews(){

        ArrayList<Article> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Article modelNews;
        if (cursor.getCount() > 0 ){
            do{
                modelNews = new Article();
                modelNews.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                modelNews.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                modelNews.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(AUTHOR)));
                modelNews.setPublishedAt(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                modelNews.setUrlToImage(cursor.getString(cursor.getColumnIndexOrThrow(IMAGE)));
                modelNews.setContent(cursor.getString(cursor.getColumnIndexOrThrow(CONTENT)));
                modelNews.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
                arrayList.add(modelNews);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }
    public long insertFavorite (Article modelNews){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE,modelNews.getTitle());
        contentValues.put(IMAGE,modelNews.getUrlToImage());
        contentValues.put(DATE,modelNews.getPublishedAt());
        contentValues.put(AUTHOR,modelNews.getAuthor());
        contentValues.put(CONTENT,modelNews.getContent());
        contentValues.put(URL,modelNews.getUrl());
        return database.insert(DATABASE_TABLE,null,contentValues);

    }
    public int deleteBookmark(int id){
        return database.delete(TABLE_BOOKMARK_NEW,_ID+"= '"+id+"'",null);
    }
}
