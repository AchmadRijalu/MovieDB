package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Credits;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.repositories.MovieRepository;
import com.example.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application){
        super(application);
        repository = MovieRepository.getInstance();
    }


    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }



//==Begin of viewmodel get now playing
    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }
    //==End of viewmodel get now playing



    //==Begin of viewmodel get Up Coming
    private MutableLiveData<UpComing> resultgetUpComing = new MutableLiveData<>();
    public void getUpComing(){
        resultgetUpComing = repository.getNowUpComing();

    }
    public LiveData<UpComing> getResultUpComing(){
        return resultgetUpComing;
    }
    //==End of viewmodel get Up Coming

    private MutableLiveData<Credits> resultGetMovieByIdCredit = new MutableLiveData<>();

    public void getMovieByIdCredit(String movieId){
        resultGetMovieByIdCredit = repository.getCreditsData(movieId);
    }

    public LiveData<Credits> getResultGetMovieByIdCredit(){
        return resultGetMovieByIdCredit;
    }


}
