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

public class ComingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private final int item = 1;
    private final int load = 2;
    private List<UpComing.Results> UpCominglist;
    private List<UpComing.Results> getUpCominglist(){
        return UpCominglist;
    }
    public void setUpCominglist(List<UpComing.Results> UpCominglist ){
        this.UpCominglist = UpCominglist;
    }

    public ComingAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return UpCominglist.get(position) == null ? load : item ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == item){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming,parent,false);
            return new theholder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading,parent,false);
            return new loading(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof theholder){

            final UpComing.Results results = getUpCominglist().get(position);
            Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(((theholder) holder).img_poster_card_upcoming);
            ((theholder) holder).lbl_title_card_upcoming.setText(results.getTitle());
            ((theholder) holder).lbl_overview_card_upcoming.setText(results.getOverview());
            ((theholder) holder).lbl_releasedate_card_upcoming.setText(results.getRelease_date());
        }
        else if(holder instanceof loading){

        }

    }

    @Override
    public int getItemCount() {
        return getUpCominglist().size();
    }

    public class theholder extends RecyclerView.ViewHolder{
        ImageView img_poster_card_upcoming;
        CardView cv_card_upcoming;
        TextView lbl_title_card_upcoming, lbl_overview_card_upcoming, lbl_releasedate_card_upcoming;

        public theholder(@NonNull View itemView) {
            super(itemView);
            img_poster_card_upcoming = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title_card_upcoming = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview_card_upcoming = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_releasedate_card_upcoming = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            cv_card_upcoming = itemView.findViewById(R.id.cv_card_upcoming);

        }

    }
    public class loading extends RecyclerView.ViewHolder {
        public loading(@NonNull View itemView) {
            super(itemView);


        }

    }

}
