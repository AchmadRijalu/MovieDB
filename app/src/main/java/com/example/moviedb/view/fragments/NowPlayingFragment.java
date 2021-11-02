package com.example.moviedb.view.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.adapter.ComingAdapter;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.adapter.PlayingAdapter;
import com.example.moviedb.adapter.PopularAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.Popular;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.view.activities.NowPlayingActivity;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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
        dialog = ProgressDialog.show(getActivity(), "", "Now Loading", true);
    }
    private RecyclerView rv_now_playing, rv_now_playing_popular;
    private MovieViewModel view_Model ,view_model2;
    private ProgressDialog dialog;
    private int currentPage = 1;
    private int maxPage = 1;
    private List<NowPlaying.Results> listnexpage = new ArrayList<>();
    private PlayingAdapter adapter;
    public boolean check = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_now_playing, container, false);

        rv_now_playing = view.findViewById(R.id.rv_now_playing_fragment);
        rv_now_playing_popular = view.findViewById(R.id.rv_now_playing_popular);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_now_playing.setLayoutManager(linearLayoutManager);
        adapter = new PlayingAdapter(getActivity());
        adapter.setNowcominglist(listnexpage);

        rv_now_playing.setAdapter(adapter);

        view_Model = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_Model.getNowPlaying(currentPage);
        view_Model.getResultNowPlaying().observe(getActivity(), showNowPlaying);


        view_model2 = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        view_model2.getPopular();
        view_model2.getResultPopular().observe(getActivity(), showPopular);
        rv_now_playing.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv_now_playing.getLayoutManager();
                if(check == false){
                    if( linearLayoutManager!=null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listnexpage.size()-1){
                        if( currentPage <= maxPage){
                            check = true;
                            currentPage++;
                            listnexpage.add(null);
                            adapter.notifyDataSetChanged();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    listnexpage.remove(null);
                                    view_Model.getNowPlaying(currentPage);
                                    view_Model.getResultNowPlaying().observe(getActivity(), showNowPlaying);
//                                        listnexpage.addAll(newUpcoming.getResults());
//
//                                    });
                                    check = false;
                                }
                            }, 2000);
                        }
                    }
                }
            }

        });
        return view;
    }
    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
//            rv_now_playing.setLayoutManager(new LinearLayoutManager(getActivity()));
//            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
//            adapter.setListNowPlaying(nowPlaying.getResults());
//            rv_now_playing.setAdapter(adapter);
            maxPage = nowPlaying.getTotal_pages();
            listnexpage.addAll(nowPlaying.getResults());
            adapter.notifyDataSetChanged();

//            ItemClickSupport.addTo(rv_now_playing).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
//                    return false;
//                }
//            });
            ItemClickSupport.addTo(rv_now_playing).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", "" + nowPlaying.getResults().get(position).getId());
                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_MovieDetailsFragment, bundle);


                }
            });
            dialog.dismiss();
        }


    };
    private Observer<Popular> showPopular = new Observer<Popular>() {
        @Override
        public void onChanged(Popular popular) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false);
            rv_now_playing_popular.setLayoutManager(linearLayoutManager);
            PopularAdapter adapt = new PopularAdapter(getActivity());
            adapt.setListPopular(popular.getResults());
            rv_now_playing_popular.setAdapter(adapt);
        }
    };
}