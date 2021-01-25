package com.dicoding.mynewsapp.Detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dicoding.mynewsapp.Business.Article;
import com.dicoding.mynewsapp.R;
import com.dicoding.mynewsapp.WebActivity;
import com.dicoding.mynewsapp.db.BookmarkHelper;

import java.util.ArrayList;

public class DetailBusiness extends AppCompatActivity {
    public static Article data;
    private CheckBox checkBox;
    private BookmarkHelper bookmarkHelper;
    ArrayList<Article> articles;
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_AUTHOR = "extra_author";
    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_CONTENT = "extra_content";
    public static final String EXTRA_URL = "extra_url";
    private String url;

    TextView tv_judul,tv_tanggal,tv_konten,tv_author;
    ImageView img_gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_news);
        tv_judul = findViewById(R.id.tv_judul);
        tv_author = findViewById(R.id.tv_author);
        tv_konten = findViewById(R.id.tv_konten);
        tv_tanggal = findViewById(R.id.tv_tanggal);
        img_gambar = findViewById(R.id.img_gambar);
        checkBox = findViewById(R.id.cb_bookmark);

        bookmarkHelper = BookmarkHelper.getInstance(this.getApplicationContext());

        final String judul = getIntent().getStringExtra(EXTRA_TITLE);
        final String author = getIntent().getStringExtra(EXTRA_AUTHOR);
        final String konten = getIntent().getStringExtra(EXTRA_CONTENT);
        final String photo = getIntent().getStringExtra(EXTRA_IMAGE);
        final String date = getIntent().getStringExtra(EXTRA_DATE);
      url = getIntent().getStringExtra(EXTRA_URL);
//        Log.e("Data", data.getTitle());
//        final String judul = data.getTitle();
//        final String date = data.getPublishedAt();
//        Log.e("baco" , data.getPublishedAt());
//        final String konten = data.getContent();
//        final String author = data.getAuthor();
//        final String photo;
//        photo = data.getUrlToImage();

        articles = bookmarkHelper.getAllNews();
        if (articles.size()>0){
            for(int i = 0; i<articles.size();i++){
                if (articles.get(i).getTitle().equalsIgnoreCase(judul)&& articles.get(i).getContent().equalsIgnoreCase(konten)){
                    checkBox.setChecked(true);
                }
            }
        }


        Glide.with(DetailBusiness.this)
                .load(photo)
                .into(img_gambar);
        tv_judul.setText(judul);
        tv_tanggal.setText(date);
        tv_author.setText(author);
        tv_konten.setText(konten);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    articles = bookmarkHelper.getAllNews();
                    boolean noSimilliarItem = true;
                    for (int i = 0; i<articles.size();i++){
                        if (articles.get(i).getTitle().equalsIgnoreCase(judul)&& articles.get(i).getContent().equalsIgnoreCase(konten)){
                            noSimilliarItem = false;
                            break;
                        }
                    }
                    if (noSimilliarItem){
                        data = new Article();
                        data.setTitle(judul);
                        data.setPublishedAt(date);
                        data.setUrlToImage(photo);
                        data.setAuthor(author);
                        data.setContent(konten);
                        data.setUrl(url);
                        long article =bookmarkHelper.insertFavorite(data);
                        checkBox.setChecked(true);
                        if (article>0){
                            data.setId((int)article);
                            Toast.makeText(DetailBusiness.this, "Selamat! Artikel Anda Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DetailBusiness.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    checkBox.setChecked(false);
                    BookmarkHelper bookmarkHelper = BookmarkHelper.getInstance(DetailBusiness.this);
                    articles = bookmarkHelper.getAllNews();
                    int id = 0;
                    for (int i = 0;i<articles.size();i++){
                        if (articles.get(i).getTitle().equalsIgnoreCase(judul)&& articles.get(i).getContent().equalsIgnoreCase(konten)){
                            id = articles.get(i).getId();
                        }
                        bookmarkHelper.deleteBookmark(id);
                    }
                }
            }
        });

    }

    public void web1(View view) {
        Intent intent = new Intent(DetailBusiness.this, WebActivity.class);
        intent.putExtra("EXTRA_URL",url);
        startActivity(intent);

    }
}

