package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {


    private MovieViewModel viewdetails;
    private TextView lbl_movie_details, lbl_overview_movie_details, lbl_name_movie_details, lbl_subtitle_movie_details, lbl_genres_movie_details;
    private String movie_id = "";
    private String movie_genre = "";
    private String img_id = "";
    private ImageView img_movie_details;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        img_movie_details = findViewById(R.id.img_movie_details_fragment);
        lbl_movie_details = findViewById(R.id.lbl_movie_details_fragment);
        lbl_overview_movie_details = findViewById(R.id.lbl_overview_movie_details_fragment);
        lbl_name_movie_details = findViewById(R.id.lbl_name_movie_details_fragment);
        lbl_subtitle_movie_details = findViewById(R.id.lbl_subtitle_movie_details_fragment);
        lbl_genres_movie_details = findViewById(R.id.lbl_genres_movie_details_fragment);
        Intent i = getIntent();




        viewdetails = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        movie_id = i.getStringExtra("movie_id");
        viewdetails.getMovieById(movie_id);
        viewdetails.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultDetails);



        lbl_movie_details.setText(movie_id);
        img_movie_details = findViewById(R.id.img_movie_details_fragment);

    }
    private Observer<Movies>showResultDetails = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String over = movies.getOverview();
            lbl_overview_movie_details.setText(over);
            String img_path = movies.getPoster_path().toString();
            String full_image = "https://image.tmdb.org/t/p/w500/" + img_path;
            String name = movies.getTitle();
            String subt = movies.getRelease_date();

            for (int i = 0; i< movies.getGenres().size(); i++){
                if(i == movies.getGenres().size()-1){
                    movie_genre += movies.getGenres().get(i).getName();
                }
                else{
                    movie_genre += movies.getGenres().get(i).getName() + ",";
                }
            }
            lbl_subtitle_movie_details.setText(subt);
            lbl_genres_movie_details.setText(movie_genre);
            lbl_name_movie_details.setText(name);
            Glide.with(MovieDetailsActivity.this).load(full_image).into(img_movie_details);

        }
    };


    @Override
    public void onBackPressed() {
        finish();
    }
}