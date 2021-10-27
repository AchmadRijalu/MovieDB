package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.CreditAdapter;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.adapter.ProductionAdapter;
import com.example.moviedb.model.Credits;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private MovieViewModel viewModel, castModel;
    private String movie_genre = "";
    private TextView lbl_name, lbl_avg, lbl_release, lbl_popularity,lbl_genres, lbl_tagline,lbl_overview;
    private ImageView lbl_poster, lbl_backdrop;
    private RecyclerView rv_carddetails_production;
    private RecyclerView rv_carddetails_cast;
    private String movie_production = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_details, container, false);

       lbl_name =view.findViewById(R.id.lbl_carddetails_name);
        lbl_avg = view.findViewById(R.id.lbl_carddetails_avg);
        lbl_release = view.findViewById(R.id.lbl_carddetails_release);
        lbl_popularity = view.findViewById(R.id.lbl_carddetails_popularity);
        lbl_poster = view.findViewById(R.id.lbl_carddetails_image);
        lbl_backdrop = view.findViewById(R.id.lbl_carddetails_backdrop);
        lbl_tagline = view.findViewById(R.id.lbl_carddetails_tagline);
        lbl_overview = view.findViewById(R.id.lbl_carddetails_overview);
        lbl_genres = view.findViewById(R.id.lbl_carddetails_genre);
        rv_carddetails_production = view.findViewById(R.id.rv_carddetails_production);
        rv_carddetails_cast = view.findViewById(R.id.rv_carddetails_cast);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);

        String movie_id = getArguments().getString("movieId");
        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultDetails);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getMovieByIdCredit(movie_id);
        viewModel.getResultGetMovieByIdCredit().observe(getActivity(),showCast);
//        viewModel.getResultGetMovieById().observe(getActivity(), showCast);

//        String movieId = getArguments().getString("movieId".toString());
//
//        lbl_movie_id.setText(movieId);
        return view;
    }
    private Observer<Credits> showCast = new Observer<Credits>() {
        @Override
        public void onChanged(Credits credits) {
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false);
            rv_carddetails_cast.setLayoutManager(layoutManager2);
            CreditAdapter adapter1 = new CreditAdapter(getActivity());
            adapter1.setCastList(credits.getCast());
            rv_carddetails_cast.setAdapter(adapter1);
        }
    };
    private Observer<Movies>showResultDetails = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
//            rv_production.setLayoutManager(new LinearLayoutManager(getActivity()));
//            ProductionAdapter adapter = new ProductionAdapter(getActivity());

//            rv_production.setAdapter(adapter);

            String title = movies.getTitle();
            String over = movies.getOverview();
            String popularity = Double.toString(movies.getPopularity());
            String avg = Double.toString(movies.getVote_average());
            String tagline = movies.getTagline();
            lbl_overview.setText(over);
            String img_path = movies.getPoster_path().toString();
            String full_image = "https://image.tmdb.org/t/p/w500/" + img_path;

            String date = movies.getRelease_date();
            String img_backdrop= movies.getBackdrop_path();
            String full_backdrop = "https://image.tmdb.org/t/p/w500/" + img_backdrop;
            for (int i = 0; i< movies.getGenres().size(); i++){
                if(i == movies.getGenres().size()-1){
                    movie_genre += movies.getGenres().get(i).getName();
                }
                else{
                    movie_genre += movies.getGenres().get(i).getName() + " ,";
                }
            }

            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

            rv_carddetails_production.setLayoutManager(layoutManager);
            ProductionAdapter adapter = new ProductionAdapter(getActivity());

            adapter.setProductionlist(movies.getProduction_companies());
            rv_carddetails_production.setAdapter(adapter);





            lbl_genres.setText(movie_genre);
            lbl_name.setText(title);
            lbl_release.setText(date);
            lbl_avg.setText(avg);
            lbl_popularity.setText(popularity);
            lbl_tagline.setText(tagline);
            Glide.with(getActivity()).load(full_image).into(lbl_poster);
            Glide.with(getActivity()).load(full_backdrop).into(lbl_backdrop);
        }
    };
}