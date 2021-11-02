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
import com.example.moviedb.adapter.UpComingAdapter;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.UpComing;
import com.example.moviedb.repositories.MovieRepository;
import com.example.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link upComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class upComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public upComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment upComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static upComingFragment newInstance(String param1, String param2) {
        upComingFragment fragment = new upComingFragment();
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

    private RecyclerView rv_upcoming_fragment;
    private MovieViewModel viewModel;
    private ProgressDialog dialog;
    private int currentPage = 1;
    private int maxPage = 1;
    private List<UpComing.Results> listnexpage = new ArrayList<>();
    private ComingAdapter adapter;
    public boolean check = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listnexpage.clear();

        currentPage = 1;
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        rv_upcoming_fragment = view.findViewById(R.id.rv_upcoming_fragment);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv_upcoming_fragment.setLayoutManager(linearLayoutManager);
//        UpComingAdapter adapter = new UpComingAdapter(getActivity());
//        rv_upcoming_fragment.setAdapter(adapter);
//        rv_upcoming_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new ComingAdapter(getActivity());
        adapter.setUpCominglist(listnexpage);

        rv_upcoming_fragment.setAdapter(adapter);

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getUpComing(currentPage);
        viewModel.getResultUpComing().observe(getActivity(), showUpComing);
        rv_upcoming_fragment.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) rv_upcoming_fragment.getLayoutManager();
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
                                    viewModel.getUpComing(currentPage);
                                    viewModel.getResultUpComing().observe(getActivity(), showUpComing);
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


    private Observer<UpComing> showUpComing = new Observer<UpComing>() {
        @Override
        public void onChanged(UpComing upComing) {
            maxPage = upComing.getTotal_pages();
//            adapter.setUpCominglist(upComing.getResults());
                listnexpage.addAll(upComing.getResults());
//                listnexpage.add(null);
            adapter.notifyDataSetChanged();


                ItemClickSupport.addTo(rv_upcoming_fragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("movieId", "" + listnexpage.get(position).getId());
                        Navigation.findNavController(v).navigate(R.id.action_upComingFragment_to_MovieDetailsFragment, bundle);
                    }
                });
            dialog.dismiss();
        }

    };




}