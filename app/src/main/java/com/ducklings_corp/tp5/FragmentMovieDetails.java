package com.ducklings_corp.tp5;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentMovieDetails extends Fragment implements View.OnClickListener {
    View view;
    Button goBack;
    Movie movie;
    TextView moviePlot;
    TextView movieTitle;
    TextView movieYear;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        view = layoutInflater.inflate(R.layout.movie_details_layout,viewGroup,false);

        goBack = view.findViewById(R.id.goBackToTheFuture);
        moviePlot = view.findViewById(R.id.moviePlot);
        movieTitle = view.findViewById(R.id.movieTitle);
        movieYear = view.findViewById(R.id.movieYear);

        goBack.setOnClickListener(this);

        movie = ((MainActivity)getActivity()).movieRequest();

        moviePlot.setText(movie.plot);
        movieTitle.setText(movie.title);
        movieYear.setText(movie.year+"");
        movie.applyImage(view);

        return view;
    }

    public void onClick(View view){
        ((MainActivity)getActivity()).backToSearch();
    }
}
