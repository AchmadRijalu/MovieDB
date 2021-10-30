package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Popular;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.viewHolder> {

    private Context context;
    private List<Popular.Results> listPopular;
    private List<Popular.Results> getListPopular(){
        return listPopular;
    }
    public void setListPopular(List<Popular.Results> listPopular){
        this.listPopular = listPopular;
    }
    public PopularAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public PopularAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra,parent,false);
        return new PopularAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.viewHolder holder, int position) {
        final Popular.Results results = getListPopular().get(position);
        Glide.with(context).load(Const.IMG_URL + results.getBackdrop_path()).into(holder.Popular_image);
    }

    @Override
    public int getItemCount() {
        return getListPopular().size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        CardView extra_cv;
        ImageView Popular_image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            extra_cv = itemView.findViewById(R.id.extra_cv);
            Popular_image = itemView.findViewById(R.id.Popular_image);
        }
    }
}
