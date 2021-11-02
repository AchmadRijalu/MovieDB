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
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;

import java.util.List;

public class PlayingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private final int item = 1;
    private final int load = 2;
    private List<NowPlaying.Results> nowcominglist;
    private List<NowPlaying.Results> getNowPlayinglist(){
        return nowcominglist;
    }
    public void setNowcominglist(List<NowPlaying.Results> nowcominglist ){
        this.nowcominglist = nowcominglist;
    }

    public PlayingAdapter(Context context){
        this.context = context;
    }

//    @Override
    public int getItemViewType(int position) {
        return nowcominglist.get(position) == null ? load : item ;
    }

    @NonNull
//    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == item){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing,parent,false);
            return new theholder2(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading,parent,false);
            return new loading2(view);
        }

    }

//    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof theholder2){

            final NowPlaying.Results results = getNowPlayinglist().get(position);
            Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(((theholder2) holder).img_poster_card_nowplaying);
            ((PlayingAdapter.theholder2) holder).lbl_title_card_nowplaying.setText(results.getTitle());
            ((PlayingAdapter.theholder2) holder).lbl_overview_card_nowplaying.setText(results.getOverview());
            ((PlayingAdapter.theholder2) holder).lbl_releasedate_card_nowplaying.setText(results.getRelease_date());
        }
        else if(holder instanceof loading2){

        }

    }

//    @Override
    public int getItemCount() {
        return getNowPlayinglist().size();
    }

    public class theholder2 extends RecyclerView.ViewHolder{
        ImageView img_poster_card_nowplaying;
        CardView cv_card_nowplaying;
        TextView lbl_title_card_nowplaying, lbl_overview_card_nowplaying, lbl_releasedate_card_nowplaying;

        public theholder2(@NonNull View itemView) {
            super(itemView);
            img_poster_card_nowplaying = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title_card_nowplaying = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview_card_nowplaying = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_releasedate_card_nowplaying = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            cv_card_nowplaying = itemView.findViewById(R.id.cv_card_upcoming);

        }

    }
    public class loading2 extends RecyclerView.ViewHolder {
        public loading2(@NonNull View itemView) {
            super(itemView);


        }

    }
}
