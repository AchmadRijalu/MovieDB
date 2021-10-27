package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {


    private MovieViewModel viewModel;
    private Button btn_hit;
    private TextView txt_show;
    private TextInputLayout til_input;
    private ImageView img_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit = findViewById(R.id.button_hit_main);
        txt_show = findViewById(R.id.txt_show);
        til_input = findViewById(R.id.til_input);
        img_main = findViewById(R.id.img_main);
        btn_hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = til_input.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    til_input.setError("Please fill movie ID Field!!");
                }
                else {
                    til_input.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }

            }
        });

    }
    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null) {
                txt_show.setText("Movie ID is not available");
            } else {
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                String full_img = "https://image.tmdb.org/t/p/w500/" + img_path;
                Glide.with(MainActivity.this).load(full_img).into(img_main);

                txt_show.setText(title);
            }

        }

    };

    }
