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

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewHolder> {

    private Context context;
    private List<NowPlaying.Results> listNowPlaying;
    private List<NowPlaying.Results> getListNowPlaying(){
        return listNowPlaying;
    }
    public void setListNowPlaying(List<NowPlaying.Results> listNowPlaying){
        this.listNowPlaying = listNowPlaying;
    }
    public NowPlayingAdapter(Context context){
        this.context = context;
    }



    @NonNull
    @Override
    public NowPlayingAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing,parent,false);
        return new NowPlayingAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.CardViewHolder holder, int position) {

        final NowPlaying.Results results = getListNowPlaying().get(position);
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_poster_card_nowplaying);
        holder.lbl_title_card_nowplaying.setText(results.getTitle());
        holder.lbl_overview_card_nowplaying.setText(results.getOverview());
        holder.lbl_releasedate_card_nowplaying.setText(results.getRelease_date());


//        holder.cv_card_nowplaying.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(context, MovieDetailsActivity.class);
////                intent.putExtra("movie_id", ""+results.getId());
////
////                context.startActivity(intent);
////                Bundle bundle = new Bundle();
////                bundle.putString("movieId", ""+results.getId());
////                Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_MovieDetailsFragment, bundle);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster_card_nowplaying;
        CardView cv_card_nowplaying;
        TextView lbl_title_card_nowplaying, lbl_overview_card_nowplaying, lbl_releasedate_card_nowplaying;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster_card_nowplaying = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_title_card_nowplaying = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview_card_nowplaying = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_releasedate_card_nowplaying = itemView.findViewById(R.id.lbl_releasedate_card_upcoming);
            cv_card_nowplaying = itemView.findViewById(R.id.cv_card_upcoming);


        }

    }
}
