package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.UpComing;

import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewHolder> {


    private Context context;
    private List<UpComing.Results> UpCominglist;
    private List<UpComing.Results> getUpCominglist(){
        return UpCominglist;
    }
    public void setUpCominglist(List<UpComing.Results> UpCominglist ){
        this.UpCominglist = UpCominglist;
    }

    public UpComingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UpComingAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming,parent,false);
        return new UpComingAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingAdapter.CardViewHolder holder, int position) {
        final UpComing.Results results = getUpCominglist().get(position);
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_poster_card_upcoming);
        holder.lbl_title_card_upcoming.setText(results.getTitle());
        holder.lbl_overview_card_upcoming.setText(results.getOverview());
        holder.lbl_releasedate_card_upcoming.setText(results.getRelease_date());
    }

    @Override
    public int getItemCount() {
        return getUpCominglist().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_card_upcoming;
        CardView cv_card_upcoming;
        TextView lbl_title_card_upcoming, lbl_overview_card_upcoming, lbl_releasedate_card_upcoming;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_card_upcoming = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title_card_upcoming = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview_card_upcoming = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_releasedate_card_upcoming = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            cv_card_upcoming = itemView.findViewById(R.id.cv_card_upcoming);





        }


    }
}
