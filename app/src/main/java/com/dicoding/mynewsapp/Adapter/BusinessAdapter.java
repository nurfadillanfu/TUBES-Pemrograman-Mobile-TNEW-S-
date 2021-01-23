package com.dicoding.mynewsapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dicoding.mynewsapp.Business.Article;
import com.dicoding.mynewsapp.Detail.DetailBusiness;
import com.dicoding.mynewsapp.R;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.bidangViewHolder> {
    private ArrayList<Article> list = new ArrayList<>();
    private Context context;

    public BusinessAdapter(Context context) {
        this.context = context;
    }
    public ArrayList<Article> getList(){
        return list;
    }
    public BusinessAdapter(ArrayList<Article> list) {
        this.list = list;
    }


    public void setListBusiness(ArrayList<Article> list){
        if (list.size()>0){
            this.list.clear();
        }
        this.list = list;
    }

    @Override
    public bidangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_layout, parent, false);
        this.context = parent.getContext();
        return new bidangViewHolder(view);
    }


    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    public class bidangViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_judul,tv_deskripsi,tv_author;
        private ImageView img_gambar;



        public bidangViewHolder(View itemView) {
            super(itemView);
            img_gambar = itemView.findViewById(R.id.img_gambar);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            tv_author = itemView.findViewById(R.id.tv_author);
//            tv_deskripsi = itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}

